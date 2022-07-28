package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection  extends Sections implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> list = new ArrayList<>();

    public ListSection() {
    }

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
        return list.hashCode();
    }
}