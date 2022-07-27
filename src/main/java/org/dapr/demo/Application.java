package org.dapr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

@SpringBootApplication
//@ComponentScan(basePackages = {"org.dapr.*"})
public class Application {

    public static void main(String[] args) {
        /*
        try {
            Options options = new Options();
            options.addRequiredOption("p", "port", true, "The port this app will listen on");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            int port = Integer.parseInt(cmd.getOptionValue("port"));

            SpringApplication app = new SpringApplication(Application.class);
            app.run(String.format("--server.port=%d", port));
        } catch(Exception e) {
            System.out.println(e);
        }
        */
        try {
            SpringApplication.run(Application.class, args);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}