package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final List<PeriodInInstitution> institutionPeriod = new ArrayList<>();
    private final Link homePage;

    public Organization(String nameOfInstitution,String url, PeriodInInstitution institution) {
        institutionPeriod.add(institution);
        this.homePage = new Link(nameOfInstitution, url);
    }

    public void addInstitution(PeriodInInstitution institution) {
        institutionPeriod.add(institution);
    }

    public static class PeriodInInstitution {
        public final YearMonth dateFrom;
        public final YearMonth dateTo;
        public final String title;
        String description;

        public PeriodInInstitution(YearMonth dateFrom, YearMonth dateTo, String title) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
        }

        public PeriodInInstitution(YearMonth dateFrom, YearMonth dateTo, String title, String description) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            if (description == null)
                return "c " + dateFrom + " по " + dateTo + '\n' + title + '\n';
            return "c " + dateFrom + " по " + dateTo + '\n' + title + '\n' + description + '\n';
        }
    }

    @Override
    public String toString() {
        System.out.println(homePage.getName());
        for (PeriodInInstitution periodInInstitution : institutionPeriod) {
            System.out.print(periodInInstitution);
        }
        return "";
    }
}