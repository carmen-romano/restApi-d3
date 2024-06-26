package carmenromano.jpaApiD3.services;

import carmenromano.jpaApiD3.entities.Author;
import carmenromano.jpaApiD3.exceptions.BadRequestException;
import carmenromano.jpaApiD3.exceptions.NotFoundException;
import carmenromano.jpaApiD3.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class AuthorService {

    @Autowired
    private AuthorsRepository authorsRepository;

    public Page<Author> getAllAuthors(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return authorsRepository.findAll(pageable);
    }

    public Author saveAuthor(Author newAuthor){
        this.authorsRepository.findByEmail(newAuthor.getEmail()).ifPresent(
                author -> { throw new BadRequestException("L'email " + author.getEmail() + " è già in uso!");}
        );
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + newAuthor.getNome() + "+" + newAuthor.getCognome());
        return authorsRepository.save(newAuthor);
    }

    public Author findById(int authorId){
        return this.authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findAndUpdate(int id, Author updatedAuthor){
        Author found = this.findById(id);
                found.setNome(updatedAuthor.getNome());
                found.setCognome(updatedAuthor.getCognome());
                found.setEmail(updatedAuthor.getEmail());
                found.setDataDiNascita(updatedAuthor.getDataDiNascita());
                found.setAvatar("https://ui-avatars.com/api/?name=" + updatedAuthor.getNome() + "+" + updatedAuthor.getCognome());
            return this.authorsRepository.save(found);

    }

    public void findAndDelete(int id){
        Author found = this.findById(id);
        this.authorsRepository.delete(found);
        }

    }
        ////TEST
     //   public Author findByName(String nome) {
    //        for (Author author : this.authorsList) {
    //            if (author.getNome().equals(nome)) {
    //                return author;
    //            }
    //        }
    //        throw new RuntimeException("Autore non trovato con nome: " + nome);
    //    }
//}
