package com.java.test.CodingChallenge.controller;
import com.java.test.CodingChallenge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "password".equals(password)) {
            return ResponseEntity.ok(jwtUtil.generateToken(username));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
