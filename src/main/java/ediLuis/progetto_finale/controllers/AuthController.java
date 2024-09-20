package ediLuis.progetto_finale.controllers;


import ediLuis.progetto_finale.payloads.loginPayloads.LoginDTO;
import ediLuis.progetto_finale.payloads.loginPayloads.LoginRespDTO;
import ediLuis.progetto_finale.payloads.userPayloads.NewUserDTO;
import ediLuis.progetto_finale.payloads.userPayloads.NewUserRespDTO;
import ediLuis.progetto_finale.services.AuthService;
import ediLuis.progetto_finale.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO body){
        return this.authService.checkAndGenerateToken(body);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody NewUserDTO body){
        return this.userService.saveNewUser(body);
    }
}
