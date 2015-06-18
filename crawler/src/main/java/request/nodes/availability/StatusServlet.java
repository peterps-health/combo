package request.nodes.availability;

import com.crawler.CrawlExecutor;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

/**
 * Created by peter.puzanovs on 18/06/2015.
 */
@Component(label = "request.nodes.availability.StatusServlet",immediate = true)
public class StatusServlet {
    org.slf4j.Logger logger = LoggerFactory.getLogger(StatusServlet.class);

    @Reference
    private HttpService httpService;

    @Activate
    public void start(ComponentContext componentContext) {
        try {
            httpService.registerServlet("/node.status", new StatusExecutor(), null, null);
        } catch (ServletException e) {

        } catch (NamespaceException e) {

        }
    }
}
