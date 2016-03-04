package chapter6.service;

import static chapter6.utils.ClosebleUtil.*;//直打ちエラー
import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class UserService
 */
@WebServlet("/UserService")
public class UserService extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserService() {

    	public void register(User user){

    		Connection conection();
    	try{
    		connection = getConnection();

    		Stirng encPassword = CipherUtil.encrypt(user.getPassword());
    		user.setPassword(encPassword);

    		setDefaultIcon(user);

    		UserDao userDao = new UserDao();
    		userDao.insert(connection, user);

    		commit(connection);
    	}catch (RuntimeException e){
    		rollback(connection);
    		throw e;
    	}finally{
    		close(connection;)
    	}
    }

 private void setDefaultIcon(User user){

	 InputStream is = null;
	 try{
		 long randomNum = System.currentTimeMills() % 5;
		 String filePath = "/duke_" + randomNum + ".jpg";
		 is = UserService.class.getResourceAsStream(filePath);
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 StreamUtil.copy(is, baos);
		 user.setIcon(baos.toByteArray());
	 }finally{
		 close(is);
	 }
	 }
 }




