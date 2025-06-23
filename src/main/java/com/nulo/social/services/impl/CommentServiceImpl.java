package com.nulo.social.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nulo.social.exception.ServiceException;
import com.nulo.social.model.comment.CommentEntity;
import com.nulo.social.model.comment.RecSaveComment;
import com.nulo.social.repository.CommentRepository;
import com.nulo.social.repository.PostRepository;
import com.nulo.social.repository.UserRepository;
import com.nulo.social.services.CommentService;
import com.nulo.social.utils.MessageUtils;

import jakarta.validation.Valid;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public CommentEntity save(@Valid RecSaveComment recSaveComment) {
		validaPost(recSaveComment.postId());
		validaAuthor(recSaveComment.authorId());
		
		var entity = RecSaveComment.toEntity(recSaveComment);
		return repository.save(entity);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<CommentEntity> list(PageRequest pageRequest) {
		return repository.findAll(pageRequest);
	}
	
	@Override
	public void delete(String id) {
		repository.delete(repository.findById(new ObjectId(id)).orElse(null));
	}
	
	public CommentEntity getOne(String id) {
        if (!ObjectId.isValid(id)) {
            throw new ServiceException(MessageUtils.INVALID_ID, HttpStatus.BAD_REQUEST);
        }
        return repository.findById(new ObjectId(id)).orElseThrow(() -> new ServiceException(MessageUtils.COMMENT_NOT_FOUND,
                                                                                            HttpStatus.NOT_FOUND));
    }
	
	@Override
	public void like(String id) {
		var comment = this.getOne(id);
		comment.setLikes(comment.getLikes() + 1);
		repository.save(comment);
	}

	private void validaPost(String postId) {
		if (!ObjectId.isValid(postId)) {
			throw new ServiceException(MessageUtils.INVALID_ID, HttpStatus.BAD_REQUEST);
		}
		
		if(!postRepository.existsById(new ObjectId(postId))) {
			throw new ServiceException(MessageUtils.POST_NOT_FOUND, HttpStatus.NOT_FOUND);
		};
	}
	
	private void validaAuthor(String authorId) {
		if (!ObjectId.isValid(authorId)) {
			throw new ServiceException(MessageUtils.INVALID_ID, HttpStatus.BAD_REQUEST);
		}
		
		if(!userRepository.existsById(new ObjectId(authorId))) {
			throw new ServiceException(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
		};
	}




}
