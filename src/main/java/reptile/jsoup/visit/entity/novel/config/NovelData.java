package reptile.jsoup.visit.entity.novel.config;

public class NovelData {
    private int novel_num;
    private String novel_name;
    private String novel_url;
    private String novel_search_url;
    private String select_name;
    private String select_name_text;
    private String select_name_href;
    private String select_author_text;
    private String select_catalog;
    private String select_catalog_text;
    private String select_catalog_href;
    private String select_content;

    public NovelData() {
    }

    public NovelData(int novel_num, String novel_name, String novel_url, String novel_search_url, String select_name, String select_name_text, String select_name_href, String select_author_text, String select_catalog, String select_catalog_text, String select_catalog_href, String select_content) {
        this.novel_num = novel_num;
        this.novel_name = novel_name;
        this.novel_url = novel_url;
        this.novel_search_url = novel_search_url;
        this.select_name = select_name;
        this.select_name_text = select_name_text;
        this.select_name_href = select_name_href;
        this.select_author_text = select_author_text;
        this.select_catalog = select_catalog;
        this.select_catalog_text = select_catalog_text;
        this.select_catalog_href = select_catalog_href;
        this.select_content = select_content;
    }

    public int getNovel_num() {
        return novel_num;
    }

    public void setNovel_num(int novel_num) {
        this.novel_num = novel_num;
    }

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getNovel_url() {
        return novel_url;
    }

    public void setNovel_url(String novel_url) {
        this.novel_url = novel_url;
    }

    public String getNovel_search_url() {
        return novel_search_url;
    }

    public void setNovel_search_url(String novel_search_url) {
        this.novel_search_url = novel_search_url;
    }

    public String getSelect_name() {
        return select_name;
    }

    public void setSelect_name(String select_name) {
        this.select_name = select_name;
    }

    public String getSelect_name_text() {
        return select_name_text;
    }

    public void setSelect_name_text(String select_name_text) {
        this.select_name_text = select_name_text;
    }

    public String getSelect_name_href() {
        return select_name_href;
    }

    public void setSelect_name_href(String select_name_href) {
        this.select_name_href = select_name_href;
    }

    public String getSelect_author_text() {
        return select_author_text;
    }

    public void setSelect_author_text(String select_author_text) {
        this.select_author_text = select_author_text;
    }

    public String getSelect_catalog() {
        return select_catalog;
    }

    public void setSelect_catalog(String select_catalog) {
        this.select_catalog = select_catalog;
    }

    public String getSelect_catalog_text() {
        return select_catalog_text;
    }

    public void setSelect_catalog_text(String select_catalog_text) {
        this.select_catalog_text = select_catalog_text;
    }

    public String getSelect_catalog_href() {
        return select_catalog_href;
    }

    public void setSelect_catalog_href(String select_catalog_href) {
        this.select_catalog_href = select_catalog_href;
    }

    public String getSelect_content() {
        return select_content;
    }

    public void setSelect_content(String select_content) {
        this.select_content = select_content;
    }

    @Override
    public String toString() {
        return "NovelData{" +
                "novel_num=" + novel_num +
                ", novel_name='" + novel_name + '\'' +
                ", novel_url='" + novel_url + '\'' +
                ", novel_search_url='" + novel_search_url + '\'' +
                ", select_name='" + select_name + '\'' +
                ", select_name_text='" + select_name_text + '\'' +
                ", select_name_href='" + select_name_href + '\'' +
                ", select_author_text='" + select_author_text + '\'' +
                ", select_catalog='" + select_catalog + '\'' +
                ", select_catalog_text='" + select_catalog_text + '\'' +
                ", select_catalog_href='" + select_catalog_href + '\'' +
                ", select_content='" + select_content + '\'' +
                '}';
    }
}
