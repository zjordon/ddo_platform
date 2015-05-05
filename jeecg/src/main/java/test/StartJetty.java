/**
 * 
 */
package test;

/**
 * 启动内嵌的jetty
 * @author jason zhang
 *
 */
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
 
 
public class StartJetty {
 
    public static final int PORT = 8080;
    public static final String CONTEXT = "/jeecg";
    public static final String BASE_URL = "http://localhost:8080/jeecg";
 
    public static void main(String[] args) throws Exception {
        Server server = buildNormalServer(PORT, CONTEXT);
        server.start(); 
         
         
 
        System.out.println("Hit Enter in console to stop server");
        if (System.in.read() != 0) {
            server.stop();
            System.out.println("Server stopped");
            System.exit(0);
        }
    }
     
    public static Server buildNormalServer(int port, String contextPath) {
        Server server = new Server(port);
        WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);
        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webContext);
        server.setStopAtShutdown(true);
        return server;
    }
}
