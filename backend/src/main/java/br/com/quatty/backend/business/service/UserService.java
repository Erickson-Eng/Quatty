package br.com.quatty.backend.business.service;


import br.com.quatty.backend.application.dto.request.UserRequest;

public interface UserService {

    void registerUser(UserRequest userRequest);
}
