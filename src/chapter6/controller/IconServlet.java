package chapter6.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter6.beans.User;
import chapter6.service.UserService;
import chapter6.utils.StreamUtil;

@WebServlet(urlPatterns = { "/icon" })
public class IconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String userId = request.getParameter("user_id");
		User user = new UserService().getUser(Integer.parseInt(userId));

		if (user == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		response.setContentType("image/png");

		byte[] icon = user.getIcon();
		ServletOutputStream outputStream = response.getOutputStream();
		ByteArrayInputStream bais = new ByteArrayInputStream(icon);
		StreamUtil.copy(bais, outputStream);
		outputStream.flush();

	}

}
