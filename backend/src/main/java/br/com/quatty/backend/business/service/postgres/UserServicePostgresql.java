package br.com.quatty.backend.business.service.postgres;

import br.com.quatty.backend.application.dto.mapper.UserMapper;
import br.com.quatty.backend.application.dto.request.UserRequest;
import br.com.quatty.backend.business.entity.Role;
import br.com.quatty.backend.business.entity.User;
import br.com.quatty.backend.business.service.UserService;
import br.com.quatty.backend.resource.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServicePostgresql implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public void registerUser(UserRequest userRequest) {
        User entity  = buildSuperUser(userRequest);
        try {
            if (!verifyIfExist(userRequest.getUsername())){
                userRepository.save(entity);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    protected User createCommonUser(UserRequest userRequest){
        return User.builder()
                .email(userRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .username(userRequest.getUsername())
                .roles(Set.of(new Role(1L, null)))
                .build();
    }

    protected User buildSuperUser(UserRequest userRequest){
        return User.builder()
                .email(userRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRequest.getPassword()))
                .username(userRequest.getUsername())
                .roles(Set.of(new Role(1L, null), new Role(2L, null)))
                .build();
    }
    protected boolean verifyIfExist(String usernameOrEmail){
        return userRepository.findByUsername(usernameOrEmail).isPresent();
    }


}
