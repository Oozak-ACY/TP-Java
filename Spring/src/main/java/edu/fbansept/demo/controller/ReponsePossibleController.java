package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.security.AppUserDetails;
import edu.fbansept.demo.security.IsAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponsepossible")
public class ReponsePossibleController {

    @Autowired
    ReponsePossibleDao reponsePossibleDao;

    @GetMapping("/liste")
    public List<ReponsePossible> liste() {
        return reponsePossibleDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReponsePossible> get(@PathVariable int id) {

        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(id);

        if(optionalReponsePossible.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalReponsePossible.get(),HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("")
    public ResponseEntity<ReponsePossible> add(
            @RequestBody @Valid ReponsePossible reponsePossible,
            @AuthenticationPrincipal AppUserDetails userDetails) {

        reponsePossible.setId(null);

        reponsePossibleDao.save(reponsePossible);
        return new ResponseEntity<>(reponsePossible, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/{id}")
    public ResponseEntity<ReponsePossible> update(
            @PathVariable int id,
            @RequestBody @Valid ReponsePossible reponsePossible) {
        reponsePossible.setId(id);

        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(id);

        if(optionalReponsePossible.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponsePossibleDao.save(reponsePossible);

        return new ResponseEntity<>(reponsePossible, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReponsePossible> delete(@PathVariable int id) {

        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(id);

        if(optionalReponsePossible.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponsePossibleDao.deleteById(id);

        return new ResponseEntity<>(optionalReponsePossible.get(), HttpStatus.OK);
    }

}
