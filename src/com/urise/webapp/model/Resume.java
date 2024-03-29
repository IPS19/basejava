package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    // Unique identifier

    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.setContact(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.setContact(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.setContact(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.setContact(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.setContact(SectionType.EXPERIENCE, new ExperienceSection(Organization.EMPTY));
        EMPTY.setContact(SectionType.EDUCATION, new ExperienceSection(Organization.EMPTY));
    }
    private String uuid;
    private String fullName;

    public Resume() {
    }

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private final Map<SectionType, Sections> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void setContact(SectionType kindOfSection, Sections section) {
        sections.put(kindOfSection, section);
    }

    public Map<SectionType, Sections> getSections() {
        return sections;
    }

    public void setSection(ContactType type, String value) {
        contacts.put(type, value);
    }

    public Sections getSection(SectionType type) {
        return sections.get(type);
    }

    @Override
    public String toString() {
        return "" +
                "uuid=" + uuid + "\n" +
                "fullName: " + fullName + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
