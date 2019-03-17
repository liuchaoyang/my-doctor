package com.doctor.service;


import com.doctor.exception.APIBaseException;
import com.doctor.util.HttpSSLUtils;
import com.doctor.util.MapXmlConverter;
import com.doctor.util.OrderIdGenerator;
import com.doctor.util.RandomUtils;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WxPayService {
    private static final Logger logger = LoggerFactory.getLogger(WxPayService.class);

    private static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";

    private static final String OPPEN_ID = "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o";
    private static final String APP_ID = "wx2421b1c4370ec43b";//appId
    private static final String APP_SECRET = "wx2421b1c4370ec43b";//app secret
    private static final String MECHANT_ID = "10000100";//商户号
    private static final String MACHINE_IP = "14.23.150.211";//机器IP
    private static final String NOTIFY_URL = "";//回调地址
    private static final String BODY = "JSAPI支付测试";

    private static final String KEY = "za0NWyAKDnL1twKymIZTnzMI8pdJ4PIj";


    private String getOpenId(String code) {
        try {

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(HttpSSLUtils.createSSLConnSocketFactory()).build();
            HttpGet httpGet = new HttpGet(OPENID_URL + "?appid=" + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code");

            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            logger.info("getOpenId response StatusCode:{}", statusLine.getStatusCode());
            String result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            logger.info("getOpenId response entity:{}", result);
            Map map = new Gson().fromJson(result, Map.class);
            System.out.println(map.get("openid"));
            System.out.println(map.get("session_key"));
            return String.valueOf(map.get("openid"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object pay(String userId, String code, int amount) throws APIBaseException {
        String openId = getOpenId(code);
        if (openId == null) {
            throw APIBaseException.USER_NOT_EXIST;
        }


        Map<String,Object> params = getParamsMap(openId, amount);
        String stringA = params.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        String stringSignTemp = stringA + "&key=" + KEY;
        String sign = DigestUtils.md5DigestAsHex(stringSignTemp.getBytes()).toUpperCase(); //注：MD5签名方式
        params.put("sign", sign);

        try {
            String xml = MapXmlConverter.map2xml(params);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(HttpSSLUtils.createSSLConnSocketFactory()).build();
            HttpPost httpPost = new HttpPost(PAY_URL);
            StringEntity entity = new StringEntity(xml, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            entity.setContentType("text/xml");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            logger.info("WxPay response StatusCode:{}", statusLine.getStatusCode());
            String result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            logger.info("WxPay response entity:{}", result);
            Map<String, String> map = MapXmlConverter.xml2Map(result);
            System.out.println(map.get("return_code"));

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Map<String,Object> getParamsMap(String openId, int amount) {
        Map<String,Object> map = new HashMap<>();
        map.put("openid", openId);
        map.put("openid",openId);
        map.put("appid",APP_ID);
        map.put("mch_id",MECHANT_ID);

        String randomStr = RandomUtils.generateRandomStr(16);
        map.put("nonce_str",randomStr); //随机字符串
//        map.put("sign","</sign>");//签名
        map.put("body",BODY);//商品描述

        //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
        String orderId = OrderIdGenerator.generateId();
        map.put("out_trade_no",orderId);
        //订单总金额，单位为分，参数值不能带小数
        map.put("total_fee",amount * 100);
        //终端IP，支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
        map.put("spbill_create_ip",MACHINE_IP);
        map.put("notify_url",NOTIFY_URL);
        map.put("trade_type","JSAPI");//交易类型
        map.put("limit_pay","no_credit");//上传此参数no_credit--可限制用户不能使用信用卡支付

        return map;
    }

    private StringBuilder getStringBuilder(String openId, int amount) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<openid>").append(openId).append("</openid>");
        sb.append("<appid>").append(APP_ID).append("</appid>");
        sb.append("<mch_id>").append(MECHANT_ID).append("</mch_id>");

        String randomStr = RandomUtils.generateRandomStr(16);
        sb.append("<nonce_str>").append(randomStr).append("</nonce_str>"); //随机字符串
        sb.append("<sign>").append("</sign>");//签名
        sb.append("<body>").append(BODY).append("</body>");//商品描述

        //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
        String orderId = OrderIdGenerator.generateId();
        sb.append("<out_trade_no>").append(orderId).append("</out_trade_no>");
        //订单总金额，单位为分，参数值不能带小数
        sb.append("<total_fee>").append(amount * 100).append("</total_fee>");
        //终端IP，支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
        sb.append("<spbill_create_ip>").append(MACHINE_IP).append("</spbill_create_ip>");
        sb.append("<notify_url>").append(NOTIFY_URL).append("</notify_url>");
        sb.append("<trade_type>").append("JSAPI").append("</trade_type>");//交易类型
        sb.append("<limit_pay>").append("no_credit").append("</limit_pay>");//上传此参数no_credit--可限制用户不能使用信用卡支付

        sb.append("</xml>");
        return sb;
    }

}
