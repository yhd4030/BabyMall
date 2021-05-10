package com.cmx.mall.model;

import lombok.Data;

@Data
public class JSONResult {
    private boolean success;
    private String msg;
    private Object object;

    public JSONResult() {
    }

    public JSONResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
    public JSONResult(boolean success, Object object) {
        this.success = success;

        this.object = object;
    }
    public JSONResult(boolean success, String msg, Object object) {
        this.success = success;
        this.msg = msg;
        this.object = object;
    }
}
