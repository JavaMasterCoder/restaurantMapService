package web.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

public class PersistenceUtils {
    public static EntityManagerFactory getEntityManagerFactory(ServletContext context) {
        return (EntityManagerFactory) context.getAttribute("factory");
    }

    public static EntityManager getEntityManager(ServletContext context) {
        EntityManagerFactory factory = getEntityManagerFactory(context);
        if (factory == null) {
            throw new IllegalStateException("No EntityManagerFactory found in the context");
        }

        return factory.createEntityManager();
    }
}
