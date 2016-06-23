package com.example.dojoy.myapplication.gallery;

/**
 * Created by dojoy on 2016/6/7.
 */
public class HomeItem {
    int resId;
    boolean selected;
    String name;
    String content;
    int type;

    public HomeItem(int resId, boolean selected, String name, String content, int type) {
        this.resId = resId;
        this.selected = selected;
        this.name = name;
        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HomeItem() {
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HomeItem{" +
                "resId=" + resId +
                ", selected=" + selected +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
