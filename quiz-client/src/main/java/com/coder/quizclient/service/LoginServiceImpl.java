package com.coder.quizclient.service;



import com.coder.quizclient.payloads.LoginRequest;
import com.coder.quizclient.payloads.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@Service
public class LoginServiceImpl implements LoginService {

    @Value("${service.base.url}")
    String REST_SERVICE_URI;
    //public static final String REST_SERVICE_URI = "https://whispering-springs-63266.herokuapp.com";

    private final String PATH_LOGIN = "/auth";

    @Scope(WebApplicationContext.SCOPE_SESSION)
    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        RestTemplate restTemplate = new RestTemplate();
        LoginResponse loginResponse = restTemplate.postForObject(REST_SERVICE_URI + PATH_LOGIN, loginRequest, LoginResponse.class);
        return loginResponse;
    }
}
