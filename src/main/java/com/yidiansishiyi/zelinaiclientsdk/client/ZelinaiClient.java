package com.yidiansishiyi.zelinaiclientsdk.client;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.yidiansishiyi.zelinaiclientsdk.common.BaseResponse;
import com.yidiansishiyi.zelinaiclientsdk.model.ZelinAIRequest;
import com.yidiansishiyi.zelinaiclientsdk.model.ZelinAIResponse;

import java.util.HashMap;
import java.util.Map;

import static com.yidiansishiyi.zelinaiclientsdk.utils.ApiAuthUtils.generateNonce;

public class ZelinaiClient {
    private static final String HOST = "https://zelinai.com";
    private static final String SYNCHRONIZATION = "/biz/v1/app/chat/sync";

    private final String appkey;

    private final String appsecret;

    public ZelinaiClient(String appkey, String appsecret) {
        this.appkey = appkey;
        this.appsecret = appsecret;
    }

    /**
     * 对话
     *{
     *     "app_id": "xxx",
     *     "request_id": "xxx",
     *     "uid": "xxx",
     *     "content": "你好"
     * }
     * @param zelinaiRequest
     * @return
     */
    public BaseResponse<ZelinAIResponse> doChat(ZelinAIRequest zelinaiRequest) {
        String url = HOST + SYNCHRONIZATION;
        String json = JSONUtil.toJsonStr(zelinaiRequest);
        String result = HttpRequest.post(url)
                .addHeaders(getHeaderMap(zelinaiRequest))
                .body(json)
                .execute()
                .body();
        TypeReference<BaseResponse<ZelinAIResponse>> typeRef = new TypeReference<BaseResponse<ZelinAIResponse>>() {};
        return JSONUtil.toBean(result, typeRef, false);
    }

    /**
     * 获取请求头
     *
     * @param zelinaiRequest 请求参数
     * @return
     */
    private Map<String, String> getHeaderMap(ZelinAIRequest zelinaiRequest) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("appkey", appkey);
        String nonce= RandomUtil.randomNumbers(16);
        hashMap.put("nonce", nonce);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        hashMap.put("timestamp", timestamp);

        String app_id = zelinaiRequest.getApp_id();
        String request_id = zelinaiRequest.getRequest_id();
        String uid = zelinaiRequest.getUid();
        String content = zelinaiRequest.getContent();
        hashMap.put("app_id",app_id);
        hashMap.put("request_id",request_id);
        hashMap.put("uid",uid);
        hashMap.put("content",content);
        hashMap.put("signature", generateNonce(hashMap, appsecret));
//        hashMap.put("Content-Type", "application/json");
        hashMap.put("Content-Type", "application/json;charset=UTF-8");
        return hashMap;
    }

//    public static void main(String[] args) {
//        // 替换成您的实际 appkey 和 appsecret
//        String appkey = "ak";
//        String appsecret = "sk";
//
//        // 创建 ZelinaiClient 实例
//        ZelinaiClient zelinaiClient = new ZelinaiClient(appkey, appsecret);
//
//        // 创建 DevChatRequest 对象并设置参数
//        DevChatRequest devChatRequest = new DevChatRequest();
//        devChatRequest.setApp_id("应用id,先创建应用");
//        devChatRequest.setRequest_id("会话id标示会话");
//        devChatRequest.setUid("用户 id");
//        devChatRequest.setContent("分析需求：分析增长情况，请使用折线图 原始数据：日期,用户数1号,10 2号,20 3号,30 4号,90 5号,0 6号,10 号,20");
//        System.out.println(zelinaiClient.doChat(devChatRequest));
//        // 调用 doChat 方法发送对话请求
//        BaseResponse<DevChatResponse> response = zelinaiClient.doChat(devChatRequest);
//    }
}

