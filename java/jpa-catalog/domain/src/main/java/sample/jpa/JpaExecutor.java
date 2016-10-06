package sample.jpa;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;

public class JpaExecutor {
    public static void execute(String name) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SampleUnit");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            TypedQuery<EntityAlpha> query = em.createQuery("select a from EntityAlpha a", EntityAlpha.class);
            System.out.println("************************************************************");
            query.getResultList().forEach(e -> {
                System.out.println(e);
                e.test();
            });
            System.out.println("************************************************************");



            EntityAlpha entity = new EntityAlpha(name, Arrays.asList(new EmbeddableAlpha("x"), new EmbeddableAlpha("y"), new EmbeddableAlpha("z")));
            em.persist(entity);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }
}