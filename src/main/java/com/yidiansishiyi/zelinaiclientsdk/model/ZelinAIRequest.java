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
     * @param app_id 提供方提供
     */
    private String app_id;
    /**
     * @param request_id 调用方提供会话id
     */
    private String request_id;
    /**
     * @param uid 调用方提供用户id
     */
    private String uid;
    /**
     * @param content 用户输入
     */
    private String content;
    private static final long serialVersionUID = 1L;

}