package com.example.Distributed.Application.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
@Component
public class ApiKeyFilter extends GenericFilterBean {

@Value("${api.key}")
 private String apiKey;


 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
         throws IOException, ServletException {
             
     HttpServletRequest httpRequest = (HttpServletRequest) request;
     HttpServletResponse httpResponse = (HttpServletResponse) response;
 
     // Allow CORS for all requests
     httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
     httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
     httpResponse.setHeader("Access-Control-Allow-Headers", "*");
     httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
 
     if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
         httpResponse.setStatus(HttpServletResponse.SC_OK);
         return;
     }
 
     String path = httpRequest.getRequestURI();
     if (path.startsWith("/saas")) {
         String requestApiKey = httpRequest.getHeader("X-API-KEY");
 
         if (requestApiKey == null || !apiKey.equals(requestApiKey)) {
             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             httpResponse.getWriter().write("Unauthorized");
             return;
         }
     }
 
     chain.doFilter(request, response);
 }
 
}