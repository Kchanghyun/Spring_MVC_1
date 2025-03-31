package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") // spring 빈의 이름
/**
 * http 요청 시 먼저
 * @RequestMapping으로 정의된 경로 먼저 확인 -> 어댑터 RequestMappingHandlerMapping
 * RequestMapping으로 정의된 경로가 없다면 스프링 빈 이름으로 핸들러를 찾음. -> 어댑터 SimpleControllerHandlerAdapter
 */
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
