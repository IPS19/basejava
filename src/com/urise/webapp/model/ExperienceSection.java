package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ExperienceSection implements Sections {
    private final List<Organization> list = new ArrayList<>();

    public List<Organization> getList() {
        return list;
    }

    public void addElement(Organization element) {
        list.add(element);
    }

    @Override
    public String toString() {
        for (Organization organization : list) {
            System.out.println(organization);
        }
        return "";
    }
}
