package com.coder.quizclient.service;



import com.coder.quizclient.payloads.LoginResponse;
import com.coder.quizclient.payloads.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public UserDTO signup(UserDTO userDTO, LoginResponse loginResponse);

}

