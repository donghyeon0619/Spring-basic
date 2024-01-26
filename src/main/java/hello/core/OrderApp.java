package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
애플리케이션 로직으로 이렇게 테스트를 진행하는 것은 좋은 방법이 아니므로 Junit 테스트를 이용하는 것이 좋음
 */
public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        // 바로 전에 작업했던 파일로 되돌아가기 단축키 : ctrl + e + enter
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId =1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
