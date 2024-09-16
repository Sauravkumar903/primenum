import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NumberCheckServlet")
public class NumberCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int number = Integer.parseInt(request.getParameter("number"));
        boolean isPrime = checkPrime(number);

        if (isPrime) {
            response.getWriter().write("Success! The number " + number + " is a prime number.");
        } else {
            response.getWriter().write("The number " + number + " is not a prime number.");
        }
    }

    private boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
