package org.nands.app.services;

import org.nands.app.dto.CreateUserRequest;
import org.nands.app.dto.UserLogin;
import org.nands.app.exceptions.BadRequestException;
import org.nands.app.exceptions.NotFoundException;
import org.nands.app.exceptions.ValidationException;
import org.nands.app.models.User;
import org.nands.app.repositories.UserRepository;
import org.nands.app.utils.PasswordUtil;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository =
                new UserRepository();
    }

    /**
     * CREATE USER
     */
    public User create(
            CreateUserRequest request) {

        validateCreate(request);

        boolean exists =
                userRepository.existsByEmail(
                        request.getEmail()
                );

        if (exists) {

            throw new ValidationException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setId(
                UUID.randomUUID().toString()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                PasswordUtil.hash(
                        request.getPassword()
                )
        );

        user.setName(
                request.getName()
        );

        user.setRole("USER");

        userRepository.save(user);

        return user;
    }

    public User login(UserLogin user){
        validateLogin(user);
        User userExist = userRepository.findByEmail(user.getEmail());
        if(userExist == null){
            throw new NotFoundException("user with the username does'nt exist");
        }
        if(!PasswordUtil.verify(user.getPassword(), userExist.getPassword())){
            throw new BadRequestException("password incorect");
        }
        return userExist;
    }

    /**
     * VALIDATION
     */
    private void validateCreate(
            CreateUserRequest request) {

        if (request.getEmail() == null ||
                request.getEmail().isBlank()) {

            throw new ValidationException(
                    "Email required"
            );
        }

        if (!request.getEmail()
                .contains("@")) {

            throw new ValidationException(
                    "Invalid email"
            );
        }

        if (request.getPassword() == null ||
                request.getPassword().length() < 8) {

            throw new ValidationException(
                    "Password minimum 8 characters"
            );
        }

        if (request.getName() == null ||
                request.getName().isBlank()) {

            throw new ValidationException(
                    "Name required"
            );
        }
    }

    private void validateLogin(UserLogin user){
        if(user.getEmail() == null || user.getEmail().isBlank()){
            throw new ValidationException("Email required");
        }
        if(user.getPassword() == null || user.getPassword().isBlank()){
            throw new ValidationException("Password required");
        }
    }
}