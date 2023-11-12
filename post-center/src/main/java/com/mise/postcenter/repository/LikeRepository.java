package com.mise.postcenter.repository;

import com.mise.postcenter.domain.entity.Comment;
import com.mise.postcenter.domain.entity.Like;
import com.mise.postcenter.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like, Long> {


    Like findFirstByOrderByLikeTimeDesc();
}
