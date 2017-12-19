package com.example.huanpet.view.pet.View;

/**
 * Created by 阿三 on 2017/12/14.
 */
public class SortModel {

    private String name; // 显示的数据
    private String sortLetters; // 显示数据拼音的首字母
    private String petCode;

    public String getPetCode() {
        return petCode;
    }

    public void setPetCode(String petCode) {
        this.petCode = petCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
