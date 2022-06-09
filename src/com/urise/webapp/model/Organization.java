package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final List<Expereince> institutionPeriod = new ArrayList<>();
    private final Link homePage;

    public Organization(String nameOfInstitution,String url, Expereince institution) {
        institutionPeriod.add(institution);
        this.homePage = new Link(nameOfInstitution, url);
    }

    public void addInstitution(Expereince institution) {
        institutionPeriod.add(institution);
    }

    public static class Expereince {
        private final YearMonth dateFrom;
        private final YearMonth dateTo;
        private final String title;
        String description;

        public Expereince(YearMonth dateFrom, YearMonth dateTo, String title) {
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
        }

        public Expereince(YearMonth dateFrom, YearMonth dateTo, String title, String description) {
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
        String url = homePage.getUrl();
        if(url != null) System.out.println(url);
        for (Expereince periodInInstitution : institutionPeriod) {
            System.out.print(periodInInstitution);
        }
        return "";
    }
}