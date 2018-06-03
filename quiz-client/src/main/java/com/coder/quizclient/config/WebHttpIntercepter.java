package com.coder.quizclient.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Created by shajib on 5/1/2017.
 */
public class WebHttpIntercepter implements ClientHttpRequestInterceptor {

    private String Authorization;

    public WebHttpIntercepter() {
    }

    public WebHttpIntercepter(String Authorization) {
        this.Authorization = Authorization;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("Authorization", this.getAuthorization());
        return execution.execute(request, bytes);
    }
}
