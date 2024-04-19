package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)       /// 값이 하나면 value값을 빼도되지만 만약 들어가는 파라미터가 여러개라면 value를 넣어줘야 함
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestUrl(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean create" + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("[" + uuid + "]" + "request scope bean close" + this);
    }

}
