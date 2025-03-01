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
        out.println("<div class='m-5'>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>\n");
        out.println("<h1>Book List</h1>");
        out.println("<ul>");

        for(int i = 0; i < BookDAO.bok.length; i++) {
            Books book = BookDAO.bok[i];
            out.println("<li><a href=\"viewBook?bookId=" + book.getId() +
                    "\">" + book.getTitle() + "</a></li>");
        }

        out.println("</ul>");
        out.println("</div>");
        out.println("</body></html>");

    }

    public void destroy(){}


}
