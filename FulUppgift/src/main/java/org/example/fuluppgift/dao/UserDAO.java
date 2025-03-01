package org.example.fuluppgift.dao;

import org.example.fuluppgift.models.Users;
import org.example.fuluppgift.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

// denna behövs för databashantering av användare
public class UserDAO extends GenericDAO<Users, Integer>{

    public UserDAO() {
        super(Users.class);
    }

    public Users getUser(String username) throws SQLException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Users> query = session.createQuery("FROM Users WHERE username = :username", Users.class);
            query.setParameter("username", username);

            List<Users> users = query.list();
            if (users.isEmpty()) {
                return null;
            }

            return users.getFirst();

        }

    }



}
