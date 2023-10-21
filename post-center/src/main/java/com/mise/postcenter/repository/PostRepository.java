package com.mise.postcenter.repository;

import com.mise.postcenter.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Long> {
    // 可以添加自定义的查询方法
}