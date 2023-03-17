package com.chestnut.entity;

public class Guestbook {
    private Integer commentId;
    private String comment;
    private String name;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Guestbook() {
    }

    public Guestbook(Integer commentId, String comment, String name) {
        this.commentId = commentId;
        this.comment = comment;
        this.name = name;
    }
}
