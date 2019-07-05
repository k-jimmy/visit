package reptile.jsoup.visit.unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import reptile.jsoup.visit.entity.novel.config.NovelData;
import reptile.jsoup.visit.service.BaseService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class NovelInitialization {
//    public static NovelData[] novelDatas = null;

    public static void initNovelData() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\yanan\\IdeaProjects\\visit\\src\\main\\resources\\novel\\novel-data.json"));
            Gson gson = new GsonBuilder().create();
            BaseService.novelDatas = gson.fromJson(reader, NovelData[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
