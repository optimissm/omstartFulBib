package org.example.fuluppgift.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.example.fuluppgift.models.Books;
import org.example.fuluppgift.dao.BookDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/viewBook")
public class BookController extends HttpServlet {

    public void doGet(HttpServlet req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String bookIdParam = req.getInitParameter("bookId");
        int bookId = Integer.parseInt(bookIdParam);

        Books selectedBook = null;
        for (Books book : BookDAO.bok) {
            if (book.getId() == bookId) {
                selectedBook = book;
                break;
            }
        }

        // och om vi hittar boken så vill jag se information om den
        out.println("<html><body>");
        if (selectedBook != null) {
            out.println("<h1>" + selectedBook.getTitle() + "</h1>");
            out.println("<p>Författare: " + selectedBook.getAuthor() + "</p>");
            out.println("<p>Genre: " + selectedBook.getGenre() + "</p>");
            out.println("<p>Är boken tillgänglig? " + (selectedBook.isAvailable() ? "Ja" : "Nej") + "</p>");
        } else {
            out.println("<p>Boken hittades inte.</p>");
        }
        out.println("<a href='/booklist'>Tillbaka</a>");
        out.println("</body></html>");


    }

}
