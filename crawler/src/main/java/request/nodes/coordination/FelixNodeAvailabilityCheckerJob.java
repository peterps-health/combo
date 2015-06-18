package request.nodes.coordination;

import org.quartz.*;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class FelixNodeAvailabilityCheckerJob implements Job {

    org.slf4j.Logger logger = LoggerFactory.getLogger(FelixNodeAvailabilityCheckerJob.class);
    public FelixNodeAvailabilityCheckerJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("I say hello !" + context.getJobDetail().getJobDataMap().get("color"));;
    }
}