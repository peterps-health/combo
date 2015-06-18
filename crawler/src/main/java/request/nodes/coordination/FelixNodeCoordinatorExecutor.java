package request.nodes.coordination;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Created by peter.puzanovs on 18/06/2015.
 */
public class FelixNodeCoordinatorExecutor extends HttpServlet {

    public FelixNodeCoordinatorExecutor(AvailableFelixNodes availableFelixNodes) {
        this.availableFelixNodes = availableFelixNodes;
    }

    private AvailableFelixNodes availableFelixNodes;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getOutputStream().print(String.format("{beat:%s}", new Date().getTime()));
        } catch (IOException e) {

        }
    }
}
