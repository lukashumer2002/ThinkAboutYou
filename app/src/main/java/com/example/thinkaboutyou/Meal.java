package com.example.thinkaboutyou;

public class Meal {

    private String meal;
    private int kcal;

    public Meal(String meal, int kcal) {
        this.meal = meal;
        this.kcal = kcal;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
