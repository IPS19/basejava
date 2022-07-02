package com.urise.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExperienceSection implements Sections, Serializable {
    private static final long serialVersionUID = 1L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceSection that = (ExperienceSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}