package org.example.fuluppgift.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.fuluppgift.dao.UserDAO;
import org.example.fuluppgift.models.Users;
import org.example.fuluppgift.util.HashingUtil;

import java.util.Date;


// då ska vi fixa login funktioner
// när man söker i urlen ska vi ha /login
@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {

        // sätter upp möjlighet att fylla i vad användarnamn och lösenord ska vara
        String username = req.getParameter("username") == null ? "" :
                req.getParameter("username");
        String password = req.getParameter("password") == null ? "" :
                req.getParameter("password");
        // samt utrymme för errormeddelande
        String error = "";

        // har användarnamn och/eller lösenord inte fyllts i så ska detta errormeddelande visas
        if(username.isBlank() || password.isBlank()) {
            error(req, res, "Vänligen fyll i alla fält", username);
            return;
        }

        UserDAO udao = new UserDAO();
        Users users = null;

        try {
            users = udao.getUser(username);
            if(users == null){
                error(req, res, "Ingen användare med detta namn hittas. Kontrollera dina uppgifter.", username);
                return;
            }
            if(!HashingUtil.Verify(password, users.getPassword())){
                error(req, res, "Fel lösenord, försök igen", username);
                return;
            }

            // dessa är för funktioner jag inte har atm
//            users.setLastLogin(new Date());
//            udao.update(users);

            HttpSession session = req.getSession();
            session.setAttribute("users", users);

            res.sendRedirect(req.getContextPath() + "/");

        } catch (Exception e) {
            error = e.getMessage();
        }

    }


    // logout funktion
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Boolean logout = Boolean.parseBoolean(req.getParameter("logout"));

            if (logout) {
                HttpSession session = req.getSession(false);
                if(session != null) {
                    session.setAttribute("user", null);
                    session.invalidate();
                }
                req.setAttribute("message", "You have been logged out");
            }

            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    // tillslut funktion för error
    private void error(HttpServletRequest req, HttpServletResponse res, String error, String username) {
        req.setAttribute("error", error);
        req.setAttribute("username", username);

        try {
            System.out.println("Returning with error: " + error);
            req.getRequestDispatcher("/view/login.jsp").forward(req, res);

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


}
