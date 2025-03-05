package org.example.fuluppgift.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.fuluppgift.dao.UserDAO;
import org.example.fuluppgift.models.Users;
import org.example.fuluppgift.util.HashingUtil;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("view/register.jsp").forward(req, resp);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // sätter funktion att fylla i alla fält
        // de fält vi vill ha när någon registrerar sig är:
        // namn, användarnamn, lösenord
        // id och när kontot skapades ska sättas av sig själv

        String name = req.getParameter("name") == null ? "" : req.getParameter("name");
        String username = req.getParameter("username") == null ? "" : req.getParameter("username");
        String password = req.getParameter("password") == null ? "" : req.getParameter("password");
        String password2 = req.getParameter("password2") == null ? "" : req.getParameter("password2");
        String error = null;

        if (name.isBlank() || username.isBlank() || password.isBlank() || password2.isBlank()) {
            error = "Du behöver fylla i alla fält för att fortsätta.";
        }

        if(!password.equals(password2)){
            error = "Lösenorden matchar inte";
        }

//        // Kontrollera om användaren redan finns
//        if (userService.existsByUsername(username)) {
//            req.setAttribute("error", "Användarnamnet är redan taget!");
//            req.getRequestDispatcher("register.jsp").forward(req, resp);
//            return;
//        }
//
//        // Spara användaren
//        userService.saveUser(username, password);
//
//        // Skicka tillbaka till inloggningssidan
//        response.sendRedirect("login.jsp");

        if (error != null) {

            req.setAttribute("error", error);
            req.setAttribute("name", name);
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("password2", password2);

            try {
                System.out.println("Felet som uppstod: " + error);

                req.getRequestDispatcher("view/register.jsp").forward(req, resp);

            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

            return;

        }

        // och så att vi får en ny användare så behöver vi sätta dessa värden
        // till en ny användare i vår Users klass och ocskå i vår
        // udao (user database)
        Users users = new Users();
        users.setName(name);
        users.setUsername(username);
        // men vi vill hasha lösenordet
        users.setPassword(HashingUtil.Encrypt(password));
        UserDAO udao = new UserDAO();

        try {
            udao.save(users);
            req.setAttribute("success", "Välkommen som ny användare i vårt bibliotek!");
        } catch (Throwable e) {
            req.setAttribute("error", "Vi kunde tyvärr inte skapa ny användare just nu. " +
                    "Vänligen kontakta Elsbieta för mer information");
        }

        // och för att vi ska kunna vara kvar på /register och få våra
        // success/error meddelande och också ha kvar våra ifyllda fält så
        // har vi denna:
        try {
            req.getRequestDispatcher("view/register.jsp").forward(req, resp);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }



    }


}
