
package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenProvider;

public class AuthController {

    public AuthController(UserService us, JwtTokenProvider jwt) {}

    public Object register(Object req) { return null; }
    public Object login(Object req) { return null; }
}
