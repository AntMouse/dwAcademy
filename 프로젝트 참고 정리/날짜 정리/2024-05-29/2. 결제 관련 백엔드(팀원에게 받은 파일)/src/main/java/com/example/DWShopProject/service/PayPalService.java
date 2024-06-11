package com.example.DWShopProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.util.Base64;
import java.util.Map;

@Service
public class PayPalService {
    private final RestClient restClient;

    @Autowired
    public PayPalService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getAccessToken() {

        String clientId = "";
        String secret = "";
        String auth = clientId +":"+secret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic" + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials";

        Map response = restClient.post()
                .uri("https://api-m.sandbox.paypal.com/v1/oauth2/token")
                .headers(headers)
                .body(body)
                .retrieve()
                .body(Map.class);

        return (String) response.get("access_token");
    }

}
