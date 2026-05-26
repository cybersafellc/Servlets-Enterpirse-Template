package org.nands.app.filters;

import jakarta.servlet.*;

import java.io.IOException;

public class DummyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("hello world saya di akses");
        chain.doFilter(request, response);
    }
}
