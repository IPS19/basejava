package com.urise.webapp.model;

import java.io.Serializable;

public class TextSection implements Sections, Serializable {
    private static final long serialVersionUID = 1L;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}