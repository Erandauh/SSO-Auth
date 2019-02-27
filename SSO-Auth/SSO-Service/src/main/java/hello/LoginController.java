package hello;

import hello.sso.CookieUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hora on 1/18/19.
 */
@RestController
public class LoginController {

    private static final String jwtTokenCookieName = "HORA-TOKEN";

    @RequestMapping("/")
    public String home(){
        return "redirect:/another-login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest httpServletRequest){

        return "another-login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String login(HttpServletResponse httpServletResponse){

        CookieUtil.clear(httpServletResponse,jwtTokenCookieName);

        return "redirect";
    }
}
