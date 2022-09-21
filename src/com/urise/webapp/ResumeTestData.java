package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.Map;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume gKislin = new Resume(uuid, fullName);
        /*gKislin.addContact(ContactType.PHONE, "+7(921) 855-0482");
        gKislin.addContact(ContactType.SKYPE, "grigory.kislin");
        gKislin.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        gKislin.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        gKislin.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        gKislin.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        gKislin.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        TextSection objective = new TextSection();
        objective.setDescription("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        gKislin.addSection(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection();
        personal.setDescription("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        gKislin.addSection(SectionType.PERSONAL, personal);

        ListSection achievement = new ListSection();
        achievement.addElement("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов " +
                "на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для " +
                "комплексных DIY смет");
        achievement.addElement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP)." +
                " Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievement.addElement("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.addElement("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера");
        achievement.addElement("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring," +
                " Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.addElement("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов" +
                " (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии" +
                " через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.addElement("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        gKislin.addSection(SectionType.ACHIEVEMENT, achievement);

        ListSection qualifications = new ListSection();
        qualifications.addElement("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addElement("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.addElement("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.addElement("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.addElement("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.addElement("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, " +
                "MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, " +
                "ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.addElement("Python: Django.");
        qualifications.addElement("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.addElement("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.addElement("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX," +
                " DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.addElement("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.addElement("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway," +
                " Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.addElement("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualifications.addElement("Родной русский, английский \"upper intermediate\"");
        gKislin.addSection(SectionType.QUALIFICATIONS, qualifications);

        Organization school = new Organization("Заочная физико-техническая школа при МФТИ", "https://school.mipt.ru/",
                new Organization.Experience(YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Закончил с отличием"));

        Organization itmo = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/",
                new Organization.Experience(YearMonth.of(1987, 9), YearMonth.of(1993, 7), "Инженер (программист Fortran, C)"));
        itmo.addInstitution(new Organization.Experience(YearMonth.of(1993, 9), YearMonth.of(1996, 7), "Аспирантура (программист С, С++)"));

        Organization alcatelEdu = new Organization("Alcatel", "http://www.alcatel.ru/",
                new Organization.Experience(YearMonth.of(1997, 9), YearMonth.of(1998, 3), "6 месяцев обучения цифровым телефонным сетям (Москва)"));

        Organization simensEdu = new Organization("Siemens AG", "https://new.siemens.com/",
                new Organization.Experience(YearMonth.of(2005, 1), YearMonth.of(2005, 4), "3 месяца обучения мобильным IN сетям (Берлин)"));

        Organization luxoftEdu = new Organization("Luxoft", "https://ibs-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                new Organization.Experience(YearMonth.of(2011, 3), YearMonth.of(2011, 4), "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'"));

        Organization coursera = new Organization("Coursera", "https://www.coursera.org/course/progfun",
                new Organization.Experience(YearMonth.of(2013, 3), YearMonth.of(2013, 5), "'Functional Programming Principles in Scala' by Martin Odersky"));

        ExperienceSection education = new ExperienceSection();
        education.addElement(school);
        education.addElement(itmo);
        education.addElement(alcatelEdu);
        education.addElement(simensEdu);
        education.addElement(luxoftEdu);
        education.addElement(coursera);
        gKislin.addSection(SectionType.EDUCATION, education);

        Organization alcatel = new Organization("Alcatel", "http://www.alcatel.ru/",
                new Organization.Experience(YearMonth.of(1997, 9), YearMonth.of(2005, 1),
                        "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));

        Organization simens = new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
                new Organization.Experience(YearMonth.of(2005, 1), YearMonth.of(2007, 2), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));

        Organization enkata = new Organization("Enkata", "https://www.pega.com/products/platform/robotic-process-automation",
                new Organization.Experience(YearMonth.of(2007, 3), YearMonth.of(2008, 6),
                        "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        Organization yota = new Organization("Yota", "https://www.yota.ru/",
                new Organization.Experience(YearMonth.of(2008, 6), YearMonth.of(2010, 12),
                        "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));

        Organization luxoft = new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                new Organization.Experience(YearMonth.of(2010, 12), YearMonth.of(2012, 4), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, " +
                        "Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));

        Organization rit = new Organization("RIT Center", null,
                new Organization.Experience(YearMonth.of(2012, 4), YearMonth.of(2014, 10),
                        "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы." +
                        " Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        Organization wrike = new Organization("Wrike", "https://www.wrike.com/",
                new Organization.Experience(YearMonth.of(2014, 10), YearMonth.of(2016, 1),
                        "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));

        Organization jop = new Organization("Java Online Projects", "https://javaops.ru/",
                new Organization.Experience(YearMonth.of(2013, 10), "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."));

        ExperienceSection workExperience = new ExperienceSection();
        workExperience.addElement(alcatel);
        workExperience.addElement(simens);
        workExperience.addElement(enkata);
        workExperience.addElement(yota);
        workExperience.addElement(luxoft);
        workExperience.addElement(rit);
        workExperience.addElement(wrike);
        workExperience.addElement(jop);
        gKislin.addSection(SectionType.EXPERIENCE, workExperience);*/
        return gKislin;
    }

    public static void main(String[] args) {

        Resume gKislin = createResume("uuid1", "Григорий Кислин");

        System.out.println(gKislin);

        Map<ContactType, String> contacts = gKislin.getContacts();
        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            System.out.println(entry.getValue());
        }

        Map<SectionType, Sections> sections = gKislin.getSections();
        for (Map.Entry<SectionType, Sections> entry : sections.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            System.out.println(entry.getValue());
        }
    }
}