package com.crawler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by peter.puzanovs on 03/05/2015.
 */
public class CrawlExecutor extends HttpServlet {
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
           try {
               resp.sendError(505);
           } catch (IOException e) {

           }
       }
}
