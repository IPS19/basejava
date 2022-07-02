package com.urise.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection implements Sections, Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}