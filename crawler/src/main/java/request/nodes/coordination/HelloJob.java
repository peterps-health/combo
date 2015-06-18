package request.nodes.coordination;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {

    org.slf4j.Logger logger = LoggerFactory.getLogger(HelloJob.class);
    public HelloJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("I say hello !" + context.getJobDetail().getJobDataMap().get("color"));;
    }
}