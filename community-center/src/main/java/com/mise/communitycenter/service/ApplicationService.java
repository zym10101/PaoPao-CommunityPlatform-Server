package com.mise.communitycenter.service;


public interface ApplicationService {

    /**
     * 申请加入社区
     * @param userID 用户id
     * @param communityID 社区id
     * @return 申请结果
     */
    boolean applyForCommunity(long userID, long communityID);

    /**
     * 退出社区
     * @param userID 用户id
     * @param communityID 社区id
     * @return 退出结果
     */
    boolean exitCommunity(long userID, long communityID);


    /**
     * 同意用户加入社区
     * @param userID 用户id
     * @param communityID 社区id
     * @param handlerID 处理人id
     * @return 处理结果
     */
    boolean accept(long userID, long communityID, long handlerID);

    /**
     * 拒绝用户加入社区
     * @param userID 用户id
     * @param communityID 社区id
     * @param handlerID 处理人id
     * @return 处理结果
     */
    boolean refuse(long userID, long communityID, long handlerID);
}
