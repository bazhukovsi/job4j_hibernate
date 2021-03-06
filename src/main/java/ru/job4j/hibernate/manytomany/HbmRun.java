package ru.job4j.hibernate.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.Author;
import ru.job4j.hibernate.model.Book;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book bookOne = Book.of("Как стать программистом Java?");
            Book bookTwo = Book.of("Что такое Spring Boot?");

            Author authorOne = Author.of("Иванов");
            authorOne.getBooks().add(bookOne);
            authorOne.getBooks().add(bookTwo);

            Author authorTwo = Author.of("Сидоров");
            authorTwo.getBooks().add(bookOne);

            session.persist(authorOne);
            session.persist(authorTwo);

            Author author = session.get(Author.class, 1);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
