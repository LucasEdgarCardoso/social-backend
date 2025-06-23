package com.nulo.social.services.impl;

import com.nulo.social.exception.ServiceException;
import com.nulo.social.model.post.PostEntity;
import com.nulo.social.model.post.RecSavePost;
import com.nulo.social.model.post.RecUpdatePost;
import com.nulo.social.repository.PostRepository;
import com.nulo.social.services.PostService;
import com.nulo.social.utils.MessageUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;

    @Override
    @Transactional
    public PostEntity save(RecSavePost recSavePost) {
        PostEntity post = RecSavePost.toEntity(recSavePost);
        return repository.save(post);
    }

    @Override
    @Transactional
    public PostEntity update(RecUpdatePost recUpdatePost) {
        var postId = new ObjectId(recUpdatePost.id());
        PostEntity savedPost = repository.findById(postId)
                .orElseThrow(() -> new ServiceException(MessageUtils.POST_NOT_FOUND, HttpStatus.NOT_FOUND));
        PostEntity newPost = RecUpdatePost.updateValues(savedPost, recUpdatePost);

        repository.update(postId, newPost.getBody(), newPost.getTags());
        return newPost;
    }

    @Override
    @Transactional
    public void executeLogicalDelete(String postId) {
        if (ObjectId.isValid(postId)) {
            repository.deleteLogically(new ObjectId(postId));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostEntity> list(String bodyFilter, Pageable pageRequest) {
        return repository.findByBodyPageable(bodyFilter, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public PostEntity getOne(String id) {
        if (!ObjectId.isValid(id)) {
            throw new ServiceException(MessageUtils.INVALID_ID, HttpStatus.BAD_REQUEST);
        }
        return repository.findById(new ObjectId(id)).orElseThrow(() -> new ServiceException(MessageUtils.USER_NOT_FOUND,
                                                                                            HttpStatus.NOT_FOUND));
    }
}
