package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {
    private final String title;
    private final YearMonth dateFrom;
    private final YearMonth dateTo;
    private final String nameOfInstitution;
    private final String nameOfSpecialization;
    private final String description;

    public Experience(String title, YearMonth dateFrom, YearMonth dateTo, String nameOfInstitution,
                      String nameOfSpecialization, String description) {
        this.title = title;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.nameOfInstitution = nameOfInstitution;
        this.nameOfSpecialization = nameOfSpecialization;
        this.description = description;
    }
}