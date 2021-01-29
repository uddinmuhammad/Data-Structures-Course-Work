public class Data {
    String heading;
    int[] data;// = new ArrayList<>();

    public Data() {
    }

    public Data(String heading, int[] data) {
        this.heading = heading;
        this.data = data;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "heading='" + heading + '\'' +
                ", data=" + data +
                '}';
    }
}
