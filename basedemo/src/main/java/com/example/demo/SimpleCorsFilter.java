package com.example.demo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
//middeware
	

	  public SimpleCorsFilter() {
	   }
	  
	    @Override
	    public void init(FilterConfig filterConfig) {
	    }

	    @Override
	    public void destroy() {
	    }

	    @Override
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	 
	    	HttpServletResponse response = (HttpServletResponse) res;
	        HttpServletRequest request = (HttpServletRequest) req;
	        //set header cua response
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type");


	        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	            response.setStatus(HttpServletResponse.SC_OK);
	        } else {
	            chain.doFilter(req, res);
	        }
	      
	      
	    }
}