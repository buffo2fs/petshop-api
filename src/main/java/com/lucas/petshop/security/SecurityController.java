package com.lucas.petshop.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String users() {
        return "AUTHORIZED USER";

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String managers() {
        return "AUTHORIZED MANAGER";
    }

}
