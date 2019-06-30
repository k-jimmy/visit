package reptile.jsoup.visit.entity;

public class ProxyIPEntity {
    private String IP;
    private int port;
    private String agreement;
    private String privacy;
    private String position;
    private double speed;
    private String survivalTime;
    private int score;

    public ProxyIPEntity() {
    }

    public ProxyIPEntity(String IP, int port, String agreement, String privacy, String position, double speed, String survivalTime, int score) {
        this.IP = IP;
        this.port = port;
        this.agreement = agreement;
        this.privacy = privacy;
        this.position = position;
        this.speed = speed;
        this.survivalTime = survivalTime;
        this.score = score;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getagreement() {
        return agreement;
    }

    public void setagreement(String agreement) {
        this.agreement = agreement;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(String survivalTime) {
        this.survivalTime = survivalTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ProxyIPEntity{" +
                "IP='" + IP + '\'' +
                ", port='" + port + '\'' +
                ", agreement='" + agreement + '\'' +
                ", privacy='" + privacy + '\'' +
                ", position='" + position + '\'' +
                ", speed=" + speed +
                ", survivalTime='" + survivalTime + '\'' +
                ", score=" + score +
                '}';
    }
}
