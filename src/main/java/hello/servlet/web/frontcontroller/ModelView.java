package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
// 컨트롤러가 반환하는 데이터와 뷰 이름을 담는 객체
// 컨트롤러가 클라이언트 요청을 처리한 후, 뷰 이름과 모델 데이터를 담아서 반환하는 객체
// model은 뷰에서 사용할 데이터를 담아 전달하는 역할. ex. ("member", member)
/*
 * 컨트롤러는 request, response를 몰라도 됨 -> SRP(Single Responsibility Principle) 강화
 * MyView가 request.setAttribute()를 처리 -> 컨트롤러가 JSP 렌더링을 신경 쓸 필요가 없음.
 * => ModelView를 도입하면서 request, response를 직접 전달할 필요 없이,
 * 컨트롤러는 "뷰 이름 + 모델 데이터"만 반환하면 되고,
 * MyView가 최종 렌더링을 담당하게 됨.
 */
public class ModelView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
