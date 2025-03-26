package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청을 처리할 핸들러를 찾음 (HandlerMapping 사용)
        // 처리할 수 있는 어댑터 반환 Object 타입
        // ex handler = MemberFormControllerV3
        Object handler = getHandler(request);
        if (handler == null) {
            System.out.println("FrontControllerServletV5.service");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 핸들러 어댑터 목록 조회
        // 핸들러 어댑터
        // adapter = MemberFormControllerV3 지원하는 ControllerV3HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 핸들러 실행 후 ModelView 반환
        // ControllerV3HandlerAdapter.handle -> (ControllerV3)handler 명식적 형변환,
        // handle에서 process를 통해 얻은 ModelView mv 인스턴스 반환
        ModelView mv = adapter.handle(request, response, handler);

        // viewName = "new-form"
        String viewName = mv.getViewName();
        // 뷰 이름을 기반으로 MyView 객체 생성
        // MyView("/WEB-INF/views/" + viewName + ".jsp")
        // MyView 생성자 -> viewPath
        MyView myView = viewResolver(viewName);
        // 모델 데이터를 바탕으로 뷰 렌더링 및 HTML 응답
        // 예시인 MemberFormControllerV3는 getModel().put을 사용할 일이 없음. (뷰 템플릿 쪽에서 사용할 객체가 없기 때문)

        // MemberSaveControllerV3는 getModel().put("member", member)를 통해 ModelView의 model에 값을 넣음.
        // 그로 인해 render에서 modelToRequestAtrribute(model, request); 메서드를 통해 request.setAttribute()를 함.
        myView.render(mv.getModel(), request, response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        // handler = MemberFormControllerV3
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
