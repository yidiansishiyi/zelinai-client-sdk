package com.yidiansishiyi.zelinaiclientsdk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZelinAIRequest implements Serializable {

    /**
     * 请求体示例
     * {
     *     "app_id": "xxx",
     *     "request_id": "xxx",
     *     "uid": "xxx",
     *     "content": "你好"
     * }
     */
    private String app_id;
    private String request_id;
    private String uid;
    private String content;
    private static final long serialVersionUID = 1L;

}