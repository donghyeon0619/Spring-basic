package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        특정 파일로 이동 : ctrl + n + 가고싶은 파일 이름
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 스프링은 모두 ApplicationContext에서 시작됨, 스프링 컨테이너라 보면 됨. AppConfig도 스프링 컨테이너에 등록됨
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 첫번째 파라미터는 메소드의 이름(key 값) 이고 두번재 파라미터는 반환타입을 의미함
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
