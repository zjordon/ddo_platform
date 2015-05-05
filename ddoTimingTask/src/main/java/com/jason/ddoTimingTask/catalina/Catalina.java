/**
 * 
 */
package com.jason.ddoTimingTask.catalina;

import org.apache.log4j.Logger;

import com.jason.ddoTimingTask.dao.DaoManager;
import com.jason.ddoTimingTask.scheduler.TaskScheduler;

/**
 * 启动引擎
 * @author jasonzhang
 *
 */
public class Catalina {

	private static final Logger logger = Logger
			.getLogger(Catalina.class);
	
	private boolean starting = false;
	private boolean stopping = false;

	private CatalinaShutdownHook shutdownHook;
	
	public void start() {
		logger.info("start init dao manager");
		DaoManager.getInstance().init();
		logger.info("end init dao manager");
		logger.info("start scheduler task");
		TaskScheduler.getInstance().init();
		logger.info("end scheduler task");
		if (shutdownHook == null) {
			shutdownHook = new CatalinaShutdownHook();
		}
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}
	
	public void stop() {
		Runtime.getRuntime().removeShutdownHook(shutdownHook);
	}
	
	public static void main(String args[]) {
    	args = new String[] {"start"};
    	System.setProperty("catalina.home", "D:\\work\\ddo\\ddoTimingTask");
        (new Catalina()).process(args);
    }
	

    /**
     * The instance main program.
     *
     * @param args Command line arguments
     */
    public void process(String args[]) {

        //setAwait(true);
        //setCatalinaHome();
        try {
            if (arguments(args)) {
                if (starting) {
                    load(args);
                    start();
                } else if (stopping) {
                    //stopServer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    /* 
     * Load using arguments
     */
    public void load(String args[]) {

        try {
            if (arguments(args)) {
            	load();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private void load() {
    	//do nothing
    }
    
   
    
    
    /**
     * Process the specified command line arguments, and return
     * <code>true</code> if we should continue processing; otherwise
     * return <code>false</code>.
     *
     * @param args Command line arguments to process
     */
    private boolean arguments(String args[]) {

        if (args.length < 1) {
            usage();
            return (false);
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-config")) {
                
            } else if (args[i].equals("-help")) {
                usage();
                return (false);
            } else if (args[i].equals("start")) {
                starting = true;
                stopping = false;
            } else if (args[i].equals("stop")) {
                starting = false;
                stopping = true;
            } else {
                usage();
                return (false);
            }
        }

        return (true);

    }
    
    /**
     * Print usage information for this application.
     */
    private void usage() {

        System.out.println
            ("usage: java com.ptnetwork.dataCompress.App"
             + " [ -config {pathname} ]"
             + " { start | stop }");

    }
	
	private class CatalinaShutdownHook extends Thread {
    	public void run() {
    		
    		Catalina.this.stop();
    	}
    }
}
