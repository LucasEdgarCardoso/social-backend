package com.nulo.social.model.post;

import java.time.LocalDateTime;
import java.util.List;

public record RecPostOutput(
        String id,
        String author,
        String body,
        String[] tags,
        LocalDateTime createdAt,
        int likes,
        Boolean deleted
) {

    public RecPostOutput(PostEntity post) {
        this(post.getId().toHexString(), //
                post.getAuthor().toHexString(), //
                post.getBody(),
                post.getTags(),
                post.getCreatedAt(),
                post.getLikes(),
                post.getDeleted());
    }

    public static List<RecPostOutput> toRec(List<PostEntity> posts) {
        return posts.stream().map(RecPostOutput::new).toList();
    }
}
