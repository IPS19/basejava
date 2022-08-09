package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSrializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            Map<ContactType, String> contacts = r.getContacts();

            dos.writeInt(contacts.size());

            writeWithExeption(contacts.entrySet(), dos, new Writer<Map.Entry<ContactType, String>>() {
                @Override
                public void write(Map.Entry<ContactType, String> contactTypeStringEntry) throws IOException {
                    dos.writeUTF(contactTypeStringEntry.getKey().name());
                    dos.writeUTF(contactTypeStringEntry.getValue());
                }
            });

            Map<SectionType, Sections> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Sections> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Sections section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        ListSection listSection = (ListSection) entry.getValue();
                        List<String> list = listSection.getList();
                        dos.writeInt(list.size());
                        for (String element : list) {
                            dos.writeUTF(element);
                        }
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        ExperienceSection experienceSection = (ExperienceSection) entry.getValue();
                        List<Organization> organizations = experienceSection.getList();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            Link link = organization.getHomePage();
                            dos.writeUTF(link.getName());
                            String url = link.getUrl();

                            dos.writeUTF(url == null ? "" : url);

                            List<Organization.Experience> experience = organization.getInstitutionPeriod();
                            dos.writeInt(experience.size());
                            for (Organization.Experience element : experience) {
                                dos.writeInt(element.getDateFrom().getYear());
                                dos.writeInt(element.getDateFrom().getMonthValue());
                                dos.writeInt(element.getDateTo().getYear());
                                dos.writeInt(element.getDateTo().getMonthValue());
                                dos.writeUTF(element.getTitle());
                                String description = element.getDescription();

                                dos.writeUTF(description == null ? "" : description);
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                String sectionType = dis.readUTF();
                switch (sectionType) {
                    case "PERSONAL":
                    case "OBJECTIVE": {
                        TextSection textSection = new TextSection();
                        textSection.setDescription(dis.readUTF());
                        if (sectionType.equals("PERSONAL")) {
                            resume.addSection(SectionType.PERSONAL, textSection);
                        } else resume.addSection(SectionType.OBJECTIVE, textSection);
                        break;
                    }
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS": {
                        int size = dis.readInt();
                        ListSection listSection = new ListSection();
                        for (int j = 0; j < size; j++) {
                            listSection.addElement(dis.readUTF());
                        }
                        if (sectionType.equals("ACHIEVEMENT")) {
                            resume.addSection(SectionType.ACHIEVEMENT, listSection);
                        } else resume.addSection(SectionType.QUALIFICATIONS, listSection);
                        break;
                    }
                    case "EDUCATION":
                    case "EXPERIENCE": {
                        ExperienceSection experienceSection = new ExperienceSection();
                        List<Organization> organizations = new ArrayList<>();
                        int organizationsSize = dis.readInt();
                        for (int j = 0; j < organizationsSize; j++) {
                            String name = dis.readUTF();

                            String url = dis.readUTF();

                            url = (url.equals("") ? null : url);

                            List<Organization.Experience> experience = new ArrayList<>();
                            int experienceSize = dis.readInt();
                            for (int k = 0; k < experienceSize; k++) {
                                YearMonth dateFrom = YearMonth.of(dis.readInt(), dis.readInt());
                                YearMonth dateTo = YearMonth.of(dis.readInt(), dis.readInt());
                                String title = dis.readUTF();

                                String description = dis.readUTF();

                                description = (description.equals("") ? null : description);

                                Organization.Experience element = new Organization.Experience(
                                        dateFrom, dateTo, title, description);
                                experience.add(element);
                            }
                            Organization organization = new Organization(name, url, experience);
                            experienceSection.addElement(organization);
                        }
                        if (sectionType.equals("EDUCATION")) {
                            resume.addSection(SectionType.EDUCATION, experienceSection);
                        } else resume.addSection(SectionType.EXPERIENCE, experienceSection);
                        break;
                    }
                }
            }
            // TODO implements sections
            return resume;
        }
    }
    <T> void writeWithExeption(Collection<T> collection, DataOutputStream dos, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }
}

