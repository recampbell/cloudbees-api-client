package com.cloudbees.api.util;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Olivier Lamy
 */
public class CloudbeesServer {

    Server server = null;

    int port;

    public void startServer()
        throws Exception
    {
        this.server = new Server( 0 );
        Context context = new Context( this.server, "/", 0 );
        context.addServlet( new ServletHolder(new CloudbessServlet()), "/*" );
        this.server.start();
        Connector connector = this.server.getConnectors()[0];
        this.port = connector.getLocalPort();
    }

    public void stopServer()
        throws Exception
    {
        if ( this.server != null && this.server.isRunning() )
        {
            this.server.stop();
        }
    }

    public int getPort() {
        return port;
    }

    static class CloudbessServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //TODO take care of &format=xml/json
            String action = req.getParameter("action");
            if (action.equals("application.list"))
            {
                String response = XmlResponseGenerator.applicationListResponse();
                resp.getWriter().print(response);
                return;
            }
        }
    }

}
