package com.example.demo.myUtile.Security;

import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter // call it one time per request
{

    private String TOKEN_HEADER = "Authorization";

    @Autowired
    private TokenUtilty tokenUtilty;

    @Autowired
    private UserService userService;

    // this filer as middleware in laravel
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        // get token from header
        // make sure is valid


        String header = request.getHeader(TOKEN_HEADER);

        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (header != null && securityContext.getAuthentication() == null)
        {
            String token = header.substring("Bearer ".length());
            String username  = tokenUtilty.getUsernameFromToken(token);
            if (username != null)
            {
                UserDetails userDetails = userService.loadUserByUsername(username);

                if (tokenUtilty.isTokenVaild(token,userDetails))
                {
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }


        filterChain.doFilter(request,response);
    }
}
