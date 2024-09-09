package com.karanc.UrlShortner.Json;

import com.karanc.UrlShortner.Entity.Url;

public class ResponseJson {
    private String successMessage;

    private String successCode;

    private ErrorJson error;

    private Url url;

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public ErrorJson getError() {
        return error;
    }

    public void setError(ErrorJson error) {
        this.error = error;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
