package com.school.midland.admin.client.auth;

import com.school.midland.admin.client.auth.dto.*;
import com.school.midland.admin.client.utils.ClientExceptionResponse;
import com.school.midland.admin.exception.AuthException;
import com.school.midland.admin.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthServiceClient {

    private final WebClient webClient;
    private static final String AUTH_SERVICE_BASE_URL = "http://localhost:8081/midland";

    public LoginResponse login(LoginRequest request) {
        return webClient.post()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/" + request.getRole().toLowerCase() + "/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })
                .bodyToMono(LoginResponse.class)
                .block(); // block() keeps same synchronous API style
    }

    public RegisterResponse register(RegisterRequest request, String authHeader) {
        return webClient.post()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", authHeader != null ? authHeader : "")
                .bodyValue(request)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })

                .bodyToMono(RegisterResponse.class)
                .block();
    }

    public RegisterResponse createAdmin(RegisterRequest request) {
        return webClient.post()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/superAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })

                .bodyToMono(RegisterResponse.class)
                .block();
    }

    public boolean deleteUser(String schoolEmail, String authHeader) {
        return webClient.delete()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/users/delete/" + schoolEmail)
                .header("Authorization", authHeader != null ? authHeader : "")
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })

                .bodyToMono(Boolean.class)
                .blockOptional()
                .orElse(false);
    }

    public UserDto getbyuserName(String username) {
        return webClient.get()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/users/get/" + username)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })

                .bodyToMono(UserDto.class)
                .block();
    }

    public UserDto getByEmail(String email) {
        return webClient.get()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/users/schoolEmail/" + email)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })
                .bodyToMono(UserDto.class)
                .block();
    }

    public UserDto updateUser(String email, RegisterRequest updatedUser,String authHeader) {
        return webClient.put()
                .uri(AUTH_SERVICE_BASE_URL + "/auth/users/update/"+ email)
                .header("Authorization", authHeader != null ? authHeader : "")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedUser)
                .retrieve()
                .onStatus(httpStatus->httpStatus.isError(),clientResponse -> {
                    return  clientResponse.bodyToMono(String.class)
                            .flatMap(responseBody->{

                                String errorMessage = ClientExceptionResponse.extractErrorMessageFromResponse(responseBody);
                                HttpStatus errorStatus= ClientExceptionResponse.extractErrorStatusFromResponse(responseBody);
                                return Mono.error(new AuthException(errorMessage, errorStatus));
                            });
                })

                .bodyToMono(UserDto.class)
                .block();
    }
}
