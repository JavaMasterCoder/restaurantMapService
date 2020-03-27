package web.listeners;

import database.daos.AdminUserDAO;
import model.users.AdminUser;
import web.utils.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProdPersistenceUnit");
        servletContextEvent.getServletContext().setAttribute("factory", factory);

        EntityManager manager = factory.createEntityManager();
        AdminUserDAO adminUserDAO = new AdminUserDAO(manager);

        String adminLogin = "admin";
        String adminPassword = "12345678";

        AdminUser admin = adminUserDAO.findAdminByLogin(adminLogin);

        if (admin == null) {
            adminUserDAO.createAdminUser(adminLogin, adminPassword);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerFactory factory = PersistenceUtils.getEntityManagerFactory(servletContextEvent.getServletContext());

        if (factory != null) {
            factory.close();
        }
    }
}
