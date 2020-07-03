package com.bohniman.vmsmaintenance.payload;

import java.util.Objects;

public class JsonResponse {

    private Boolean result = false;
    private Object payload;
    private String message;

    public JsonResponse() {
    }

    public JsonResponse(Boolean result, Object payload, String message) {
        this.result = result;
        this.payload = payload;
        this.message = message;
    }

    public Boolean isResult() {
        return this.result;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Object getPayload() {
        return this.payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonResponse result(Boolean result) {
        this.result = result;
        return this;
    }

    public JsonResponse payload(Object payload) {
        this.payload = payload;
        return this;
    }

    public JsonResponse message(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JsonResponse)) {
            return false;
        }
        JsonResponse jsonResponse = (JsonResponse) o;
        return Objects.equals(result, jsonResponse.result) && Objects.equals(payload, jsonResponse.payload)
                && Objects.equals(message, jsonResponse.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, payload, message);
    }

    @Override
    public String toString() {
        return "{" + " result='" + isResult() + "'" + ", payload='" + getPayload() + "'" + ", message='" + getMessage()
                + "'" + "}";
    }

}