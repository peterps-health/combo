package com.deploy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

/**
 * Prepares Felix runtime
 */
public class Main {

    public static void main(String[] args) {
        try {
            FileUtils.deleteDirectory(new File(format("%s/felix-cache", args[0])));
            FileUtils.copyFile(new File("log4j.properties"), new File(format("%s/conf/log4j.properties", args[0])));
            FileUtils.copyFile(new File("config.properties"), new File(format("%s/conf/config.properties", args[0])));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
