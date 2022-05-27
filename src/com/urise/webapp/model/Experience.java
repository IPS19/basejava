package com.urise.webapp.model;

import java.time.YearMonth;

public class Experience {
    private String title;
    private YearMonth dateFrom;
    private YearMonth dateTo;
    private String nameOfInstitution;
    private String nameOfSpecialization;
    private String description;

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
