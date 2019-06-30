package reptile.jsoup.visit.entity;

public class UserAgent {
    private String ua;

    public UserAgent() {
    }

    public UserAgent(String ua) {
        this.ua = ua;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "ua='" + ua + '\'' +
                '}';
    }
}
