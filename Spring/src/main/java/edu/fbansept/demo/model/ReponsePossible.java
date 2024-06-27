package edu.fbansept.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReponsePossible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected  Integer id;

    @NotBlank
    @NotBlank
    @Column(columnDefinition = "TEXT")
    protected String texte;

    @NotNull
    @Column(columnDefinition = "BOOLEAN")
    protected boolean bonneReponse;

    @ManyToOne(optional = false)
    protected Question question;

}
