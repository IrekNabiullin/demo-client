package ru.javamentor.democlient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceRest implements UserService {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public UserServiceRest(
            RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl
    ) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }

    @Override
    public List<UserInfo> findAllUsers() {
        return restTemplate.exchange(
                serverUrl + "/api/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserInfo>>() {
                }
        ).getBody();
    }

    public List<UserInfo> getdefaultUsers() {
        return Collections.singletonList(new UserInfo() {{
            setName("James");
            setLastName("Brown");
            setAge((byte) 25);
        }});
    }

    public List<UserInfo> putUserById(Long id) {
        return restTemplate.exchange(
                serverUrl + "/api/users/" + id,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<List<UserInfo>>() {
                }
        ).getBody();
    }
}
