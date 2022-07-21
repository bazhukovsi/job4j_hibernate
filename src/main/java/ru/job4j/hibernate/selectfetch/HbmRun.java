package ru.job4j.hibernate.selectfetch;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.model.BankVacancies;
import ru.job4j.hibernate.model.Candidate;
import ru.job4j.hibernate.model.Vacancy;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Vacancy vacancy1 = Vacancy.of("Java Junior");
            Vacancy vacancy2 = Vacancy.of("Middle");
            Vacancy vacancy3 = Vacancy.of("Senior");
            Vacancy vacancy4 = Vacancy.of("C++ Developer");

            BankVacancies bankVacancies = new BankVacancies();
            bankVacancies.addVacancy(vacancy1);
            bankVacancies.addVacancy(vacancy2);
            bankVacancies.addVacancy(vacancy3);
            bankVacancies.addVacancy(vacancy4);
            session.save(bankVacancies);

            Candidate candidate = Candidate.of("Bazhukov Sergey", 3.5, bankVacancies);
            session.save(candidate);
            Candidate candidateStore = session.createQuery(
                    "select distinct candidate from Candidate candidate "
                            + "join fetch candidate.bankVacancies s "
                            + "join fetch s.vacancies v "
                            + "where candidate.id = :cId", Candidate.class
            ).setParameter("cId", 1).uniqueResult();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
