package com.doctor.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsClient {
    private static final Logger logger = LoggerFactory.getLogger(SmsClient.class);

    @Value("${sms.access_key}")
    private String accessKeyId;
    @Value("${sms.access_key_secret}")
    private String accessKeySecret;

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String signName = "全科通";
    private static final String template = "SMS_154593459";

    public SendSmsResponse sendSmsCode(String mobile, String verifyCode) {
        try {
            //设置超时时间-可自行调整
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化ascClient,暂时不支持多region（请勿修改）
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                    accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
            request.setPhoneNumbers(mobile);
            request.setSignName(signName);
            request.setTemplateCode(template);
            request.setTemplateParam("{\"code\":\"" +verifyCode+ "\"}");
            //请求失败这里会抛ClientException异常
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if(sendSmsResponse.getCode() != null) {
                logger.info("Send sms:{},{}, response:{},{}", mobile, verifyCode, sendSmsResponse.getCode(), sendSmsResponse.getMessage());
            } else {
                logger.error("Send sms response null:{},{}", mobile, verifyCode);
            }
            return sendSmsResponse;
        } catch (Exception e) {
            logger.error("Send sms error:{},{}", mobile, verifyCode, e);
            return null;
        }
    }

}
