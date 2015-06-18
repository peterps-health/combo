package com.crawler;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

/**
 * Created by peter.puzanovs on 03/05/2015.
 */
@Component(label = "Crawling Service Servlet",immediate = true)
public class CrawlServlet{
    org.slf4j.Logger logger = LoggerFactory.getLogger(CrawlServlet.class);

    @Reference
    private HttpService httpService;

    @Activate
    public void start(ComponentContext componentContext) {
        logger.error("quick");
        try {
            httpService.registerServlet("/hello", new CrawlExecutor(), null, null);
        } catch (ServletException e) {

        } catch (NamespaceException e) {

        }
    }
}
