package com.coder.quizclient.service;



import com.coder.quizclient.config.WebHttpIntercepter;
import com.coder.quizclient.payloads.LoginResponse;
import com.coder.quizclient.payloads.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

/**
 * @author Mohammad Rajaul Islam
 * @created on 01/05/2018
 */
@Service
public class UserServiceImpl implements UserService {

    private final String PATH = "/api/auth";
    private final String PATH_ADD = PATH + "/signup";

    RestTemplate restTemplate=new RestTemplate();
    @Value("${service.base.url}")
    private String REST_SERVICE_URI;


    @Override
    public UserDTO signup(UserDTO userDTO, LoginResponse loginResponse) {
        restTemplate.setInterceptors(Collections.singletonList(new WebHttpIntercepter(loginResponse.getToken())));
        return restTemplate.postForObject(REST_SERVICE_URI + PATH_ADD, userDTO, UserDTO.class);
    }
}
