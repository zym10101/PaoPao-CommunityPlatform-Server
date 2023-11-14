package com.mise.communitycenter.service.impl;

import com.mise.communitycenter.domain.vo.CommunityVO;
import com.mise.communitycenter.domain.vo.MemberVO;
import com.mise.communitycenter.domain.vo.PostVO;
import com.mise.communitycenter.service.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author whm
 * @date 2023/10/27 17:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    @Override
    public boolean createCommunity(long userID, CommunityVO communityVO) {
        return false;
    }

    @Override
    public List<MemberVO> getCommunityMembers(long communityID) {
        return null;
    }

    @Override
    public List<PostVO> getPosts(long communityID) {
        return null;
    }

    @Override
    public boolean deleteMember(long memberID) {
        return false;
    }

    @Override
    public boolean addMember(MemberVO memberVO) {
        return false;
    }

    @Override
    public List<CommunityVO> showCommunities(long userID) {
        return null;
    }
}
