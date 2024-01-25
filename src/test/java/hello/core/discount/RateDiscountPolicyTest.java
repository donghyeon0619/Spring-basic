package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy DiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member member = new Member(1L, "MemberVIP", Grade.VIP);
        //when
        int discount = DiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    //테스트를 할 때는 아닌 경우도 봐야함
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않는다")
    void vip_x(){
        //given
        Member member = new Member(1L, "MemberBasic", Grade.BASIC);
        //when
        int discount = DiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}