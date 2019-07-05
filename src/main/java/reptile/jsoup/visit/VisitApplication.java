package reptile.jsoup.visit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reptile.jsoup.visit.unit.NovelInitialization;
import reptile.jsoup.visit.unit.ProxyIP;

@SpringBootApplication
public class VisitApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisitApplication.class, args);
        //初始化小说源
        NovelInitialization.initNovelData();
        //初始化请求头
        ProxyIP.initUserAgent();
        //初始化IP代理池
        ProxyIP.timeIPTask();
    }

}
