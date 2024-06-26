package carmenromano.jpaApiD3.services;

import carmenromano.jpaApiD3.entities.Author;
import carmenromano.jpaApiD3.entities.Blog;
import carmenromano.jpaApiD3.exceptions.NotFoundException;
import carmenromano.jpaApiD3.payloads.BlogPostsPayload;
import carmenromano.jpaApiD3.repositories.AuthorsRepository;
import carmenromano.jpaApiD3.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BlogService {
    @Autowired
   private  BlogPostsRepository blogPostsRepository;
    @Autowired
    private AuthorsRepository authorRepository;

    public Page<Blog> getAllBlogPosts(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 100) pageSize = 100;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return blogPostsRepository.findAll(pageable);
    }

    public Blog saveBlog(BlogPostsPayload payload){
            Author author = authorRepository.findById(payload.getAuthorId())
            .orElseThrow(() -> new NotFoundException(payload.getAuthorId()));
            Blog newBlog = new Blog();
            newBlog.setCategoria(payload.getCategoria());
            newBlog.setTitolo(payload.getTitolo());
            newBlog.setCover(payload.getCover());
            newBlog.setContenuto(payload.getContenuto());
            newBlog.setTempoDiLettura(payload.getTempoDiLettura());
            newBlog.setAuthor(author);
            return blogPostsRepository.save(newBlog);
}

    public Blog findById(int blogId){
        return this.blogPostsRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
    }

    public Blog findByIdAndUpdate(int id, Blog updatedBlog){
        Blog found = this.findById(id);
                found.setCategoria(updatedBlog.getCategoria());
                found.setTitolo(updatedBlog.getTitolo());
                found.setCover(updatedBlog.getCover());
                found.setContenuto(updatedBlog.getContenuto());
                found.setTempoDiLettura(updatedBlog.getTempoDiLettura());
                return this.blogPostsRepository.save(found);
            }



    public void findAndDelete(int id){
        Blog found = this.findById(id);
        this.blogPostsRepository.delete(found);
    }


//    public List<Blog> findByReadingTime(int readingTime) {
//        List<Blog> foundBlogs = new ArrayList<>();
//        for (Blog blog : this.blogList) {
//            if (blog.getTempoDiLettura() > readingTime) {
//                foundBlogs.add(blog);
//            }
//        }
//        if (foundBlogs.isEmpty()) {
//            throw new RuntimeException("Nessun blog trovato con tempo di lettura: " + readingTime);
//        }
//        return foundBlogs;
//    }
    }

