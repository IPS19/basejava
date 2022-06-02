package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection implements Sections {
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
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return "";
    }
}