package com.jason.ddoMsg.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.jason.ddoMsg.startup.Bootstrap;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener {
	private Bootstrap bootstrap = null;

	private static final Logger logger = Logger.getLogger(InitListener.class);
    /**
     * Default constructor. 
     */
    public InitListener() {
        
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	logger.info("the context is initializing");
    	bootstrap = new Bootstrap();
    	bootstrap.startup();
    	logger.info("the context initialized finished");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         if (bootstrap != null) {
        	 bootstrap.stop();
         }
    }
	
}
