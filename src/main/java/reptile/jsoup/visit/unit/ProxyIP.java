package reptile.jsoup.visit.unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import reptile.jsoup.visit.entity.ProxyIPEntity;
import reptile.jsoup.visit.entity.UserAgent;
import reptile.jsoup.visit.entity.novel.config.NovelData;
import reptile.jsoup.visit.service.BaseService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 自动爬取代理IP地址
 */
public class ProxyIP {
//    private static String xilaIP = "http://www.xiladaili.com/api/?uuid=c7b9a3e091e9470e9043678469eb0d0f&category=1&repeat=1&speed=1.5&sortby=3&num=500&format=1";

    /**
     * IP代理池定时任务,每60秒执行一次
     * 取前1000页数据
     */
    public static void timeIPTask() {
        final long timeInterval = 60 * 1000;
        Runnable runnable = new Runnable() {
            int i = 1;
            public void run() {
                while (true) {
                    reptileIP(i);
                    try {
                        Thread.sleep(timeInterval);
                        i++;
                        if (i == 1001) {
                            i = 1;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * IP代理池爬取
     *
     * @param i
     */
    public static void reptileIP(int i) {
        String url = "http://www.xiladaili.com/gaoni/";
        Connection conn;
        Document doc;

        try {
            conn = Jsoup.connect(url + i + "/");
            conn = setHeader(conn);
            conn = setProxyIP(conn);
            doc = conn.timeout(5000).get();
            Elements elements = doc.select("body > div > div.container.mt-4 > div.mt-0.mb-2.table-responsive > table > tbody > tr");
            for (Element ele : elements) {
                double speed = Double.parseDouble(ele.select("td:nth-child(5)").text());
                int score = Integer.parseInt(ele.select("td:nth-child(8)").text());
                String survivalTime = ele.select("td:nth-child(6)").text();
                if (speed > 3) {
                    continue;
                }
                if (score < 10000) {
                    continue;
                }
                if (!survivalTime.contains("天")) {
                    continue;
                }
                String ip_port = ele.select("td:nth-child(1)").text();
                String agreement = ele.select("td:nth-child(2)").text();
                String privacy = ele.select("td:nth-child(3)").text();
                String position = ele.select("td:nth-child(4)").text();
                String ip = ip_port.split(":")[0];
                int port = Integer.parseInt(ip_port.split(":")[1]);
                ProxyIPEntity ipEntity = new ProxyIPEntity(ip, port, agreement, privacy, position, speed, survivalTime, score);
                System.out.println(ipEntity);
                BaseService.proxyIPS.add(ipEntity);
            }
//            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(BaseService.proxyIPS.size());
//            if (proxyIP != null) {
//                System.out.println("当前页:" + i + ";当前IP:" + proxyIP.getIP() + ";当前端口:" + proxyIP.getPort());
//            }
//            reptileIP(i);
        }
    }

    /**
     * 设置代理IP
     *
     * @param conn
     * @return
     */
    public static Connection setProxyIP(Connection conn) {
        ProxyIPEntity proxyIP = null;
        if (BaseService.proxyIPS.size() > 0) {
            proxyIP = getIP();
            conn.proxy(proxyIP.getIP(), proxyIP.getPort());
        }
        return conn;
    }

    /**
     * 设置请求头
     *
     * @param conn
     * @return
     */
    public static Connection setHeader(Connection conn) {
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.header("Accept-Encoding", "gzip, deflate, sdch");
        conn.header("Accept-Language", "zh-CN,zh;q=0.8");
        if (BaseService.userAgent != null) {
            System.out.println(getUserAgent());
            conn.header("User-Agent", getUserAgent());
        }
        return conn;
    }

    /**
     * 随机获取userAgent
     *
     * @return
     */
    public static String getUserAgent() {
        int i = (int) (Math.random() * BaseService.userAgent.length);
        return BaseService.userAgent[i].getUa();
    }

    /**
     * 随机获取IP地址
     *
     * @return
     */
    public static ProxyIPEntity getIP() {
        int i = (int) (Math.random() * BaseService.proxyIPS.size());
        return BaseService.proxyIPS.get(i);
    }

    /**
     * 初始化userAgent
     */
    public static void initUserAgent() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\yanan\\IdeaProjects\\visit\\src\\main\\resources\\userAgent.json"));
            Gson gson = new GsonBuilder().create();
            BaseService.userAgent = gson.fromJson(reader, UserAgent[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
