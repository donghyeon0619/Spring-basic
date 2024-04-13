package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
//      ApplicationContext 클래스로 하면 close()함수를 사용 못함
//      왜냐하면 부모에서 자식에 있는 함수를 사용 못하므로 AnnotationConfig~~나 Configurable~를 사용해야함
//      ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

//        @Bean(initMethod = "init", destroyMethod = "close")     //destroyMethod는 default가 (inferred) 이므로 생략해도됨
//        ctrl + b : go to Declaration or Usages
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
