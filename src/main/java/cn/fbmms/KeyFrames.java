package cn.fbmms;

public class KeyFrames {
    private String id;
    private String cnt_date;
    private String cnt_time;
    private int sum_cnt;
    private int fiveframe_cnt;
    private double fiveframe_rate;
    private int sixframe_cnt;
    private double sixframe_rate;
    private int keyframe_cnt;
    private double keyframe_rate;

    public KeyFrames(String id, String cnt_date, String cnt_time, int sum_cnt, int fiveframe_cnt, double fiveframe_rate, int sixframe_cnt, double sixframe_rate, int keyframe_cnt, double keyframe_rate) {
        this.id = id;
        this.cnt_date = cnt_date;
        this.cnt_time = cnt_time;
        this.sum_cnt = sum_cnt;
        this.fiveframe_cnt = fiveframe_cnt;
        this.fiveframe_rate = fiveframe_rate;
        this.sixframe_cnt = sixframe_cnt;
        this.sixframe_rate = sixframe_rate;
        this.keyframe_cnt = keyframe_cnt;
        this.keyframe_rate = keyframe_rate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCnt_date(String cnt_date) {
        this.cnt_date = cnt_date;
    }

    public void setCnt_time(String cnt_time) {
        this.cnt_time = cnt_time;
    }

    public void setSum_cnt(int sum_cnt) {
        this.sum_cnt = sum_cnt;
    }

    public void setFiveframe_cnt(int fiveframe_cnt) {
        this.fiveframe_cnt = fiveframe_cnt;
    }

    public void setFiveframe_rate(double fiveframe_rate) {
        this.fiveframe_rate = fiveframe_rate;
    }

    public void setSixframe_cnt(int sixframe_cnt) {
        this.sixframe_cnt = sixframe_cnt;
    }

    public void setSixframe_rate(double sixframe_rate) {
        this.sixframe_rate = sixframe_rate;
    }

    public void setKeyframe_cnt(int keyframe_cnt) {
        this.keyframe_cnt = keyframe_cnt;
    }

    public void setKeyframe_rate(double keyframe_rate) {
        this.keyframe_rate = keyframe_rate;
    }

    public String getId() {
        return id;
    }

    public String getCnt_date() {
        return cnt_date;
    }

    public String getCnt_time() {
        return cnt_time;
    }

    public int getSum_cnt() {
        return sum_cnt;
    }

    public int getFiveframe_cnt() {
        return fiveframe_cnt;
    }

    public double getFiveframe_rate() {
        return fiveframe_rate;
    }

    public int getSixframe_cnt() {
        return sixframe_cnt;
    }

    public double getSixframe_rate() {
        return sixframe_rate;
    }

    public int getKeyframe_cnt() {
        return keyframe_cnt;
    }

    public double getKeyframe_rate() {
        return keyframe_rate;
    }

    @Override
    public String toString() {
        return "KeyFrames{" +
                "id='" + id + '\'' +
                ", cnt_date='" + cnt_date + '\'' +
                ", cnt_time='" + cnt_time + '\'' +
                ", sum_cnt=" + sum_cnt +
                ", fiveframe_cnt=" + fiveframe_cnt +
                ", fiveframe_rate=" + fiveframe_rate +
                ", sixframe_cnt=" + sixframe_cnt +
                ", sixframe_rate=" + sixframe_rate +
                ", keyframe_cnt=" + keyframe_cnt +
                ", keyframe_rate=" + keyframe_rate +
                '}';
    }
}
