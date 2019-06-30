package reptile.jsoup.visit.service;

import com.google.gson.Gson;
import reptile.jsoup.visit.entity.UserAgent;
import reptile.jsoup.visit.entity.novel.config.NovelData;
import reptile.jsoup.visit.entity.ProxyIPEntity;

import java.util.ArrayList;
import java.util.List;

//@Service
public class BaseService {
    Gson gson = new Gson();
    public static NovelData[] novelDatas = null;
    public static UserAgent[] userAgent = null;
    public static List<ProxyIPEntity> proxyIPS = new ArrayList<>();

}
