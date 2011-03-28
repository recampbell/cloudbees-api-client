/*
 * Copyright 2010-2011, CloudBees Inc.
 */

package net.stax.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
@Ignore
public class StaxClientTest
{
    StaxClient client;
    Properties properties;


    @Before
    public void setUp() throws Exception {
        properties = getConfigProperties(null, new File(System.getProperty("user.home"), ".bees/bees.config"));
        properties = getConfigProperties(properties, new File("stax-api-client/src/test/resource","test.data"));
        client = new StaxClient(get("bees.api.url"), get("bees.api.key"), get("bees.api.secret"), "xml", "1.0");
    }

    private Properties getConfigProperties(Properties properties, File propertiesFile)
    {
        if (properties == null)
            properties = new Properties();
        if (propertiesFile.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(propertiesFile);
                properties.load(fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ignored) {}
                }
            }
        }
        return properties;
    }

    private String getAppId() {
        return get("bees.project.app.domain") + "/" + get("bees.project.app.id");
    }

    private String getAppUrl() {
        return "http://" + get("bees.project.app.id") + "." + get("bees.project.app.domain") + "." + get("bees.domain.url");
    }

    private String get(String name)
    {
        return properties.getProperty(name);
    }

    
    public void _testTail() throws Exception
    {
        client.tailLog(getAppId(), "server", System.out);
    }
    
    @Test
    public void testHello() throws Exception
    {
        SayHelloResponse response = client.sayHello("Hello World");
        
        assertEquals("Hello World", response.getMessage());
    }
    
    public void _testApplicationDeployEar() throws Exception
    {
        ApplicationDeployArchiveResponse response = client.applicationDeployEar(getAppId(), "prod", "api deployment", "C:\\demo\\simple\\dist\\stax-deploy.zip",
                "C:\\demo\\simple\\dist\\stax-src.zip", null);
        System.out.println(response);
        assertEquals(getAppId(), response.getId());
        assertEquals(getAppUrl(), response.getUrl());
    }
    
    @Test
    public void testApplicationDeployWar() throws Exception
    {
        ApplicationDeployArchiveResponse response = client.applicationDeployWar(getAppId(), "prod", "api deployment", get("bees.app.war"),
                null, null);
        System.out.println(response);
        assertEquals(getAppId(), response.getId());
        assertEquals(getAppUrl(), response.getUrl());
    }

/*
    public void testApplicationGetSourceUrl() throws Exception
    {
        ApplicationGetSourceUrlResponse response = client.applicationGetSourceUrl(getAppId());
        System.out.println(response);
        assertEquals(getAppUrl(), response.getUrl());
    }
*/

    @Test
    public void testApplicationRestart() throws Exception
    {
        ApplicationRestartResponse response = client.applicationRestart(getAppId());
        System.out.println(response);
        assertEquals(true, response.isRestarted());
    }

    @Test
    public void testApplicationStop() throws Exception
    {
        ApplicationStatusResponse response = client.applicationStop(getAppId());
        System.out.println(response);
        assertEquals("stopped", response.getStatus());
    }

    @Test
    public void testApplicationStart() throws Exception
    {
        ApplicationStatusResponse response = client.applicationStart(getAppId());
        System.out.println(response);
        assertEquals("active", response.getStatus());
    }

    @Test
    public void testApplicationList() throws Exception
    {
        ApplicationListResponse response = client.applicationList();
        System.out.println(response);
        assertEquals(1, response.getApplications().size());
        ApplicationInfo app = response.getApplications().get(0);
        assertEquals(getAppId(), app.getId());
    }

    @Test
    public void testApplicationInfo() throws Exception
    {
        ApplicationInfo response = client.applicationInfo(getAppId());
        System.out.println(response);
        assertEquals(getAppId(), response.getId());
        URL url = new URL(getAppUrl());
        assertEquals(url.getHost(), response.getUrls()[0]);
    }

    private void applicationSetTitle(String title) throws Exception
    {
        Map<String, String> attrs = new HashMap<String, String>();
        attrs.put("title", title);
        ApplicationSetMetaResponse response = client.applicationSetMeta(getAppId(), attrs);
        System.out.println(response);
        assertTrue(response.isSuccess());
        ApplicationInfo response2 = client.applicationInfo(getAppId());
        System.out.println(response2);
        assertEquals(title, response2.getTitle());
    }

    @Test
    public void testApplicationSetMeta() throws Exception
    {
        applicationSetTitle("something else");
        applicationSetTitle("Test Hello");
    }

    @Test
    public void testApplicationDelete() throws Exception
    {
        ApplicationDeleteResponse response = client.applicationDelete(getAppId());
        System.out.println(response);
        assertTrue(response.isDeleted());
    }
    
    @Test
    public void testDatabaseCreate() throws Exception
    {
        DatabaseCreateResponse response = client.databaseCreate(get("bees.project.app.domain"),
                get("bees.db.name"), get("bees.db.user"), get("bees.db.password"));
        System.out.println(response);
        assertEquals(get("bees.db.name"), response.getDatabaseId());

        String msg = "";
        try {
            response = client.databaseCreate(get("bees.project.app.domain"),
                    get("bees.db.name"), get("bees.db.user"), get("bees.db.password"));
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assertTrue(msg.indexOf("Database already exists:") > -1);

        msg = "";
        try {
            response = client.databaseCreate(get("bees.project.app.domain"),
                    get("bees.db.name")+"more", get("bees.db.user"), get("bees.db.password"));
        } catch (Exception e) {
            msg = e.getMessage();
        }
        assertTrue(msg.indexOf("Database username already exists") > -1);
    }
    
    @Test
    public void testDatabaseInfo() throws Exception
    {
        DatabaseInfo response = client.databaseInfo(get("bees.db.name"), true);
        System.out.println(response);
        assertEquals(get("bees.db.name"), response.getName());
        assertEquals(get("bees.db.user"), response.getUsername());
        assertEquals(get("bees.db.password"), response.getPassword());
    }

    @Test
    public void testDatabaseList() throws Exception
    {
        DatabaseListResponse response = client.databaseList();
        System.out.println(response);
        assertEquals(1, response.getDatabases().size());
        DatabaseInfo db = response.getDatabases().get(0);
        assertEquals(get("bees.db.name"), db.getName());
        assertEquals(get("bees.db.user"), db.getUsername());
    }

    @Test
    public void testDatabaseDelete() throws Exception
    {
        DatabaseDeleteResponse response = client.databaseDelete(get("bees.db.name"));
        System.out.println(response);
        assertEquals(true, response.isDeleted());        

        DatabaseListResponse response2 = client.databaseList();
        System.out.println(response2);
        assertEquals(0, response2.getDatabases().size());
    }
}
