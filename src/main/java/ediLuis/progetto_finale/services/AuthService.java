package ediLuis.progetto_finale.services;

import ediLuis.progetto_finale.entities.User;
import ediLuis.progetto_finale.exceptions.BadRequestException;
import ediLuis.progetto_finale.exceptions.UnauthorizedException;
import ediLuis.progetto_finale.payloads.loginPayloads.LoginDTO;
import ediLuis.progetto_finale.payloads.loginPayloads.LoginRespDTO;
import ediLuis.progetto_finale.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    public LoginRespDTO checkAndGenerateToken(LoginDTO body){
        User found = this.userService.findByEmail(body.email());
        if (found != null) return new LoginRespDTO(this.jwtTools.createToken(found));
        else throw new UnauthorizedException("Errore nella generazione del token");
    }

}
