package com.yidiansishiyi.zelinaiclientsdk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZelinAIResponse {
    /**
     * @param aiContent ai返回参数
     */
    private String aiContent;
}