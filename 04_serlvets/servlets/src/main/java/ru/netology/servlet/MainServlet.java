package ru.netology.servlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import ru.netology.controller.PostController;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainServlet extends HttpServlet {
    private PostController controller;
    private final String GET = "GET";
    private final String POST = "POST";
    private final String DELETE = "DELETE";
    private final String PATHWITHOUTID = "/api/posts";
    private final String PATHWITHID = "/api/posts/\\d+";

    @Override
    public void init() {
        final var tomcat = new Tomcat();
        final Path baseDir;
        try {
            baseDir = Files.createTempDirectory("tomcat");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(9999);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
        tomcat.getServer().await();
    }
}