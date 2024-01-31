package com.example.kafka.domain;

public class Payload {

    private String content;

    private String clientIpAddress;

    private String userAgentHeader;

    public Payload() {
    }

    public Payload(String content, String clientIpAddress, String userAgentHeader) {
        this.content = content;
        this.clientIpAddress = clientIpAddress;
        this.userAgentHeader = userAgentHeader;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getUserAgentHeader() {
        return userAgentHeader;
    }

    public void setUserAgentHeader(String userAgentHeader) {
        this.userAgentHeader = userAgentHeader;
    }

    @Override
    public String toString() {
        return STR."Payload{content='\{content}\{'\''}, clientIpAddress='\{clientIpAddress}\{'\''}, userAgentHeader='\{userAgentHeader}\{'\''}\{'}'}";
    }
}
