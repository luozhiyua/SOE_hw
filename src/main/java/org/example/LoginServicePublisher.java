package org.example;
import javax.xml.ws.Endpoint;
public class LoginServicePublisher {
    public static void main(String[] args) {
        String wsUrl = "http://localhost:8080/LoginService";
        Endpoint.publish(wsUrl, new LoginServiceImpl());
    }
}