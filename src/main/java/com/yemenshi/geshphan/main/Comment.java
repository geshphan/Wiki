package com.yemenshi.geshphan.main;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Comment {
    public enum CommentStatus {
        POSTED, DELETED
    }

    @Nullable
    private String id;

    @Nonnull
    private String content;

    private CommentStatus status;

    public Comment(String id, String content) {
        this.id = id;
        this.content = content;
        this.status = CommentStatus.POSTED;
    }

    public void changeStatus(CommentStatus status) {
        if (this.status == status) throw new IllegalStateException("Cannot change to the same status as the current status.");
        this.status = status;
    }

    public boolean checkStatus(CommentStatus status) {
        return this.status == status;
    }

}
