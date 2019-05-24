package com.example.thinkaboutyou;

public class Workouts {

    String name;
    int wdh;
    String imagePath;

    public Workouts(String name, int wdh, String imagePath) {
        this.name = name;
        this.wdh = wdh;
        this.imagePath = imagePath;
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
}
