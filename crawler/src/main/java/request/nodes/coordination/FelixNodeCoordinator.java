package request.nodes.coordination;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.LoggerFactory;
import request.nodes.availability.StatusExecutor;

import javax.servlet.ServletException;

/**
 * Created by peter.puzanovs on 18/06/2015.
 */
@Component(label = "request.nodes.coordination.FelixNodeCoordinator", immediate = true)
public class FelixNodeCoordinator {
    //add node by IP
    //add node by dnsName
    //remove node by IP
    //remove node by dnsName
    org.slf4j.Logger logger = LoggerFactory.getLogger(FelixNodeCoordinator.class);

    @Reference
    private HttpService httpService;

    @Reference
    private AvailableFelixNodes availableFelixNodes;

    @Activate
    public void start(ComponentContext componentContext) {
        try {
            httpService.registerServlet("/add.node.by.ip", new FelixNodeCoordinatorExecutor(availableFelixNodes), null, null);
        } catch (ServletException e) {

        } catch (NamespaceException e) {

        }
    }
}
