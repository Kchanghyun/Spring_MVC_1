package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
//    WebServlet 으로 여기에서 각 controller로 보내줘야 될 거 같은데.
//    그럼 여기서 이제 어떻게 해야 넘어가는거지?
}
