package filterDemo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GzipResponseServlet", value = "/GzipResponseServlet")
public class GzipResponseServlet extends HttpServlet {

    private String contentStr = "jekfjdlfgjlfdjgkldfjglkdfjklgjdflkjglkdfjgjdflgjlfdjglkfjdlgjldkfjgkldfjgkldfjglkjfdgldjfgjdflgjdjekfjdlfgjlfdjgkldfjglkdfjklgjdflkjglkdfjgjdflgjlfdjglkfjdlgjldkfjgkldfjgkldfjglkjfdgldjfgjdflgjdjekfjdlfgjlfdjgkldfjglkdfjklgjdflkjglkdfjgjdflgjlfdjglkfjdlgjldkfjgkldfjgkldfjglkjfdgldjfgjdflgjdjekfjdlfgjlfdjgkldfjglkdfjklgjdflkjglkdfjgjdflgjlfdjglkfjdlgjldkfjgkldfjgkldfjglkjfdgldjfgjdflgjdjekfjdlfgjlfdjgkldfjglkdfjklgjdflkjglkdfjgjdflgjlfdjglkfjdlgjldkfjgkldfjgkldfjglkjfdgldjfgjdflgjd";

    /**
     * 获取到的应该是Content-Length=75，并且Content-Encoding=Gzip的返回值
     * curl -X GET \
     *   http://localhost:8080/javaWebDemo_war_exploded/GzipResponseServlet \
     *   -H 'Content-Type: application/x-www-form-urlencoded' \
     *   -H 'Postman-Token: b62787be-ad5d-4dcd-a331-e9287025cfc9' \
     *   -H 'cache-control: no-cache'
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().write(contentStr.getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
