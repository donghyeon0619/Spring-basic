package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.event.annotation.AfterTestClass;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        assertThat(clientBean1.logic()).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        assertThat(clientBean2.logic()).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{

        /*
        @Autowired
        private ObjectProvider<PrototypeBean> objectProvider;  //생성 시점에 주입됨
        */

        @Autowired
        private Provider<PrototypeBean> objectProvider;     // 스프링부트3 부터 javax.inject가 아닌 jakarta.inject를 사용

        public int logic(){
            /*
            getObject()에 매개변수에는 아무것도 안들어가도됨 왜냐하면 처음 ObjectProvider를 생성할 때
            그 클래스에 대한 빈을 만들어주는 것으로 설정하고 만들었으므로
            자바에서는 for문에 사용하는 라벨 느낌인듯 es a:for
            */

//          PrototypeBean prototypeBean = objectProvider.getObject(); ObjectProvider를 사용할 때 사용
            PrototypeBean prototypeBean = objectProvider.get();

            prototypeBean.addCount();

//          int count = prototypeBean.getCount();     둘이 합치는 단축키 ctrl + alt + n
//          return count;
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
         private int count = 0;

         public void addCount(){
             count++;
         }

         public int getCount(){
             return count;
         }

         @PostConstruct
         public void init(){
             System.out.println("PrototypeBean.init = " + this);
         }

         @PreDestroy
         public void destroy(){
             System.out.println("PrototypeBean.destroy");
         }
    }
}
