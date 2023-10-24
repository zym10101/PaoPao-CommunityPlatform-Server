package com.mise.postcenter.service.impl;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.repository.PostRepository;
import com.mise.postcenter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostByTitleContaining(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    public List<Post> getPostByTitleAndUserId(String title, Long userId) {
        return postRepository.findByTitleAndUserId(title, userId);
    }

    public Post createPost(Post post) {
        post.setCreateTime(new Date());
        post.setLastUpdateTime(new Date());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Override
    public void updatePost(Post post) {
        post.setLastUpdateTime(new Date());
        postRepository.save(post);
    }

    @Override
    public List<Post> getSimilarPost(Post targetPost, List<Post> postList, int topK){
        if (topK <= postList.size()) {
            return postList;
        }
        List<Integer> scoreList = new ArrayList<>();
        int keywordN = 10;
        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
        List<Keyword> targetKeywords = tfidfAnalyzer.analyze(targetPost.getContent(), keywordN);
        if (targetKeywords.size() < 10) {
            keywordN = targetKeywords.size();
        }
        for (Post post : postList) {
            List<Keyword> keywords = tfidfAnalyzer.analyze(post.getContent(), keywordN);
            int score = 0;
            for (Keyword keyword : targetKeywords) {
                for (Keyword keyword1 : keywords) {
                    if (keyword.getName().equals(keyword1.getName())) {
                        score += keyword.getTfidfvalue() * keyword1.getTfidfvalue();
                    }
                }
            }
            scoreList.add(score);
        }
        List<Post> similarPostList = new ArrayList<>();
        for (int i = 0; i < topK; i++) {
            int maxIndex = 0;
            int maxScore = 0;
            for (int j = 0; j < scoreList.size(); j++) {
                if (scoreList.get(j) > maxScore) {
                    maxScore = scoreList.get(j);
                    maxIndex = j;
                }
            }
            similarPostList.add(postList.get(maxIndex));
            scoreList.remove(maxIndex);
            postList.remove(maxIndex);
        }
        return similarPostList;
    }

}