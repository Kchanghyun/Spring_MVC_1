package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> param, Map<String, Object> model) {
        String username = param.get("username");
        int age = Integer.parseInt(param.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }
}
