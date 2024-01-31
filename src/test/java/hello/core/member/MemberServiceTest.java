package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    /**
     * @SpringBootTest로 테스트를 하면 스프링을 띄어야 하므로 더 오래 걸릴 수 있음
     * 그러므로 @Test를 이용해사 단위 테스트를 하는 것이 빠르게 테스트를 할 수 있음
     */

    @Test
    void join(){
        //given
        Member member = new Member(1l, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
