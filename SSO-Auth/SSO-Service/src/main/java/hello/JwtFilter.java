package hello;

import hello.sso.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hora on 2/26/19.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String jwtTokenCookieName = "HORA-TOKEN";
    private static final String signingKey = "horaKey";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String username = JwtUtil.getSubject(httpServletRequest, jwtTokenCookieName, signingKey);
        if(username == null){
            String authService = this.getFilterConfig().getInitParameter("services.auth");
            httpServletResponse.sendRedirect(authService + "?redirect=" + httpServletRequest.getRequestURL());
        } else{
            httpServletRequest.setAttribute("username", username);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
