package vip.kernel.application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;





@WebServlet(name = "LogoutServlet", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(LogoutServlet.class.getCanonicalName());

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.log(Level.FINER, "Invalidating a user session...");
		
		HttpSession session = request.getSession(false);
		if (session != null) session.invalidate();

		response.sendRedirect(request.getContextPath());
	}
}
