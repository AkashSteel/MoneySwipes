package com.example.akash.cookieclicker;

import java.io.Serializable;

/**
 * Created by Akash on 2/12/2018.
 */

public class Upgrades implements Serializable {
    String name;
    String additional;
    String cost;
    int timesSelected;
    int pic;

    public Upgrades(String name, String additional,String cost, int timesSelected,int pic){
        this.name = name;
        this.additional = additional;
        this.cost = cost;
        this.timesSelected = timesSelected;
        this.pic = pic;
    }

    public String getName(){
        return name;
    }

    public String getAdditional() {
        return additional;
    }

    public String getCost() {
        return cost;
    }

    public int getTimesSelected() {
        return timesSelected;
    }

    public int getPic() {
        return pic;
    }
}