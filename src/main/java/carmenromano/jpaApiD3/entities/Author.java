package carmenromano.jpaApiD3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;


    public Author(int id, String nome, String cognome, String email, LocalDate dataDiNascita, String avatar) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = avatar;
    }
}
