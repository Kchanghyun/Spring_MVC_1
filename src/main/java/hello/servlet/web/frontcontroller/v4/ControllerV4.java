package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param param
     * @param model
     * @return viewName
     */
    String process(Map<String, String> param, Map<String, Object> model);
}
