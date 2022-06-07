package com.urise.webapp.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Experience {


    private final String nameOfInstitution; // организация(заведение)
    private final List<PeriodInInstitution> institutionPeriod = new ArrayList<>();

    public Experience(String nameOfInstitution, PeriodInInstitution institution) {
        this.nameOfInstitution = nameOfInstitution;
        institutionPeriod.add(institution);
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
        System.out.println(nameOfInstitution);
        for (int i = 0; i < institutionPeriod.size(); i++) {
            System.out.print(institutionPeriod.get(i));
        }
        return "";
    }
    /*    @Override
    public String toString() {
        if (description == null)
            return "c " + dateFrom + " по " + dateTo + '\n' + nameOfInstitution + '\n' + title + '\n';
        return "c " + dateFrom + " по " + dateTo + '\n' + nameOfInstitution + '\n' + title + '\n' + description + '\n';
    }*/
}