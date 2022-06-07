package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ExperienceSection implements Sections {
    private final List<Experience> list = new ArrayList<>();

    public List<Experience> getList() {
        return list;
    }

    public void addElement(Experience element) {
        list.add(element);
    }

    @Override
    public String toString() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return "";
    }
}
