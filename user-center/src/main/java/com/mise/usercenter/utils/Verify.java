package com.mise.usercenter.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.AddSmsTemplateRequest;
import com.aliyun.dysmsapi20170525.models.AddSmsTemplateResponse;

/**
 * @author whm
 * @date 2023/10/24 20:52
 */
public class Verify {
    /**
     * 此处通过从环境变量中读取AccessKey，初始化云呼叫中心Client
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createCCClient()
            throws Exception {
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户
        // 此处以把AccessKey 和 AccessKeySecret 保存在环境变量为例说明。您也可以根据业务需要，保存到配置文件里
        // 强烈建议不要把 AccessKey 和 AccessKeySecret 保存到代码里，会存在密钥泄漏风险
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(System.getenv("SMS_ACCESS_KEY_ENV"))
                .setAccessKeySecret(System.getenv("SMS_ACCESS_KEY_SECRET_ENV"));
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[]args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dysmsapi20170525.Client client = createCCClient();

        AddSmsTemplateRequest addSmsTemplateRequest = new AddSmsTemplateRequest()
                .setTemplateType(0)
                .setTemplateName("content3")
                .setTemplateContent("您正在申请手机注册，验证码为：${code}，5分钟内有效!")
                .setRemark("用于文件下载时的验证码");
        // 复制代码运行请自行打印API的返回值
        client.addSmsTemplate(addSmsTemplateRequest);
        AddSmsTemplateResponse response = client.addSmsTemplate(
                addSmsTemplateRequest
        );
        System.out.println(JSONObject.toJSONString(response));
    }
}
