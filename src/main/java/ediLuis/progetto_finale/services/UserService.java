package ediLuis.progetto_finale.services;

import ediLuis.progetto_finale.entities.User;
import ediLuis.progetto_finale.exceptions.BadRequestException;
import ediLuis.progetto_finale.exceptions.NotFoundException;
import ediLuis.progetto_finale.payloads.userPayloads.NewUserDTO;
import ediLuis.progetto_finale.payloads.userPayloads.NewUserRespDTO;
import ediLuis.progetto_finale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll (int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return this.userRepository.findAll(pageable);
    }

    public User findById(UUID id){
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente con id " + id + " non è stato trovato"));
    }

    public NewUserRespDTO saveNewUser(NewUserDTO body) {

        // controllo se l'utente esiste già
        if (this.userRepository.existsByEmail(body.email())) throw new BadRequestException("L'email è già in uso");
        if (this.userRepository.existsByUsername(body.username())) throw new BadRequestException("Questo username è già in uso");

        User user = new User(body.username(), body.email(), bcrypt.encode(body.password()), body.name(), body.surname());
        this.userRepository.save(user);
        return new NewUserRespDTO(user.getId());
    }

    public User findByEmail(String email){
       return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Non è stato trovato nessun utente con email " + email));
    }


}
