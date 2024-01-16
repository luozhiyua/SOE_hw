package org.example;
import javax.jws.WebService;
@WebService(endpointInterface = "org.example.LoginService")
public class LoginServiceImpl implements LoginService{
    @Override
    public String login(String email, String password) {
        if (email.endsWith("@nju.teacher.edu.cn")) {
            if (password.equals("teacher123")) {
                return "Welcome, Teacher!";
            } else {
                return "Invalid password!";
            }
        } else if (email.endsWith("@nju.postgrad.edu.cn")) {
            if (password.equals("postgrad123")) {
                return "Welcome, Postgraduate!";
            } else {
                return "Invalid password!";
            }
        } else if (email.endsWith("@nju.undergrad.edu.cn")) {
            if (password.equals("undergrad123")) {
                return "Welcome, Undergraduate!";
            } else {
                return "Invalid password!";
            }
        } else {
            return "Invalid email address!";
        }
    }
}
