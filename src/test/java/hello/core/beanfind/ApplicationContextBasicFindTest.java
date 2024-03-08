package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 특정 test만 실행 ctrl + shift + F10
    @Test
    @DisplayName("이름 없이 타임만으로 조회")
    void findByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // 구체 타입으로 조회하는 것은 역할과 구현을 분리해야하는데 구현에 의존하게 되므로 별로 좋은 방법은 아님
    // 변경시 유연성이 많이 떨어짐
    @Test
    @DisplayName("구체 타입으로 조회")
    void findByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    //assrtThrows는 junit에서 기본적으로 제공해주는 메소드
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findByNameX(){
        //ac.getBean("xxxxx",MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxxxx",MemberService.class));
    }

}
