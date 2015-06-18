package com.crawler;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.osgi.service.component.ComponentContext;
import org.slf4j.LoggerFactory;

/**
 * Created by peter.puzanovs on 03/05/2015.
 */
@Component(label = "Crawling Service", immediate = true)
public class CrawlService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(CrawlService.class);

    @Activate
    public void init(ComponentContext componentContext) {
        logger.error("hello");
    }

    @Deactivate
    public void die(ComponentContext componentContext) {
        logger.error("bye bye");
    }

}
