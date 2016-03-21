package honeycomb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value="/sign_in", method=RequestMethod.GET)
    public String Sign_in(Model model) {
        return "html/sign_in";
    }
    
    @RequestMapping(value="/sign_up", method=RequestMethod.GET)
    public String sign_up(Model model) {
        return "html/sign_up";
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
