package com.urise.webapp.model;

import com.urise.webapp.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Experience> institutionPeriod = new ArrayList<>();
    private Link homePage;

    public Organization(String nameOfInstitution, String url, Experience institution) {
        institutionPeriod.add(institution);
        this.homePage = new Link(nameOfInstitution, url);
    }

    public Organization() {
    }

    public void addInstitution(Experience institution) {
        institutionPeriod.add(institution);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return institutionPeriod.equals(that.institutionPeriod) &&
                Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionPeriod, homePage);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Experience implements Serializable {
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth dateFrom;
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth dateTo;
        private String title;
        String description;

        public Experience() {
        }

        public Experience(YearMonth dateFrom, YearMonth dateTo, String title) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
        }

        public Experience(YearMonth dateFrom, YearMonth dateTo, String title, String description) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
            this.description = description;
        }

        public Experience(YearMonth dateFrom, String title, String description) {
            this.dateFrom = dateFrom;
            this.dateTo = NOW;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            if (description == null)
                return "c " + dateFrom + " по " + dateTo + '\n' + title + '\n';
            return "c " + dateFrom + " по " + dateTo + '\n' + title + '\n' + description + '\n';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Experience that = (Experience) o;
            return dateFrom.equals(that.dateFrom) && dateTo.equals(that.dateTo) && title.equals(that.title) && Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateFrom, dateTo, title, description);
        }
    }

    @Override
    public String toString() {
        System.out.println(homePage.getName());
        String url = homePage.getUrl();
        if (url != null) System.out.println(url);
        for (Experience periodInInstitution : institutionPeriod) {
            System.out.print(periodInInstitution);
        }
        return "";
    }
}