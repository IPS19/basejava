package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
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
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
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
                        List<String> qualifications = (List<String>) entry.getValue();
                        dos.writeInt(qualifications.size());
                        for (String element : qualifications) {
                            dos.writeUTF(element);
                        }
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        List<Organization> organizations = (List<Organization>) entry.getValue();
                        dos.writeInt(organizations.size());
                        for (Organization organization : organizations) {
                            Link link = organization.getHomePage();
                            dos.writeUTF(link.getName());
                            String url = link.getUrl();
                            if (url != null) dos.writeUTF(link.getUrl());
                            List<Organization.Experience> experience = organization.getInstitutionPeriod();
                            for (Organization.Experience element : experience) {
                                dos.writeInt(element.getDateFrom().getYear());
                                dos.writeInt(element.getDateFrom().getYear());
                                dos.writeUTF(element.getTitle());
                                String description = element.getDescription();
                                if (description != null) dos.writeUTF(description);
                            }
                        }
                    }
                }
            }

            // TODO implements sections
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
                    case "PERSONAL": {
                        resume.addSection(SectionType.PERSONAL, readTextSection(dis));
                    }
                    case "OBJECTIVE": {
                        resume.addSection(SectionType.OBJECTIVE, readTextSection(dis));
                    }
                    case "ACHIEVEMENT": {
                        resume.addSection(SectionType.ACHIEVEMENT, readListSection(dis));
                    }
                    case "QUALIFICATIONS": {
                        resume.addSection(SectionType.QUALIFICATIONS, readListSection(dis));
                    }
                    case "EDUCATION": {
                        resume.addSection(SectionType.EDUCATION, );
                    }
                }
            }
            // TODO implements sections
            return resume;
        }
    }

    private TextSection readTextSection(DataInputStream dis) throws IOException {
        TextSection text = new TextSection();
        text.setDescription(dis.readUTF());
        return text;
    }

    private ListSection readListSection(DataInputStream dis) throws IOException {
        int listSize = dis.readInt();
        ListSection list = new ListSection();
        for (int i = 0; i < listSize; i++) {
            list.addElement(dis.readUTF());
        }
        return list;
    }

    private ExperienceSection readExperienceSection(DataInputStream dis) throws IOException {
        ExperienceSection experience = new ExperienceSection();
        int listSize = dis.readInt();
        List<Organization> organizations = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            Organization organization = new Organization(dis.readUTF(),dis.readUTF(), )
            Link link = new Link();
            link.
            organizations.add(dis.readUTF());
        }

    }
}

