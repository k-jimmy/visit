package reptile.jsoup.visit.entity.novel.ret;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class NovelName {
    private int novel_num;
    private String novel_name;
    private String novel_img;
    private String novel_author;
    private String novel_intro;
    private String novel_url;

    public NovelName() {
    }

    public NovelName(String novel_name, String novel_img, String novel_author, String novel_intro, String novel_url) {
        this.novel_name = novel_name;
        this.novel_img = novel_img;
        this.novel_author = novel_author;
        this.novel_intro = novel_intro;
        this.novel_url = novel_url;
    }

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getNovel_img() {
        return novel_img;
    }

    public void setNovel_img(String novel_img) {
        this.novel_img = novel_img;
    }

    public String getNovel_author() {
        return novel_author;
    }

    public void setNovel_author(String novel_author) {
        this.novel_author = novel_author;
    }

    public String getNovel_intro() {
        return novel_intro;
    }

    public void setNovel_intro(String novel_intro) {
        this.novel_intro = novel_intro;
    }

    public String getNovel_url() {
        return novel_url;
    }

    public void setNovel_url(String novel_url) {
        this.novel_url = novel_url;
    }

    @Override
    public String toString() {
        return "NovelName{" +
                "novel_name='" + novel_name + '\'' +
                ", novel_img='" + novel_img + '\'' +
                ", novel_author='" + novel_author + '\'' +
                ", novel_intro='" + novel_intro + '\'' +
                ", novel_url='" + novel_url + '\'' +
                '}';
    }
}
