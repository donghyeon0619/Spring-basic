package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

    private final int discountPercent = 10; //10퍼 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){     // ENUM은 정수 값이므로 ==연산자를 사용해야함
            return price * discountPercent / 100;
        }
        return 0;
    }

}
