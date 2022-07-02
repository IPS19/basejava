package com.urise.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.NOW;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Experience> institutionPeriod = new ArrayList<>();
    private final Link homePage;

    public Organization(String nameOfInstitution, String url, Experience institution) {
        institutionPeriod.add(institution);
        this.homePage = new Link(nameOfInstitution, url);
    }

    public void addInstitution(Experience institution) {
        institutionPeriod.add(institution);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return institutionPeriod.equals(that.institutionPeriod) && Objects.equals(homePage, that.homePage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionPeriod, homePage);
    }

    public static class Experience implements Serializable {
        private final YearMonth dateFrom;
        private final YearMonth dateTo;
        private final String title;
        String description;

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