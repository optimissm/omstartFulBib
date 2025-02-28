package org.example.fuluppgift;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.fuluppgift.dao.BookDAO;
import org.example.fuluppgift.models.Books;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/booklist")
public class BookList extends HttpServlet {

    public void init(){}

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Book List</h1>");
        out.println("<ul>");
        for(int i = 0; i < BookDAO.bok.length; i++) {
            Books book = BookDAO.bok[i];
            out.println("<li><a href=\"viewBooks?bookId" + book.getId() +
                    "\">" + book.getTitle() + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body></html>");

    }

    public void destroy(){}


}
