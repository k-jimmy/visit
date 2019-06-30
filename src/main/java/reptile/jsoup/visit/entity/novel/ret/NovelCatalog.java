package reptile.jsoup.visit.entity.novel.ret;

public class NovelCatalog {
    private int catalog_num;
    private String catalog_url;
    private String catalog_name;

    public NovelCatalog() {
    }

    public NovelCatalog(int catalog_num, String catalog_url, String catalog_name) {
        this.catalog_num = catalog_num;
        this.catalog_url = catalog_url;
        this.catalog_name = catalog_name;
    }

    public int getCatalog_num() {
        return catalog_num;
    }

    public void setCatalog_num(int catalog_num) {
        this.catalog_num = catalog_num;
    }

    public String getCatalog_url() {
        return catalog_url;
    }

    public void setCatalog_url(String catalog_url) {
        this.catalog_url = catalog_url;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }

    @Override
    public String toString() {
        return "NovelCatalog{" +
                "catalog_num=" + catalog_num +
                ", catalog_url='" + catalog_url + '\'' +
                ", catalog_name='" + catalog_name + '\'' +
                '}';
    }
}
