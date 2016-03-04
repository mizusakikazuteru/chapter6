package chapter6.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.mysql.jdbc.StringUtils;


@WebServlet(urlPatterns = {("/signUp")})
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	   request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	   List<String>messages = new ArrayList<String>();
	   HttpSession session = request.getSession();
	   if (isValid(request, messages) == true) {

		   User user = new User();
		   user.setFullName(request.getParameter("name"));
		   user.setAccount(request.getParameter("account"));
		   user.setPassword(request.getParameter("password"));
		   user.setEmail(request.getParameter("email"));
		   user.setDescription(request.getParameter("description"));

		   new UserService().register(user);

		   response.sendRedirect("./");
	   }else{
		   session.setAttribute("errorMessages", messages);
		   response.sendRedierct("signup");
	   }
   }

   private boolean isValid(HttpServletRequest request, List<String>messages){
	   String account = request.getParameter("account");
	   String password = request.getParameter("password");

	   if(StringUtils.isEmpty(account) == true){
		   messages.add("アカウント名を入力して下さい");
	   }
	   if(StringUtils.isEmpty(password) == true){
		   messages.add("パスワードを入力して下さい");
	   }
	   //TODOアカウントが既に利用されていないか、メールアドレスがすでに
	   //登録されていないか等も確認必要
	   if(messages.size() == 0){
		   return true;
	   }else{
		   return false;

	   }
   }
}
