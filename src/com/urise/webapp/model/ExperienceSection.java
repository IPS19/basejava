package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ExperienceSection extends Sections implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Organization> organizations = new ArrayList<>();

    public ExperienceSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public ExperienceSection() {

    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addElement(Organization element) {
        organizations.add(element);
    }

    @Override
    public String toString() {
        for (Organization organization : organizations) {
            System.out.println(organization);
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceSection that = (ExperienceSection) o;
        //return Objects.equals(list, that.list);
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}