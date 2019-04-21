package cn.javakk.service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: javakk
 * @Date: 2019/4/21 1:37
 */
@Service
public class MessageService {

    @Value("${tencent.message.appid}")
    private Integer appid;
    @Value("${tencent.message.appkey}")
    private String appkey;
    @Value("${tencent.message.smsSign}")
    private String smsSign;

    @Value("${tencent.message.register.templateId}")
    private int registerTemplateId;

    @Value("${tencent.message.register.effectiveTime}")
    private String effectiveTime;

    public void sendRegisterMessage(String phoneNumber, String code) {
        try {
            String[] params = {code, effectiveTime};
            SmsSingleSender sender = new SmsSingleSender(appid, appkey);
            sender.sendWithParam("86", phoneNumber, registerTemplateId, params, smsSign, "", "");
        } catch (Exception e) {
            // TODO: deal
        }
    }
}
