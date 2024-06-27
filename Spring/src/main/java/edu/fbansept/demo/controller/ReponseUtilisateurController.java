package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.ReponseUtilisateurDao;
import edu.fbansept.demo.model.ReponseUtilisateur;
import edu.fbansept.demo.security.AppUserDetails;
import edu.fbansept.demo.security.IsAdmin;
import edu.fbansept.demo.security.IsUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponseutilisateur")
public class ReponseUtilisateurController {

    @Autowired
    ReponseUtilisateurDao reponseUtilisateurDao;

    @GetMapping("/liste")
    public List<ReponseUtilisateur> liste() {
        return reponseUtilisateurDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> get(@PathVariable int id) {

        Optional<ReponseUtilisateur> optionalReponseUtilisateur = reponseUtilisateurDao.findById(id);

        if(optionalReponseUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalReponseUtilisateur.get(),HttpStatus.OK);
    }

    @IsUser
    @PostMapping("")
    public ResponseEntity<ReponseUtilisateur> add(
            @RequestBody @Valid ReponseUtilisateur reponseUtilisateur,
            @AuthenticationPrincipal AppUserDetails userDetails) {

        reponseUtilisateur.setId(null);
        reponseUtilisateur.setUtilisateur(userDetails.getUtilisateur());

        reponseUtilisateurDao.save(reponseUtilisateur);
        return new ResponseEntity<>(reponseUtilisateur, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> update(
            @PathVariable int id,
            @RequestBody @Valid ReponseUtilisateur reponseUtilisateur) {
        reponseUtilisateur.setId(id);

        Optional<ReponseUtilisateur> optionalReponseUtilisateur = reponseUtilisateurDao.findById(id);

        if(optionalReponseUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponseUtilisateurDao.save(reponseUtilisateur);

        return new ResponseEntity<>(reponseUtilisateur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> delete(@PathVariable int id) {

        Optional<ReponseUtilisateur> optionalReponseUtilisateur = reponseUtilisateurDao.findById(id);

        if(optionalReponseUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponseUtilisateurDao.deleteById(id);

        return new ResponseEntity<>(optionalReponseUtilisateur.get(), HttpStatus.OK);
    }

}
