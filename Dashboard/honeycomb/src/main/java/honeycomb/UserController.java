package honeycomb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class UserController {
	
	private Boolean CheckUserNameAndPassword(String userName,String passWord){
		String dbUrl = "jdbc:mysql://localhost:3306/honeycomb";
        String dbClass = "com.mysql.jdbc.Driver";
        String query = "Select * from user where email = " + "\"" + userName +"\"" + " and password = " + passWord;
        String username = "root";
        String password = "";
        try {
            Class.forName(dbClass);
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()){
            	return false;
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return true;
	}
	private Boolean ChecekNewPassword(String passWord,String passwordConfirmation){
		if(!passWord.equals(passwordConfirmation)){
			return false;
		}
		return true;
	}
	private void UserNameAndPassword(String userName,String passWord){
		String dbUrl = "jdbc:mysql://localhost:3306/honeycomb";
        String dbClass = "com.mysql.jdbc.Driver";
		String query = "insert into user (email, password) values (" + "\"" + userName +"\"" +"," + passWord+")";
		String username = "root";
        String password = "";
        try {
            Class.forName(dbClass);
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    @RequestMapping(value="/sign_in", method=RequestMethod.GET)
    public String Sign_in(Model model) {
    	model.addAttribute("user", new User());
        return "html/sign_in";
    }
    
    @RequestMapping(value="/sign_in", method=RequestMethod.POST)
    public String signInSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        //System.out.print(user.getEmail());
        //System.out.print(user.getPassword());
        Boolean succeed = CheckUserNameAndPassword(user.getEmail(), user.getPassword());
        //System.out.println(succeed);
        if (succeed) {
        	return "redirect:/index"; 
		}else {
			return "html/sign_in";
		}       
    }
        
    @RequestMapping(value="/sign_up", method=RequestMethod.GET)
    public String sign_up(Model model) {
    	model.addAttribute("user", new User());
        return "html/sign_up";
    }
    
    @RequestMapping(value="/sign_up", method=RequestMethod.POST)
    public String signUpSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        //System.out.print(user.getEmail());
        //System.out.print(user.getPassword());
        //System.out.print(user.getPasswordConfirmation());
        Boolean succeed = ChecekNewPassword(user.getPassword(), user.getPasswordConfirmation());
        //System.out.println(succeed);
        if (succeed) {
        	UserNameAndPassword(user.getEmail(), user.getPassword());
        	return "redirect:/sign_in"; 
		}else {
			return "html/sign_up";
		}       
    }
    
    @RequestMapping(value="/forgot_password", method=RequestMethod.GET)
    public String forgot_password(Model model) {
        return "html/forgot_password";
    }
    
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(Model model) {
        return "html/index";
    }
}
