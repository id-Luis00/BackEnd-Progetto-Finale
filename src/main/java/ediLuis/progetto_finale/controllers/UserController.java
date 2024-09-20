package ediLuis.progetto_finale.controllers;


import ediLuis.progetto_finale.entities.User;
import ediLuis.progetto_finale.payloads.userPayloads.NewUserDTO;
import ediLuis.progetto_finale.payloads.userPayloads.NewUserRespDTO;
import ediLuis.progetto_finale.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size){
        return this.userService.findAll(page, size);
    }




}
