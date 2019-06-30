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
    private static String xilaIP = "http://www.xiladaili.com/api/?uuid=c7b9a3e091e9470e9043678469eb0d0f&category=1&repeat=1&speed=1.5&sortby=3&num=500&format=1";

    public static void xilaIP() {
        try {
            Connection conn = Jsoup.connect(xilaIP);
            conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.header("Accept-Encoding", "gzip, deflate, sdch");
            conn.header("Accept-Language", "zh-CN,zh;q=0.8");
            if (BaseService.userAgent != null) {
                System.out.println(getUserAgent());
                conn.header("User-Agent", getUserAgent());
            }
            String ips = String.valueOf(conn.get());
            System.out.println(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reptileIP(int i) {
        String url = "http://www.xiladaili.com/gaoni/";
        Connection conn;
        Document doc;
        ProxyIPEntity proxyIP = null;
        try {
            for (; i < 2000; i++) {
                System.out.println(BaseService.userAgent[i]);
                if (BaseService.proxyIPS != null && BaseService.proxyIPS.size() > 200) {
                    break;
                }
                conn = Jsoup.connect(url + i + "/");
                conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                conn.header("Accept-Encoding", "gzip, deflate, sdch");
                conn.header("Accept-Language", "zh-CN,zh;q=0.8");
                if (BaseService.userAgent != null) {
                    System.out.println(getUserAgent());
                    conn.header("User-Agent", getUserAgent());
                }
                if (BaseService.proxyIPS.size() > 0) {
                    proxyIP = getIP();
                    conn.proxy(proxyIP.getIP(), proxyIP.getPort());
                }
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
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(BaseService.proxyIPS.size());
            if (proxyIP != null) {
                System.out.println("当前页:" + i + ";当前IP:" + proxyIP.getIP() + ";当前端口:" + proxyIP.getPort());
            }
            reptileIP(i);
        }
    }

    public static String getUserAgent() {
        int i = (int) (Math.random() * BaseService.userAgent.length);
        return BaseService.userAgent[i].getUa();
    }

    public static ProxyIPEntity getIP() {
        int i = (int) (Math.random() * BaseService.proxyIPS.size());
        return BaseService.proxyIPS.get(i);
    }

    public static void initUserAgent() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("F:\\下载\\gecco-master\\visit\\src\\main\\resources\\userAgent.json"));
            Gson gson = new GsonBuilder().create();
            BaseService.userAgent = gson.fromJson(reader, UserAgent[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
