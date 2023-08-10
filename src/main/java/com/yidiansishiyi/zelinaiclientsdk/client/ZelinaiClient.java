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
/**
 * 客户端调用接口
 *
 * @author sanqi
 */
public class ZelinaiClient {
    private static final String HOST = "https://zelinai.com";
    private static final String SYNCHRONIZATION = "/biz/v1/app/chat/sync";

    /**
     * appkey: zelinai appkey
     *
     */
    private final String appkey;

    /**
     * appsecret: zelinai appsecret
     */
    private final String appsecret;

    public ZelinaiClient(String appkey, String appsecret) {
        this.appkey = appkey;
        this.appsecret = appsecret;
    }

    /**
     * @param zelinaiRequest 请求参数
     *
     * @return 基础返回值类封装的ai回复
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
     * @return 请求体
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
        hashMap.put("Content-Type", "application/json;charset=UTF-8");
        return hashMap;
    }
}

