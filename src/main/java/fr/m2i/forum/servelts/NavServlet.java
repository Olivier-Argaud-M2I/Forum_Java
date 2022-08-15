package fr.m2i.forum.servelts;

import fr.m2i.forum.cruds.TopicCrud;
import fr.m2i.forum.cruds.UserCrud;
import fr.m2i.forum.models.Topic;
import fr.m2i.forum.models.TopicResponse;
import fr.m2i.forum.models.User;
import org.apache.cxf.wsdl.http.UrlEncoded;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;

@WebServlet(name = "NavServlet", value = {"/","/home","/about","/contact","/login","/user","/topic","/topicDetail"})
public class NavServlet extends HttpServlet {

    private static final String PAGE_HOME = "/index.jsp";
    private static final String PAGE_ABOUT = "/WEB-INF/pages/pageAbout.jsp";
    private static final String PAGE_CONTACT = "/WEB-INF/pages/pageContact.jsp";
    private static final String PAGE_LOGIN = "/WEB-INF/pages/pageLogin.jsp";
    private static final String PAGE_TOPIC= "/WEB-INF/pages/pageTopicDetail.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("page")!=null) {
            switch (request.getParameter("page")) {
                case "user":
                case "home": {
                    TopicCrud topicCrud  = new TopicCrud();
                    request.setAttribute("topics", topicCrud.getTopics());
                    this.getServletContext().getRequestDispatcher(PAGE_HOME).forward(request, response);
                    break;
                }
                case "topicDetail": {
                    TopicCrud topicCrud = new TopicCrud();
                    Topic topic = topicCrud.getTopicById(Integer.valueOf(request.getParameter("id")));
                    request.setAttribute("topic",topic);
                    this.getServletContext().getRequestDispatcher(PAGE_TOPIC).forward(request, response);
                    break;
                }
                case "about": {
                    this.getServletContext().getRequestDispatcher(PAGE_ABOUT).forward(request, response);
                    break;
                }
                case "contact": {
                    this.getServletContext().getRequestDispatcher(PAGE_CONTACT).forward(request, response);
                    break;
                }
                case "login": {

                }
                default: {
                    this.getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
                }
            }
        }
        else{
            response.sendRedirect(request.getContextPath() + "/home?page=home");
        }

        System.out.println("find e la method doget");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("log")!=null) {
            if(request.getParameter("log").equals("out")){
                request.getSession().removeAttribute("userIsLogged");
            }
        }

        if(request.getParameter("topic")!=null) {

            switch (request.getParameter("topic")){

                case "addTopic":{
                    UserCrud userCrud = new UserCrud();
                    TopicCrud topicCrud = new TopicCrud();
                    Topic topic = new Topic(
                            request.getParameter("title"),
                            request.getParameter("text"),
                            (User)request.getSession().getAttribute("userIsLogged"),
                            Calendar.getInstance().getTimeInMillis()
                            );
                    topicCrud.saveTopic(topic);
                    break;
                }

                case "addResponse":{
                    TopicCrud topicCrud = new TopicCrud();
                    Topic topic = topicCrud.getTopicById(Integer.valueOf(request.getParameter("id")));
                    TopicResponse topicResponse = new TopicResponse(
                            request.getParameter("text"),
                            (User)request.getSession().getAttribute("userIsLogged"),
                            Calendar.getInstance().getTimeInMillis(),
                            topic
                    );
//                    topic.getResponses().add(topicResponse);
                    topicCrud.saveTopicResponse(topicResponse);
                    String url  = request.getContextPath() + "/topicDetail?page=topicDetail&id="+topic.getId();
                    response.sendRedirect(url);
                    return;

                }

                case "deleteTopic":{
                    TopicCrud topicCrud = new TopicCrud();
                    topicCrud.deleteTopicById(Integer.valueOf(request.getParameter("id")));
                    break;
                }

                case "deleteResponse":{
                    TopicCrud topicCrud = new TopicCrud();
                    TopicResponse topicResponse = topicCrud.getTopicResponseById(Integer.valueOf(request.getParameter("id")));
                    Integer topicId = topicResponse.getTopic().getId();
                    topicCrud.deleteTopicResponseById(Integer.valueOf(request.getParameter("id")));
                    String url  = request.getContextPath() + "/topicDetail?page=topicDetail&id="+topicId;
                    response.sendRedirect(url);
                    return;
                }



            }

        }





//        response.sendRedirect(request.getContextPath() + "/home?page=home");


        doGet(request, response);

        System.out.println("find e la method dopost");
    }
}
