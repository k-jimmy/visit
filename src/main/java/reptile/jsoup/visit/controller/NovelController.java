package reptile.jsoup.visit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reptile.jsoup.visit.service.NovelService;

import javax.annotation.Resource;

@Controller
public class NovelController {
    @Resource
    private NovelService novelService;

    @ResponseBody
    @RequestMapping("/novelName")
    public String novelName(String novelName) {
        return novelService.searchNovelName(novelName);
    }

    @ResponseBody
    @RequestMapping("/novelCatalog")
    public String novelCatalog(String novelName, String novelAuthor, int dataIndex) {
        return novelService.novelCatalog(novelName, novelAuthor, dataIndex);
    }

    @ResponseBody
    @RequestMapping("/novelContent")
    public String novelContent(String url, int dataIndex) {
        return novelService.novelContent(url,dataIndex);
    }
}
