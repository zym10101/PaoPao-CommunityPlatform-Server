package com.mise.communitycenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mise.communitycenter.domain.entity.Community;
import com.mise.communitycenter.domain.vo.CommunityVO;
import com.mise.communitycenter.domain.vo.MemberVO;
import com.mise.communitycenter.domain.vo.PostVO;
import com.mise.communitycenter.mapper.CommunityMapper;
import com.mise.communitycenter.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whm,wlf
 * @date 2023/10/27 17:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public boolean createCommunity(long userID, CommunityVO communityVO) {
        Community community = new Community();
        community.setCommunityId(communityVO.getCommunityID());
        community.setPublic(communityVO.isPublic());
        community.setCreateTime(communityVO.getCreateTime());
        int result = communityMapper.insert(community);
        return result == 1;
    }

    @Override
    public List<MemberVO> getCommunityMembers(long communityID) {
        List<Long> memberIDs = communityMapper.findCommunityMembers(communityID);
        // TODO: 调用user-center提供的服务查询完整的成员信息
        List<MemberVO> members = new ArrayList<>();
        // 模拟
        for (Long id : memberIDs) {
            MemberVO member = new MemberVO();
            member.setUserID(id);
            member.setName("name" + id);
            members.add(member);
        }
        return members;
    }

    @Override
    public List<PostVO> getPosts(long communityID) {
        List<Long> postIDs = communityMapper.findCommunityPosts(communityID);
        //TODO: 调用post-center提供的服务查询完整的帖子信息
        List<PostVO> posts = new ArrayList<>();
        // 模拟
        for (Long id : postIDs) {
            PostVO post = new PostVO();
            post.setPostID(id);
            post.setCommunityId(String.valueOf(communityID));
            post.setPhoto("url");
            post.setTagList(new ArrayList<>());
            post.setIsPublic(true);
            post.setTitle("title" + id);
            post.setContent("content" + id);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public boolean deleteMember(long communityID, long memberID) {
        return communityMapper.deleteMember(communityID, memberID);
    }

    @Override
    public boolean addMember(long communityID, long memberID) {
        try {
            return communityMapper.addMember(communityID, memberID);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CommunityVO> showHotAndInterestingCommunities(long userID) {
        // TODO: 调用大数据模块提供的接口进行社区推荐
        return null;
    }

    @Override
    public CommunityVO getCommunityById(long communityId) {
        QueryWrapper<Community> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Community::getCommunityId, communityId);
        try {
            Community community = communityMapper.selectOne(wrapper);
            if(community == null) {
                log.error("No such communityId: {}", communityId);
                return null;
            }
            CommunityVO communityVO = new CommunityVO();
            communityVO.setCommunityID(community.getCommunityId());
            communityVO.setCreateTime(community.getCreateTime());
            communityVO.setName(community.getName());
            communityVO.setPublic(community.isPublic());
            return communityVO;
        } catch (TooManyResultsException e) {
            log.error("Duplicated communityId: {}", communityId);
            return null;
        }
    }
}
