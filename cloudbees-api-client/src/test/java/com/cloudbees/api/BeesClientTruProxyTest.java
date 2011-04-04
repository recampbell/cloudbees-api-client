/*
 * Copyright 2010-2011, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudbees.api;

import com.cloudbees.api.util.CloudbeesServer;
import com.cloudbees.api.util.ProxyServer;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mortbay.util.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:Olivier.LAMY@accor.com">Olivier Lamy</a>
 */
public class BeesClientTruProxyTest
{

    private Logger log = Logger.getLogger(getClass());


    @Test
    public void directRequest() throws Exception {
        CloudbeesServer cloudbeesServer = new CloudbeesServer();
        cloudbeesServer.startServer();

        try {
            BeesClientConfiguration beesClientConfiguration =
                new BeesClientConfiguration( "http://localhost:" + cloudbeesServer.getPort() + "/", "foo", "bar", "xml", "1.0" );
            BeesClient beesClient = new BeesClient(beesClientConfiguration);
            ApplicationListResponse response = beesClient.applicationList();
            assertNotNull(response);
            assertEquals(2, response.getApplications().size());

        } finally {
            cloudbeesServer.stopServer();
        }
    }

    @Test
    public void requestTruProxy()
        throws Exception
    {
        ProxyServer.AuthAsyncProxyServlet authAsyncProxyServlet = new ProxyServer.AuthAsyncProxyServlet();
        ProxyServer proxyServer = new ProxyServer( authAsyncProxyServlet );

        CloudbeesServer cloudbeesServer = new CloudbeesServer();
        cloudbeesServer.startServer();

        try
        {
            proxyServer.start();
            BeesClientConfiguration beesClientConfiguration =
                new BeesClientConfiguration( "http://localhost:" + cloudbeesServer.getPort() + "/", "foo", "bar", "xml", "1.0" );
            beesClientConfiguration.setProxyHost("localhost");
            beesClientConfiguration.setProxyPort(proxyServer.getPort());
            BeesClient beesClient = new BeesClient(beesClientConfiguration);
            ApplicationListResponse response = beesClient.applicationList();

            assertNotNull(response);
            assertEquals(2, response.getApplications().size());
            assertEquals(1, authAsyncProxyServlet.requestsReceived);
        }
        finally
        {
            cloudbeesServer.stopServer();
            proxyServer.stop();
        }

    }

    @Test
    public void requestTruProxyWithAutz()
        throws Exception
    {
        Map<String, String> autzs = new HashMap<String,String>();
        autzs.put("olamy","yoyoyoyo");
        ProxyServer.AuthAsyncProxyServlet authAsyncProxyServlet = new ProxyServer.AuthAsyncProxyServlet(autzs);
        ProxyServer proxyServer = new ProxyServer( authAsyncProxyServlet );

        CloudbeesServer cloudbeesServer = new CloudbeesServer();
        cloudbeesServer.startServer();

        try
        {
            proxyServer.start();
            BeesClientConfiguration beesClientConfiguration =
                new BeesClientConfiguration( "http://localhost:" + cloudbeesServer.getPort() + "/", "foo", "bar", "xml", "1.0" );
            beesClientConfiguration.setProxyHost("localhost");
            beesClientConfiguration.setProxyPort(proxyServer.getPort());
            beesClientConfiguration.setProxyUser("olamy");
            beesClientConfiguration.setProxyPassword("yoyoyoyo");
            BeesClient beesClient = new BeesClient(beesClientConfiguration);
            ApplicationListResponse response = beesClient.applicationList();

            assertNotNull(response);
            assertEquals(2, response.getApplications().size());
            // one for authz + one for the request
            assertEquals(2, authAsyncProxyServlet.requestsReceived);
        }
        finally
        {
            cloudbeesServer.stopServer();
            proxyServer.stop();
        }

    }

    @Test
    public void deployWarArchive() throws Exception {
        CloudbeesServer cloudbeesServer = new CloudbeesServer();
        cloudbeesServer.startServer();
        File local = new File("src/test/translate-puzzle-webapp-1.0.0-SNAPSHOT.war");
        File uploaded = File.createTempFile("uploaded", "foo");
        try
        {
            BeesClientConfiguration beesClientConfiguration =
                new BeesClientConfiguration( "http://localhost:" + cloudbeesServer.getPort() + "/", "foo", "bar", "xml", "1.0" );
            BeesClient beesClient = new BeesClient(beesClientConfiguration);
            beesClient.setVerbose(true);
            MockUploadProgress mockUploadProgress = new MockUploadProgress();

            beesClient.applicationDeployWar("fooId","env","desc",local.getPath(), null, mockUploadProgress);
            assertNotNull(cloudbeesServer.cloudbessServlet.items);
            int realFilesNumber = 0;

            for (FileItem fileItem : cloudbeesServer.cloudbessServlet.items) {
                log.info("fileItem " + fileItem.getName() + ", size " + fileItem.getSize());
                if (fileItem.getName() != null ) {
                    realFilesNumber++;
                    fileItem.write(uploaded);
                }
            }
            assertEquals(1, realFilesNumber);
            assertEquals("local and uploaded not the same size", local.length(), uploaded.length());
        } catch (Exception e) {
            throw e;
        } finally {
            cloudbeesServer.stopServer();
            uploaded.delete();
        }
    }

    static class MockUploadProgress implements UploadProgress {

        long total;

        public void handleBytesWritten(long deltaCount, long totalWritten, long totalToSend) {
            //
        }
    }

}
