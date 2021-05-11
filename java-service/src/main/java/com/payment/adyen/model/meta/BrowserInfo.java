package com.payment.adyen.model.meta;

public class BrowserInfo {
    
    private String userAgent;
    private String acceptHeader;
    private String language;
    private Integer colorDepth;
    private Integer screenHeight;
    private Integer screenWidth;
    private Integer timeZoneOffset;
    private boolean javaEnabled;

    private BrowserInfo() { }

    private BrowserInfo(String userAgent, String acceptHeader, String language, Integer colorDepth, Integer screenHeight, Integer screenWidth, Integer timeZoneOffset, boolean javaEnabled) {
        this.userAgent = userAgent;
        this.acceptHeader = acceptHeader;
        this.language = language;
        this.colorDepth = colorDepth;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.timeZoneOffset = timeZoneOffset;
        this.javaEnabled = javaEnabled;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getAcceptHeader() {
        return acceptHeader;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getColorDepth() {
        return colorDepth;
    }

    public Integer getScreenHeight() {
        return screenHeight;
    }

    public Integer getScreenWidth() {
        return screenWidth;
    }

    public Integer getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public boolean isJavaEnabled() {
        return javaEnabled;
    }

    public static BrowserInfo.Builder builder() {
        return new BrowserInfo().new Builder();
    }

    public class Builder {

        private Builder () {}

        public BrowserInfo buildDefault() {
            return new BrowserInfo(
                    "Mozilla\\/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\\/537.36 (KHTML, like Gecko) Chrome\\/70.0.3538.110 Safari\\/537.36",
                    "text\\/html,application\\/xhtml+xml,application\\/xml;q=0.9,image\\/webp,image\\/apng,*\\/*;q=0.8",
                    "nl-NL",
                    24,
                    723,
                    1536,
                    0,
                    true
            );
        }
    }
    
}
