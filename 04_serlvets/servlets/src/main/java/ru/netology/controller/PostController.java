package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    final Gson gson = new Gson();

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.getWriter().print(gson.toJson(service.all()));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        try {
            response.getWriter().print(gson.toJson(service.getById(id)));
        } catch (Exception e) {
            response.getWriter().print(gson.toJson(null));
        }
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.getWriter().print(gson.toJson(service.save(gson.fromJson(body, Post[].class)[0])));
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        try {
            if (service.getById(id) != null) {
                service.removeById(id);
                response.getWriter().print("post with ID = " + id + " has been removed");
            }
        } catch (Exception e) {
            response.getWriter().print("post with ID = " + id + " was not found");
        }
    }
}