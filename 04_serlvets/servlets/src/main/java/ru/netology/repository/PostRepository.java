package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private final HashMap<Long, Post> repositoryDB = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public List<Post> all() {
        Collection<Post> posts;
        posts = repositoryDB.values();
        return new ArrayList<>(posts);
    }

    public Optional<Post> getById(long id) {
        if (repositoryDB.containsKey(id)) {
            return Optional.ofNullable(repositoryDB.get(id));
        }

        return Optional.empty();
    }

    public Post save(Post post) {

        if (post.getId() == 0) {
            Long postId = idCounter.addAndGet(1);
            post.setId(postId);
            repositoryDB.put(postId, post);
        } else {
            repositoryDB.put(post.getId(), post);
        }

        return post;
    }

    public void removeById(long id) {
        repositoryDB.remove(id);
    }
}