package com.tenforce.lodms.extractors;

import java.net.URL;

public class DcatProtocolExtractorConfig {
    private URL jsonContext = this.getClass().getClassLoader().getResource("context.jsonld");
    private String dcatProtocolEndpoint = "";

    public String getDcatProtocolEndpoint() {
        return dcatProtocolEndpoint;
    }

    public void setDcatProtocolEndpoint(String dcatProtocolEndpoint) {
        this.dcatProtocolEndpoint = dcatProtocolEndpoint;
    }

    public URL getJsonContext() {
        return jsonContext;
    }

    public void setJsonContext(URL jsonContext) {
        this.jsonContext = jsonContext;
    }
}
