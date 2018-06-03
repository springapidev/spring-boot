package com.coder.quizclient.service;


import com.coder.quizclient.payloads.LoginRequest;
import com.coder.quizclient.payloads.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest loginRequest);
}
