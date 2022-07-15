package ru.job4j.hibernate.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.Brand;
import ru.job4j.hibernate.model.Model;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model modelOne = Model.of("Altea Freetrack");
            Model modelTwo = Model.of("Leon");
            Model modelThree = Model.of("Alhambra");
            Model modelFour = Model.of("Ibiza");
            Model modelFive = Model.of("Toledo");

            Brand brand = Brand.of("SEAT");
            brand.getModels().add(modelOne);
            brand.getModels().add(modelTwo);
            brand.getModels().add(modelThree);
            brand.getModels().add(modelFour);
            brand.getModels().add(modelFive);

            session.save(brand);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
