package org.neuedu.webpractice.filter;

        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebFilter(filterName = "FilterA_CrossOrigin",value="/*")
public class FilterA_CrossOrigin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}