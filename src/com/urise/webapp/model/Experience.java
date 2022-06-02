package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {
    private final String nameOfInstitution; // организация(заведение)
    private final YearMonth dateFrom;
    private final YearMonth dateTo;
    private final String title; //
    private String description = null;

    public Experience(YearMonth dateFrom, YearMonth dateTo, String nameOfInstitution,
                      String title, String description) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.nameOfInstitution = nameOfInstitution;
        this.title = title;
        this.description = description;
    }

    public Experience(YearMonth dateFrom, YearMonth dateTo, String nameOfInstitution,
                      String title) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.nameOfInstitution = nameOfInstitution;
        this.title = title;
    }

    @Override
    public String toString() {
        if (description == null)
            return "c " + dateFrom +
                    " по " + dateTo + '\n' +
                    nameOfInstitution + '\n' +
                    title + '\n';
        return "c " + dateFrom +
                " по " + dateTo + '\n' +
                nameOfInstitution + '\n' +
                title + '\n' +
                description + '\n';
    }
}