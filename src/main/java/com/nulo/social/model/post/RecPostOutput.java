package com.nulo.social.model.post;

import com.nulo.social.model.comment.CommentEntity;
import com.nulo.social.model.user.RecUserOutput;
import com.nulo.social.model.user.UserEntity;
import lombok.Data;

import java.util.List;

public record RecPostOutput(
        String id,
        String author,
        String body,
        String[] tags,
        Data createdAt,
        int likes,
        List<CommentEntity> comments,
        Boolean deleted
) {

    public RecPostOutput(PostEntity post) {
        this(post.getId().toHexString(), //
                post.getAuthor().toHexString(), //
                post.getBody(),
                post.getTags(),
                post.getCreatedAt(),
                post.getLikes(),
                post.getComments(),
                post.getDeleted());
    }

    public static List<RecPostOutput> toRec(List<PostEntity> posts) {
        return posts.stream().map(RecPostOutput::new).toList();
    }
}
