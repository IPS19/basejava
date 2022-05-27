package com.urise.webapp.model;

public enum ContactEnum {
    PHONE("Телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
