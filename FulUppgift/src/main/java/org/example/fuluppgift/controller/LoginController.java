package org.example.fuluppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.fuluppgift.dao.UserDAO;
import org.example.fuluppgift.models.Users;
import org.example.fuluppgift.util.HashingUtil;

import java.io.IOException;


// då ska vi fixa login funktioner
// när man söker i urlen ska vi ha /login
@WebServlet("/login")
public class LoginController extends HttpServlet {

    // vi får fixa en fin doGet här så att vi har en fin minimalistisk länk som tar oss till login
    // istället för att den ser ut, som nu: localhost:8080/view/login.jsp
    // så vill jag ha localhost:8080/login so let's go
    // @Override

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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
        Users user;

        try {
            user = udao.getUser(username);
            if(user == null){
                error(req, res, "Ingen användare med detta namn hittas. Kontrollera dina uppgifter.", username);
                return;
            }

            if(!HashingUtil.Verify(password, user.getPassword())){
                error(req, res, "Fel lösenord, försök igen", username);
                return;
            }

            // dessa är för funktioner jag inte har atm
            // vill jag ha en "senast inloggad" funktion så får jag
            // lägga till den i users sen
//            users.setLastLogin(new Date());
//            udao.update(users);


            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);

            res.sendRedirect(req.getContextPath() + "/");

        } catch (Exception e) {
            error = e.getMessage();
        }

    }


    // logout funktion
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logoutParam = req.getParameter("logout");

        if(logoutParam != null && logoutParam.equals("true")) {
            HttpSession session = req.getSession(false);
            if(session != null) {
                session.invalidate();
            }
            // när man har loggats ut återvänder man till inloggningssidan
            req.setAttribute("message", "Du har loggats ut");
            // frågan är dock om jag bara ska återvända till hemsidan...?
            // alltså / eller om det är /index eller w/e

            // men vi håller oss till /login atm
//            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);

            // req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);


//        try {
//            Boolean logout = Boolean.parseBoolean(req.getParameter("logout"));
//
//            if (logout) {
//                HttpSession session = req.getSession(false);
//                if(session != null) {
//                    session.setAttribute("user", null);
//                    session.invalidate();
//                }
//                req.setAttribute("message", "You have been logged out");
//            }
//
//            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
//
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
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
