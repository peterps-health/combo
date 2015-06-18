package request.nodes.coordination;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import request.nodes.FelixNode;

import java.io.IOException;
import java.util.LinkedList;

@DisallowConcurrentExecution
public class FelixNodeAvailabilityCheckerJob implements Job {

    org.slf4j.Logger logger = LoggerFactory.getLogger(FelixNodeAvailabilityCheckerJob.class);
    public FelixNodeAvailabilityCheckerJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        final LinkedList<FelixNode> availableNodes = (LinkedList<FelixNode>) context.getJobDetail().getJobDataMap().get(AvailableFelixNodes.AVAILABLE_NODES);;
        final LinkedList<FelixNode> unverifiedNodes = (LinkedList<FelixNode>) context.getJobDetail().getJobDataMap().get(AvailableFelixNodes.UNVERIFIED_NODES);;
        final LinkedList<FelixNode> recentlyVerifiedNodes = new LinkedList<FelixNode>();

        HttpClient client = HttpClientBuilder.create().build();
        checkFelixNodesInCollection(unverifiedNodes, recentlyVerifiedNodes, client);

        checkFelixNodesInCollection(availableNodes, recentlyVerifiedNodes, client);

        availableNodes.addAll(recentlyVerifiedNodes);

        logger.info("I say hello !");;
    }

    private void checkFelixNodesInCollection(LinkedList<FelixNode> unverifiedNodes, LinkedList<FelixNode> recentlyVerifiedNodes, HttpClient client) {
        for(FelixNode felixNode : unverifiedNodes) {
            HttpGet request = new HttpGet(felixNode.getIp());
            try {
                logger.debug(String.format("verifying: %s",felixNode.getIp()));
                client.execute(request);
                recentlyVerifiedNodes.add(felixNode);
            } catch (IOException e) {
                logger.error(String.format("Unable to acces %s due to:", felixNode.getIp()), e);
            }
        }
    }
}