import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/NumberCheckServlet")
public class NumberValidationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String inputParam = req.getParameter("number");

        try {
            int number = Integer.parseInt(inputParam);

            if (number > 0) {
                // Continue to the servlet if the number is valid
                chain.doFilter(request, response);
            } else {
                // Set error message and forward back to index.jsp
                request.setAttribute("errorMessage", "Please enter a number greater than 0.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Set error message for non-numeric input and forward back to index.jsp
            request.setAttribute("errorMessage", "Enter a number.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {}
}
