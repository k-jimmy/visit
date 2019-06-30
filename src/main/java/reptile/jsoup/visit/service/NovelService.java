package reptile.jsoup.visit.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import reptile.jsoup.visit.entity.novel.ret.NovelCatalog;
import reptile.jsoup.visit.entity.novel.ret.NovelName;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NovelService extends BaseService {
//    private NovelData novelData = NovelInitialization.novelDatas[dataIndex];

    /**
     * 小说搜索,默认起点
     *
     * @param novelName
     * @return
     */
    public String searchNovelName(String novelName) {
        String result = null;
        List<NovelName> novelNames = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.qidian.com/search?kw=" + novelName).get();
            Elements elements = doc.select("#result-list > div > ul > li");
            for (Element ele : elements) {
                String novel_name = ele.select(".book-mid-info > h4 > a").text();
                String novel_img = ele.select("div.book-img-box > a > img").attr("src");
                String novel_author = ele.select("div.book-mid-info > p.author > a.name").text();
                String novel_intro = ele.select("div.book-mid-info > p.intro").text();
                String novel_url = ele.select("div.book-mid-info > h4 > a").attr("href");
                NovelName ref_novel_name = new NovelName(novel_name, novel_img, novel_author, novel_intro, novel_url);
                novelNames.add(ref_novel_name);
            }
            result = gson.toJson(novelNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 根据名称搜索小说目录链接
     *
     * @param
     * @return
     */
    private String getNovelUrl(String novelName, String novelAuthor, int dataIndex) {
        String url = novelDatas[dataIndex].getNovel_search_url() + novelName;
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements elements = doc.select(novelDatas[dataIndex].getSelect_name());
            for (Element ele : elements) {
                String novel_name = ele.select(novelDatas[dataIndex].getSelect_name_text()).text();
                String novel_author = ele.select(novelDatas[dataIndex].getSelect_author_text()).text();
                if (novelName.equals(novel_name) && novelAuthor.equals(novel_author)) {
                    return ele.select(novelDatas[dataIndex].getSelect_name_href()).attr("href");
                }
            }
        } catch (IOException e) {
            getByDisableCertValidation(url);
            getNovelUrl(novelName, novelAuthor, dataIndex);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 搜索目录
     * 用户在当前小说界面查看目录,则需要网站下标取对应的目录
     * 搜索不到对应的资源,则遍历novel-data资源
     *
     * @param novelName   书名
     * @param novelAuthor 作者
     * @param dataIndex   网站下标
     * @return
     */
    public String novelCatalog(String novelName, String novelAuthor, int dataIndex) {
        //从第一章开始爬取,排除第一章之前的不相关内容
        int index = 0;
        List<NovelCatalog> novelCatalogs = new ArrayList<>();
        String url = null;
        if (dataIndex > -1) {
            url = getNovelUrl(novelName, novelAuthor, dataIndex);
        } else {
            for (int i = 0; i < novelDatas.length; i++) {
                url = getNovelUrl(novelName, novelAuthor, i);
                if (url != null) {
                    dataIndex = i;
                    break;
                }
            }
        }
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(novelDatas[dataIndex].getSelect_catalog());
            for (Element ele : elements) {
                String catalog_url;
                String catalog_name = ele.select(novelDatas[dataIndex].getSelect_catalog_text()).text();
                String sub_name = null;
                if (catalog_name.length() > 3) {
                    sub_name = catalog_name.substring(0, 3);
                }
                if ("第一章".equals(sub_name) || "第1章".equals(sub_name) || index > 0) {
                    catalog_url = ele.select(novelDatas[dataIndex].getSelect_catalog_href()).attr("href");
                    NovelCatalog ref_novel_catalog = new NovelCatalog(novelDatas[dataIndex].getNovel_num(), catalog_name, catalog_url);
                    index = 1;
                    novelCatalogs.add(ref_novel_catalog);
                }
            }
            return gson.toJson(novelCatalogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 搜索内容
     *
     * @param url       网站内容url的后缀
     * @param dataIndex 网站下标,取网站根地址,与后缀拼接成完整网址
     * @return
     */
    public String novelContent(String url, int dataIndex) {
        String content = null;
        try {
            Document doc = Jsoup.connect(novelDatas[dataIndex].getNovel_url() + url).get();
            content = doc.select(novelDatas[dataIndex].getSelect_content()).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 注册SSL
     *
     * @param url
     * @return
     */
    public static InputStream getByDisableCertValidation(String url) {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            URL uRL = new URL(url);
            HttpsURLConnection urlConnection = (HttpsURLConnection) uRL.openConnection();
            InputStream is = urlConnection.getInputStream();
            return is;
        } catch (Exception e) {
        }
        return null;
    }
}
