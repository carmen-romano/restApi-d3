package carmenromano.jpaApiD3.services;

import carmenromano.jpaApiD3.entities.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class AuthorService {
    private List<Author> authorsList = new ArrayList<>();

    public List<Author> getAuthorsList(){
        return this.authorsList;
    }

    public Author saveAuthor(Author body){
        Random rndm = new Random();
        body.setId(rndm.nextInt(1,100));
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        this.authorsList.add(body);
        return body;
    }

    public Author findById(int id){
        Author found = null;
        for(Author author: this.authorsList){
            if(author.getId() == id)
                found = author;
        }
        if(found == null) throw new RuntimeException();
        else return found;
    }

    public Author findAndUpdate(int id, Author updatedAuthor){
        Author found = null;
        for(Author author: this.authorsList){
            if(author.getId() == id) {
                found = author;
                found.setNome(updatedAuthor.getNome());
                found.setCognome(updatedAuthor.getCognome());
                found.setEmail(updatedAuthor.getEmail());
                found.setDataDiNascita(updatedAuthor.getDataDiNascita());
            }
        }
        if(found == null) throw new RuntimeException();
        else return found;
    }

    public void findAndDelete(int id){
        Iterator<Author> iterator = this.authorsList.iterator();

        while(iterator.hasNext()){
            Author current = iterator.next();
            if(current.getId() == id){
                iterator.remove();
                return;
            }
            throw new RuntimeException("Autore non trovato con ID: " + id);
        }

        ////TEST
    }
        public Author findByName(String nome) {
            for (Author author : this.authorsList) {
                if (author.getNome().equals(nome)) {
                    return author;
                }
            }
            throw new RuntimeException("Autore non trovato con nome: " + nome);
        }
}
