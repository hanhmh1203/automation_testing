package readfile;

public class LatLonEntity {
    String lat;
    String lon;
    int timeDelay;
//    LEVEL0(2, 24), LEVEL1(10, 60), LEVEL2(50, 90), NONE(Float.MAX_VALUE, 120);

    public LatLonEntity(String lat, String lon, int timeDelay) {
        this.lat = lat;
        this.lon = lon;
        this.timeDelay = timeDelay;
    }

    public long getTimeDelay() {
        if(timeDelay==0)return 60;//last item
        return timeDelay+5;
//        if(timeDelay==0)return 30;//last item
//        return 30;
    }

    public void setTimeDelay(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LatLonEntity{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", timeDelay=" + timeDelay +
                '}';
    }
}
