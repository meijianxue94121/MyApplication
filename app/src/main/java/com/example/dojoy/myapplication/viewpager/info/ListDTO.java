package com.example.dojoy.myapplication.viewpager.info;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by leo on 16/3/20.
 */
public class ListDTO<T> {
    @SerializedName("subjects")
    private ArrayList<T> list;

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListDTO{" +
                "list=" + list +
                '}';
    }
}
