package fr.m2i.forum.filters;

import fr.m2i.forum.cruds.UserCrud;
import fr.m2i.forum.models.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", value ={"/user"})
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        User user = null;
        User user1 = null;
        if(request.getParameter("username")!=null&&request.getParameter("password")!=null){
            user = new User( request.getParameter("username"),request.getParameter("password"));
            UserCrud userCrud = new UserCrud();
            user1 = userCrud.getUserByName(user.getUserName());
        }

        if(user1!=null && user !=null && user1.getPassword().equals(user.getPassword())) {
            ((HttpServletRequest) request).getSession().setAttribute("userIsLogged" , user1);

        }

        if(((HttpServletRequest) request).getSession().getAttribute("userIsLogged")!=null){
            chain.doFilter(request,response);
        }
        else{
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(((HttpServletRequest)request).getContextPath() +"/login?page=login");
        }
    }
}
