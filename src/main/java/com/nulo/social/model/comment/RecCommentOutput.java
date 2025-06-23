package com.nulo.social.model.comment;

import java.time.LocalDateTime;
import java.util.List;

public record RecCommentOutput(
		String id,
        String author,
        String body,
        LocalDateTime createdAt,
        int likes
) {

	public RecCommentOutput(CommentEntity Comment) {
        this(Comment.getId().toHexString(), //
                Comment.getAuthor().toHexString(), //
                Comment.getBody(),
                Comment.getCreatedAt(),
                Comment.getLikes());
    }

    public static List<RecCommentOutput> toRec(List<CommentEntity> Comments) {
        return Comments.stream().map(RecCommentOutput::new).toList();
    }
	
}
