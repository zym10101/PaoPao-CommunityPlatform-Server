package com.mise.communitycenter.service;

import com.mise.communitycenter.domain.vo.CommunityVO;
import com.mise.communitycenter.domain.vo.MemberVO;
import com.mise.communitycenter.domain.vo.PostVO;

import java.util.List;

/**
 * @author whm,wlf
 * @date 2023/10/27 17:04
 */
public interface CommunityService {

    /**
     * 创建社区
     * @param userID 用户id
     * @param communityVO 社区对象
     * @return 创建结果
     */
    boolean createCommunity(long userID, CommunityVO communityVO);

    /**
     * 获取社区成员
     * @param communityID 社区id
     * @return 社区成员id列表
     */
    List<MemberVO> getCommunityMembers(long communityID);

    /**
     * 获取社区内的帖子
     * @param communityID 社区id
     * @return 帖子列表
     */
    List<PostVO> getPosts(long communityID);

    /**
     * 删除社区中的某位成员
     * @param memberID 要删除的成员的id
     * @return 删除结果
     */
    boolean deleteMember(long communityID, long memberID);


    /**
     * 为社区添加成员
     * @param communityID 社区id
     * @param memberID 成员id
     * @return 添加操作结果
     */
    boolean addMember(long communityID, long memberID, int role);

    /**
     * 展示当前热门社区, 以及用户可能感兴趣的社区
     * @param userID 用户id
     * @return 社区列表
     */
    List<CommunityVO> showHotAndInterestingCommunities(long userID);


    /**
     * 根据社区id查询社区信息
     * @param communityId 社区id
     * @return 社区信息
     */
    CommunityVO getCommunityById(long communityId);
}
