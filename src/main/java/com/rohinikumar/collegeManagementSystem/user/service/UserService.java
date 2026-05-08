package com.rohinikumar.collegeManagementSystem.user.service;

import com.rohinikumar.collegeManagementSystem.user.dto.*;
import com.rohinikumar.collegeManagementSystem.user.entity.User;
import com.rohinikumar.collegeManagementSystem.user.enums.Role;
import com.rohinikumar.collegeManagementSystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(
            CreateUserRequest request,
            String creatorUsername
    ) {
        User creator = getCreator(creatorUsername);

        validateRoleCreation(
                creator.getRole(),
                request.getRole()
        );

        validateUserUniqueness(
                request.getUsername(),
                request.getEmail()
        );

        User user = buildUser(request, creator);

        return mapToResponse(
                userRepository.save(user)
        );
    }

    @Transactional
    public List<UserResponse> createUsers(
            BulkCreateUserRequest request,
            String creatorUsername
    ) {
        User creator = getCreator(creatorUsername);

        return request.getUsers()
                .stream()
                .map(userRequest -> {
                    validateRoleCreation(
                            creator.getRole(),
                            userRequest.getRole()
                    );

                    validateUserUniqueness(
                            userRequest.getUsername(),
                            userRequest.getEmail()
                    );

                    return mapToResponse(
                            userRepository.save(
                                    buildUser(userRequest, creator)
                            )
                    );
                })
                .toList();
    }

    public UserResponse updateUser(
            Long id,
            UpdateUserRequest request,
            String updaterUsername
    ) {
        User updater = getCreator(updaterUsername);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        validateRoleCreation(
                updater.getRole(),
                request.getRole()
        );

        existingUser.setUsername(request.getUsername());
        existingUser.setEmail(request.getEmail());
        existingUser.setRole(request.getRole());
        existingUser.setIsActive(request.getIsActive());

        return mapToResponse(
                userRepository.save(existingUser)
        );
    }

    public String deleteUser(
            Long id,
            String deleterUsername
    ) {
        User deleter = getCreator(deleterUsername);

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        validateRoleCreation(
                deleter.getRole(),
                user.getRole()
        );

        user.setIsActive(false);

        userRepository.save(user);

        return "User deactivated successfully";
    }

    public List<UserResponse> getAllActiveUsers() {
        return userRepository.findByIsActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private User getCreator(String username) {
        return userRepository
                .findByUsernameOrEmail(username, username)
                .orElseThrow(() ->
                        new RuntimeException("Creator not found")
                );
    }

    private void validateUserUniqueness(
            String username,
            String email
    ) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
    }

    private User buildUser(
            CreateUserRequest request,
            User creator
    ) {
        String defaultPassword =
                request.getUsername() + "@123";

        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(defaultPassword))
                .role(request.getRole())
                .isActive(true)
                .passwordChanged(false)
                .createdBy(creator)
                .build();
    }

    private void validateRoleCreation(
            Role creatorRole,
            Role targetRole
    ) {
        switch (creatorRole) {

            case SUPER_ADMIN -> {
            }

            case ADMIN -> {
                if (targetRole == Role.ADMIN
                        || targetRole == Role.SUPER_ADMIN) {
                    throw new RuntimeException(
                            "ADMIN can create only FACULTY or STUDENT"
                    );
                }
            }

            case FACULTY -> {
                if (targetRole != Role.STUDENT) {
                    throw new RuntimeException(
                            "FACULTY can create only STUDENT"
                    );
                }
            }

            default -> throw new RuntimeException(
                    "Not authorized"
            );
        }
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .build();
    }
}