package com.distribuida.servicios;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext(unitName = "PUPrincipal")
    private EntityManager entityM;

    @Override
    public List<Book> findAll() {
        return entityM.createQuery("SELECT b FROM Book b").getResultList();

    }

    @Override
    public Book findById(Integer id) {
        Book book = entityM.find(Book.class, id);
        return book;
    }

    @Override
    public void insert(Book book) {
        entityM.persist(book);
    }

    @Override
    public void update(Book book) {
        Book book1 = entityM.find(Book.class, book.getId());
        if (null != book1){
            entityM.merge(book);
        }
    }

    @Override
    public void delete(Integer id) {
        Book book = entityM.find(Book.class, id);
        if (null != book){
            entityM.remove(book);
        }
    }
}
