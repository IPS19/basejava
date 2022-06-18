package com.urise.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListSection implements Sections, Serializable {
    private List<String> list = new ArrayList<>();

    public void setList(List<String> list) {
        this.list = list;
    }

    public void addElement(String element) {
        list.add(element);
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        for (String s : list) {
            System.out.println(s);
        }
        return "";
    }
}