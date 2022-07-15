package ru.job4j.hibernate.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.BrandBi;
import ru.job4j.hibernate.model.ModelBi;
import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<BrandBi> result = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            BrandBi seat = BrandBi.of("SEAT");
            session.save(seat);

            List<ModelBi> models = List.of(
                    ModelBi.of("Altea Freetrack", seat),
                    ModelBi.of("Leon", seat),
                    ModelBi.of("Ibiza", seat),
                    ModelBi.of("Toledo", seat),
                    ModelBi.of("Alhambra", seat)
            );
            for (ModelBi model : models) {
                session.save(model);
            }

            result = session.createQuery(
                    "select distinct b from BrandBi b join fetch b.models "
            ).list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (ModelBi model: result.get(0).getModels()) {
            System.out.println(model);
        }
    }
}
