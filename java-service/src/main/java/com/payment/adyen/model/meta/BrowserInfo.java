package com.payment.adyen.model.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
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

    public static BrowserInfo.Builder defaultInfo() {
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
