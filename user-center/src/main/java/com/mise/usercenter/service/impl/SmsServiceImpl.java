package com.mise.usercenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.Gson;
import com.mise.usercenter.domain.entity.AliConfig;
import com.mise.usercenter.mapper.AliMapper;
import com.mise.usercenter.service.SmsService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author whm
 * @date 2023/10/24 21:16
 */
@Service
public class SmsServiceImpl implements SmsService {
    private com.aliyun.dysmsapi20170525.Client client;

    private Random random;

    public SmsServiceImpl(AliMapper aliMapper) {
        this.random = new Random();

        AliConfig aliConfig = aliMapper.selectById(1);
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(aliConfig.getAccessKey())
                .setAccessKeySecret(aliConfig.getAccessSecret());
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        try {
            this.client = new com.aliyun.dysmsapi20170525.Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void send(String phone) throws Exception {
        int code = random.nextInt(100000, 999999);
        System.out.println(code);

        SendSmsRequest request = new SendSmsRequest();
        request.phoneNumbers = phone;
        request.signName = "mise2023";
        request.templateCode = "";
        request.templateParam = JSONObject.toJSONString(new JSONObject().fluentPut("code", code));
        SendSmsResponse response = client.sendSms(request);
        System.out.println(new Gson().toJson(response.body));

    }
}
