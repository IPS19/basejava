package com.urise.webapp;

import com.urise.webapp.model.ContactEnum;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionEnum;
import com.urise.webapp.model.TextSection;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume gKislin = new Resume("Григорий Кислин");
        gKislin.addContact(ContactEnum.PHONE,"+7(921) 855-0482");
        gKislin.addContact(ContactEnum.SKYPE,"skype:grigory.kislin");
        gKislin.addContact(ContactEnum.EMAIL,"gkislin@yandex.ru");
        gKislin.addContact(ContactEnum.LINKEDIN,"https://www.linkedin.com/in/gkislin");
        gKislin.addContact(ContactEnum.GITHUB,"https://github.com/gkislin");
        gKislin.addContact(ContactEnum.STACKOVERFLOW,"https://stackoverflow.com/users/548473/grigory-kislin");
        gKislin.addContact(ContactEnum.HOMEPAGE,"http://gkislin.ru/");

        TextSection objective = new TextSection();
        objective.setDescription("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        gKislin.addSection(SectionEnum.OBJECTIVE, objective);

        TextSection personal = new TextSection();
        personal.setDescription("");
    }
}