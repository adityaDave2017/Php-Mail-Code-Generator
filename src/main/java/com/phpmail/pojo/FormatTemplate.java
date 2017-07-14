package com.phpmail.pojo;

import com.google.gson.annotations.SerializedName;


public class FormatTemplate {

    @SerializedName(value = "head")
    private String head;

    @SerializedName(value = "body")
    private String body;

    @SerializedName(value = "tail")
    private String tail;

    @SerializedName(value = "headers")
    private String headers;

    @SerializedName(value = "mail_code")
    private String mailCode;


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    @Override
    public String toString() {
        return "FormatTemplate{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", tail='" + tail + '\'' +
                ", headers='" + headers + '\'' +
                ", mailCode='" + mailCode + '\'' +
                '}';
    }

}
