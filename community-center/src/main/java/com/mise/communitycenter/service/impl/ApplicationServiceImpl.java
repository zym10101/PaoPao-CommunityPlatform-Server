package com.mise.communitycenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mise.communitycenter.domain.entity.Application;
import com.mise.communitycenter.enums.ApplicationStatus;
import com.mise.communitycenter.mapper.ApplicationMapper;
import com.mise.communitycenter.service.ApplicationService;
import com.mise.communitycenter.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public boolean applyForCommunity(long userID, long communityID) {
        Application application = new Application();
        application.setCommunityID(communityID);
        application.setUserID(userID);
        application.setStatus(ApplicationStatus.PENDING);
        application.setApplyTime(TimeUtil.getCurrentTime());
        int result = applicationMapper.insert(application);
        return result == 1;
    }

    @Override
    public boolean exitCommunity(long userID, long communityID) {
        UpdateWrapper<Application> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userID);
        wrapper.eq("community_id", communityID);
        int result = applicationMapper.delete(wrapper);
        return result == 1;
    }

    @Override
    public boolean accept(long userID, long communityID, long handlerID) {
        UpdateWrapper<Application> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userID);
        wrapper.eq("community_id", communityID);
        wrapper.set("status", ApplicationStatus.APPROVED);
        wrapper.set("handler_id", handlerID);
        wrapper.set("handle_time", TimeUtil.getCurrentTime());
        int result = applicationMapper.update(null, wrapper);
        return result == 1;
    }

    @Override
    public boolean refuse(long userID, long communityID, long handlerID) {
        UpdateWrapper<Application> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userID);
        wrapper.eq("community_id", communityID);
        wrapper.set("status", ApplicationStatus.DECLINED);
        wrapper.set("handler_id", handlerID);
        wrapper.set("handle_time", TimeUtil.getCurrentTime());
        int result = applicationMapper.update(null, wrapper);
        return result == 1;
    }
}
