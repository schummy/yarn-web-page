package hello.container;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.yarn.annotation.OnContainerStart;
import org.springframework.yarn.annotation.YarnComponent;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

@YarnComponent
public class HelloPojo {

    private static final Log log = LogFactory.getLog(HelloPojo.class);

    @Autowired
    private Configuration configuration;

    @OnContainerStart
    public void publicVoidNoArgsMethod() throws Exception {
        log.info("Hello from HelloPojo");
/*working code
        FsShell shell = new FsShell(configuration);
        String current = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:"+current);
        Server server =
        new Server(21095);
        WebAppContext root = new WebAppContext();
        root.setContextPath("hdfs://127.0.0.1:9000/app/gs-yarn-basic/");
        server.setHandler(root);
        server.start();
        log.info("Server started, call join()");

        server.join();
        */


        Server server = new Server(8080);

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase("");
        webAppContext.setDescriptor("web.xml");
        webAppContext.setContextPath("/");
        server.setHandler(webAppContext);

        server.start();
        server.join();

              /*  log.info("About to list from hdfs root content");

        FsShell shell = new FsShell(configuration);
        for (FileStatus s : shell.ls(false, "/")) {
            log.info(s);
        }
        shell.close();*/
    }

}
