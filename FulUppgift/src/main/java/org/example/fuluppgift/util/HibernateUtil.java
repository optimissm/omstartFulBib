package org.example.fuluppgift.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            if (sessionFactory == null)
            {
                // dessa kopplar till de dependencies
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                if(System.getenv("DB_URL") != null) {
                    configuration.setProperty( "hibernate.connection.url", System.getenv("DB_URL"));
                }
                if(System.getenv("DB_USERNAME") != null) {
                    configuration.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
                }
                if(System.getenv("DB_PASSWORD") != null) {
                    configuration.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
                }
                sessionFactory = configuration.buildSessionFactory();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
