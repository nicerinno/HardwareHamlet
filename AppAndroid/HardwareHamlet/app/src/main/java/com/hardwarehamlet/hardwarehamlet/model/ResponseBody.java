package com.hardwarehamlet.hardwarehamlet.model;

public class ResponseBody {
    private String request_type;
    private String result;

    public ResponseBody(String request_type, String result) {
        this.request_type = request_type;
        this.result = result;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRequest_type() {
        return request_type;
    }

    public String getResult() {
        return result;
    }
}
