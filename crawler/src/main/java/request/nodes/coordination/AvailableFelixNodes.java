package request.nodes.coordination;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.osgi.service.component.ComponentContext;

import org.slf4j.LoggerFactory;

/**
 * Created by peter.puzanovs on 18/06/2015.
 */
@Component(label = "request.nodes.coordination.AvailableFelixNodes", immediate = true)
public class AvailableFelixNodes {

    org.slf4j.Logger logger = LoggerFactory.getLogger(AvailableFelixNodes.class);
    @Activate
    public void init(ComponentContext componentContext) {

//        Trigger trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .startNow()
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(10)
//                        .repeatForever())
//                .build();
//                JobDetail job = newJob(HelloJob.class)
//                .withIdentity("job1", "group1")
//                .build();
//
//        logger.error("hello");
    }
//    class HelloJob implements Job{
//
//        @Override
//        public void execute(JobExecutionContext context) throws JobExecutionException {
//            logger.info("I say hello !");
//        }
//    }
}
