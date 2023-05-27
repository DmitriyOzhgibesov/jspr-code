package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
    private HashMap<Long, Post> repositoryDB = new HashMap<>();

    public List<Post> all() {
        Collection<Post> posts;
        synchronized (repositoryDB) {
            posts = repositoryDB.values();
        }
        return new ArrayList<>(posts);
    }

    public Optional<Post> getById(long id) {
        if (repositoryDB.containsKey(id)){
            return Optional.ofNullable(repositoryDB.get(id));
        }

        return Optional.empty();
    }

    public Post save(Post post) {
        synchronized (repositoryDB) {
            repositoryDB.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        synchronized (repositoryDB) {
            repositoryDB.remove(id);
        }
    }
}