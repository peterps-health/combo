package request.nodes.coordination;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.osgi.service.component.ComponentContext;

import org.quartz.*;
import org.slf4j.LoggerFactory;
import request.nodes.FelixNode;

import java.util.LinkedList;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by peter.puzanovs on 18/06/2015.
 */
@Component(label = "request.nodes.coordination.AvailableFelixNodes", immediate = true)
public class AvailableFelixNodes {

    public final static String UNVERIFIED_NODES = "request.nodes.coordination.unverified.nodes";
    public final static String AVAILABLE_NODES = "request.nodes.coordination.available.nodes";

    private final LinkedList<FelixNode> availableNodes = new LinkedList<FelixNode>();
    private final LinkedList<FelixNode> unverifiedNodes = new LinkedList<FelixNode>();

    /**
     * Adds a node to the unverified internal list
     * Once node gets verified, it will be added to the list of available nodes
     * @param felixNode - Node you would like to add
     */
    public void addNode(FelixNode felixNode) {
        unverifiedNodes.add(felixNode);
    }

    org.slf4j.Logger logger = LoggerFactory.getLogger(AvailableFelixNodes.class);
    @Activate
    public void init(ComponentContext componentContext) {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = null;
        try {
            sched = schedFact.getScheduler();
            sched.start();


        logger.error("request.nodes.coordination.AvailableFelixNodes initialization started");
        JobDetail job = newJob(FelixNodeAvailabilityCheckerJob.class)
                .withIdentity("job1", "group1")
                .build();
        job.getJobDataMap().put(UNVERIFIED_NODES, unverifiedNodes);
        job.getJobDataMap().put(AVAILABLE_NODES, availableNodes);
        Trigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

                sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            logger.error("Unable to instantiate due to", e);
        }
        logger.error("request.nodes.coordination.AvailableFelixNodes initialized");
    }
}
