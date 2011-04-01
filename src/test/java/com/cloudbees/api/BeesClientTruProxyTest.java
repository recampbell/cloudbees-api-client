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

import com.cloudbees.api.util.ProxyServer;
import org.junit.Test;

/**
 * @author <a href="mailto:Olivier.LAMY@accor.com">Olivier Lamy</a>
 */
public class BeesClientTruProxyTest
{
    @Test
    public void requestTruProxy()
        throws Exception
    {
        ProxyServer.AuthAsyncProxyServlet authAsyncProxyServlet = new ProxyServer.AuthAsyncProxyServlet();
        ProxyServer proxyServer = new ProxyServer( authAsyncProxyServlet );

        try
        {
            proxyServer.start();
            BeesClientConfiguration beesClientConfiguration =
                new BeesClientConfiguration( "http://localhost:" + proxyServer.getPort() + "/", "foo", "bar", "xml", "1.0" );
            BeesClient beesClient = new BeesClient(beesClientConfiguration);
            ApplicationListResponse applicationListResponse = beesClient.applicationList();
        }
        finally
        {
            proxyServer.stop();
        }

    }
}
