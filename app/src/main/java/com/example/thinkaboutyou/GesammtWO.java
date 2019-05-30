package com.example.thinkaboutyou;

import java.util.List;

public class GesammtWO {

    String name;
    List<Workouts> wos;

    public GesammtWO(String name, List<Workouts> wos) {
        this.name = name;
        this.wos = wos;
    }

    public GesammtWO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Workouts> getWos() {
        return wos;
    }

    public void setWos(List<Workouts> wos) {
        this.wos = wos;
    }
}
