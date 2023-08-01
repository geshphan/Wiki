package com.yemenshi.geshphan.main;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.yemenshi.geshphan.main.Comment.CommentStatus.DELETED;

@Repository
public class CommentRepository {

    private static final int PAGE_SIZE = 20;
    private static Map<String, Comment> repository = new HashMap<>();
    private static Map<Integer, Map<String, Comment>> box = new HashMap<>();
    public enum Status { SUCCESS, FAIL }

    static {
        box.put(1, repository);
    }

    private boolean isExceededLimit() {
        return repository.keySet().size() > PAGE_SIZE;
    }

    private void getNextPage() {
        if (!isExceededLimit()) return;

        int nextId = box.size() + 1;
        box.put(nextId, new HashMap<>(repository));
        repository = new HashMap<>();
    }

    public Status init(List<String> list) {
        for (String content : list) {
            String id = String.valueOf(System.currentTimeMillis());
            save(id, content);
        }

        return Status.SUCCESS;
    }

    public Status save(String id, String content) {
        getNextPage();
        repository.put(id, new Comment(id, content));

        return Status.SUCCESS;
    }

    public Status save(String content) {
        getNextPage();

        String id = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddhhmmssSS"));
        repository.put(id, new Comment(id, content));

        return Status.SUCCESS;
    }

    public Status delete(String id) {
        repository.get(id).changeStatus(DELETED);

        return Status.SUCCESS;
    }

    public Map<Integer, Map<String, Comment>> getBox() {
        return this.box;
    }

    public List<Comment> getCommentList() {
        return this.repository.values().stream()
                .filter(comment -> !comment.checkStatus(DELETED))
                .sorted(Comparator.comparing(Comment::getId).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<List<Comment>> getComment(String id) {
        Comment comment = this.repository.get(id);

        return Optional.ofNullable(comment == null ? null : List.of(comment));
    }
}
