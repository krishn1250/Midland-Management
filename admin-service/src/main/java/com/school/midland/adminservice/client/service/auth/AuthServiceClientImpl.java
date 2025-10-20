    package com.school.midland.adminservice.client.service.auth;

    import com.school.midland.adminservice.client.dtos.UserCreationRequest;
    import com.school.midland.adminservice.client.dtos.UserCreationResponse;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.*;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.util.HashMap;
    import java.util.Map;

    @Service
    @RequiredArgsConstructor
    public class AuthServiceClientImpl implements AuthServiceClient {

        private final RestTemplate restTemplate;

        @Value("${auth-service.base-url}")
        private String authServiceBaseUrl;

        @Override
        public  UserCreationResponse createUser(UserCreationRequest userCreationRequest) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserCreationRequest> request = new HttpEntity<>(userCreationRequest, headers);
            return restTemplate.postForObject(authServiceBaseUrl + "auth/register", request,  UserCreationResponse.class);
        }
//@Override
//public UserCreationResponse createUser(UserCreationRequest userCreationRequest) {
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//    headers.setBearerAuth(token.replace("Bearer ", "")); // forward token
//
//    HttpEntity<UserCreationRequest> request = new HttpEntity<>(userCreationRequest, headers);
//
//    return restTemplate.postForObject(
//            authServiceBaseUrl + "/midland/auth/register",
//            request,
//            UserCreationResponse.class
//    );
//}

    //    @Override
    //    public UserCreationResponse getbyuserName(String username) {
    //        String url = authServiceBaseUrl + "users/get/" + username; // append username
    //        return restTemplate.getForObject(url, UserCreationResponse.class);
    //    }
    public boolean deleteUser(String schoolEmail,String authHeader){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            headers.set("Authorization", authHeader);
        }
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                authServiceBaseUrl + "users/delete/" + schoolEmail,
                HttpMethod.DELETE,
                entity,
                Boolean.class
        );
        return response.getBody()!=null && response.getBody();
    }

        public UserCreationResponse getbyuserName(String username) {
            return restTemplate.getForObject(
                    authServiceBaseUrl + "users/get/" + username,
                    UserCreationResponse.class
            );
        }
        public UserCreationResponse getByEmail(String email) {
            return restTemplate.getForObject(
                    authServiceBaseUrl + "users/schoolEmail/" + email,
                    UserCreationResponse.class
            );
        }



    }
