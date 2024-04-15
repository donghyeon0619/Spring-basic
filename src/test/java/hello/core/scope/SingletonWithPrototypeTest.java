package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

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
        assertThat(clientBean2.logic()).isEqualTo(2);

    }
    @Scope("singleton")
    static class ClientBean{
        private final PrototypeBean prototypeBean;  //생성 시점에 주입됨

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            prototypeBean.addCount();
//            int count = prototypeBean.getCount();     둘이 합치는 단축키 ctrl + alt + n
//            return count;
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
