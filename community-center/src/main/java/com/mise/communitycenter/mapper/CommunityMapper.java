package com.mise.communitycenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mise.communitycenter.domain.entity.Community;
import com.mise.communitycenter.domain.vo.MemberVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author whm,wlf
 * @date 2023/10/27 17:06
 */
@Mapper
public interface CommunityMapper extends BaseMapper<Community> {

    /**
     * 根据社区id查询社区成员
     * @param communityID 社区id
     * @return 社区成员id列表
     */
    @Select("select member_id from community_member " +
            "where community_id = #{communityID}")
    List<Long> findCommunityMembers(long communityID);

    /**
     * 根据社区id查询社区帖子
     * @param communityID 社区id
     * @return 帖子id列表
     */
    @Select("select post_id from community_post " +
            "where community_id = #{communityID}")
    List<Long> findCommunityPosts(long communityID);


    /**
     * 删除社区成员
     * @param communityID 社区id
     * @param memberID 成员id
     * @return 删除结果
     */
    @Delete("delete from community_user " +
            "where community_id = #{communityID} and user_id = #{memberID}")
    boolean deleteMember(long communityID, long memberID);

    /**
     * 添加社区成员
     * @param communityID 社区id
     * @param memberID 成员id
     * @return 添加结果
     */
    @Insert("insert into community_user (community_id, user_id, role) " +
            "values (#{communityID}, #{memberID}, #{role})")
    boolean addMember(long communityID, long memberID, int role);

    /**
     * 根据管理员id查询其管理的所有社区id
     * @param adminId 管理员id
     * @return 社区idList
     */
    @Select("select community_id from community_admin " +
            "where admin_id = #{adminId}")
    List<Long> getCommunitiesByAdminId(long adminId);

    @Insert("insert into community_user (community_id, user_id, role) " +
            "values (#{communityId}, #{userId}, 0)")
    boolean addAdmin(long communityId, long userId);

    @Delete("delete from community_user " +
            "where community_id = #{communityId} and user_id = #{userId}")
    boolean removeAdmin(long communityId, long userId);

    @Select("select community_id from community order by create_time desc limit 1")
    Long findLatestCommunityId();
}
