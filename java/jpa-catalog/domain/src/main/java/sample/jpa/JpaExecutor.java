package sample.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;

public class JpaExecutor {
    public static void execute(String name) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SampleUnit");
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Query query = em.createQuery("select a from EntityAlpha a");
            System.out.println("************************************************************");
            query.getResultList().forEach(System.out::println);
            System.out.println("************************************************************");

            HashMap<EnumAlpha, String> map = new HashMap<>();
            map.put(EnumAlpha.ALPHA, "Alpha");
            map.put(EnumAlpha.BETA, "Beta");
            map.put(EnumAlpha.GAMMA, "Gamma");

            EntityAlpha entity = new EntityAlpha(name, map);
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
