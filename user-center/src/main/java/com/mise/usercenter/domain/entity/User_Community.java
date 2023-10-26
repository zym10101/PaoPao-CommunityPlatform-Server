package com.mise.usercenter.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户和加入的社区的关联
 * @author whm
 * @date 2023/10/26 21:43
 */
@Data
@TableName("user-community")
public class User_Community {
    @TableField("userId")
    private Long userId;

    @TableField("community_list")
    private String communityList;
}
