package com.mise.communitycenter.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author whm,wlf
 * @date 2023/10/27 16:28
 */
@Data
@TableName("community")
public class Community {
    @TableId(value = "community_id", type = IdType.AUTO)
    private Long communityId;

//    @TableField("administrator_list")
//    private String administratorList;
//
//    @TableField("user_list")
//    private String userList;
//
//    @TableField("post_list")
//    private String postList;

    @TableField("is_public")
    private boolean isPublic;

//    @TableField("application_list")
//    private String applicationList;

    @TableField("create_time")
    private Date createTime;
}
