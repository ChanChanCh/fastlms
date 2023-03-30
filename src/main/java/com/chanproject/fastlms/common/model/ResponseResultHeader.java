package com.chanproject.fastlms.common.model;

import lombok.Data;

@Data
public class ResponseResultHeader {

    boolean result;
    String message;

    public ResponseResultHeader(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResponseResultHeader(boolean result) {
        this.result = result;
    }
}
