package carmenromano.jpaApiD3.services;

import carmenromano.jpaApiD3.entities.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class BlogService {
    private List<Blog> blogList = new ArrayList<>();

    public List<Blog> getBlogList(){
        return this.blogList;
    }

    public Blog saveBlog(Blog body){
        Random rndm = new Random();
        body.setId(rndm.nextInt(1,100));
        this.blogList.add(body);
        return body;
    }

    public Blog findById(int id){
        Blog found = null;
        for(Blog blog: this.blogList){
            if(blog.getId() == id)
                found = blog;
        }
        if(found == null) throw new RuntimeException();
        else return found;
    }

    public Blog findByIdAndUpdate(int id, Blog updatedBlog){
        Blog found = null;
        for(Blog blog: this.blogList){
            if(blog.getId() == id) {
                found = blog;
                found.setCategoria(updatedBlog.getCategoria());
                found.setTitolo(updatedBlog.getTitolo());
                found.setCover(updatedBlog.getCover());
                found.setContenuto(updatedBlog.getContenuto());
                found.setTempoDiLettura(updatedBlog.getTempoDiLettura());
            }
        }
        if(found == null) throw new RuntimeException();
        else return found;
    }

    public void findByIdAndDelete(int id){
        Iterator<Blog> iterator = this.blogList.iterator();

        while(iterator.hasNext()){
            Blog current = iterator.next();
            if(current.getId() == id){
                iterator.remove();
            }
        }
    }

    public List<Blog> findByReadingTime(int readingTime) {
        List<Blog> foundBlogs = new ArrayList<>();
        for (Blog blog : this.blogList) {
            if (blog.getTempoDiLettura() > readingTime) {
                foundBlogs.add(blog);
            }
        }
        if (foundBlogs.isEmpty()) {
            throw new RuntimeException("Nessun blog trovato con tempo di lettura: " + readingTime);
        }
        return foundBlogs;
    }
    }

