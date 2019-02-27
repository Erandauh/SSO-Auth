package hello;

import hello.sso.CookieUtil;
import hello.sso.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hora on 1/18/19.
 */
@RestController
/*@RequestMapping(path = "/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})*/
public class LoginController {

    private static final String jwtTokenCookieName = "HORA-TOKEN";
    private static final String signingKey = "horaKey";
    private static final Map<String, String> credentials = new HashMap<>();

    public LoginController() {
        credentials.put("username", "hello");
        credentials.put("password", "sso");
    }

    @RequestMapping("/")
    public String home(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(HttpServletResponse httpServletResponse, @RequestBody MultiValueMap<String, String> formData){

        String username = formData.get("username").get(0);
        String password = formData.get("password").get(0);

        if(username == null || !credentials.containsValue(username)
                || !credentials.get("password").equals(password))
            return "login";

        String token = JwtUtil.generateToken(signingKey, username);
        CookieUtil.create(httpServletResponse,jwtTokenCookieName,token,false,60*60,"localhost");

        return "redirect";
    }
}
