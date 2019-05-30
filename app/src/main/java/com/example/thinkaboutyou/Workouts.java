package com.example.thinkaboutyou;

public class Workouts {

    String name;
    int wdh;
    String imagePath;
    long time;

    public Workouts(String name, int wdh, String imagePath, long time) {
        this.name = name;
        this.wdh = wdh;
        this.imagePath = imagePath;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWdh() {
        return wdh;
    }

    public void setWdh(int wdh) {
        this.wdh = wdh;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
