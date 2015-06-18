package request.nodes.coordination;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.osgi.service.component.ComponentContext;

import org.quartz.*;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by peter.puzanovs on 18/06/2015.
 */
@Component(label = "request.nodes.coordination.AvailableFelixNodes", immediate = true)
public class AvailableFelixNodes {

    org.slf4j.Logger logger = LoggerFactory.getLogger(AvailableFelixNodes.class);
    @Activate
    public void init(ComponentContext componentContext) {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = null;
        try {
            sched = schedFact.getScheduler();
            sched.start();
        } catch (SchedulerException e) {
            logger.error(e.getMessage());
        }

        logger.error("request.nodes.coordination.AvailableFelixNodes initialization started");
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();
        job.getJobDataMap().put("color", "Green");
        Trigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();

        try {
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        logger.error("request.nodes.coordination.AvailableFelixNodes initialized");
    }

}
