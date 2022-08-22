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

/*            writeWithExeption(contacts.entrySet(), dos, new Writer<Map.Entry<ContactType, String>>() {
                @Override
                public void write(Map.Entry<ContactType, String> contactTypeStringEntry) throws IOException {
                    dos.writeUTF(contactTypeStringEntry.getKey().name());
                    dos.writeUTF(contactTypeStringEntry.getValue());
                }
            });*/

            writeWithException(contacts.entrySet(), dos, contactTypeStringEntry -> {
                dos.writeUTF(contactTypeStringEntry.getKey().name());
                dos.writeUTF(contactTypeStringEntry.getValue());
            });

            Map<SectionType, Sections> sections = r.getSections();
            writeWithException(sections.entrySet(), dos, entrySet -> {
                SectionType type = entrySet.getKey();
                Sections section = entrySet.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS: {
                        ListSection listSection = (ListSection) entrySet.getValue();
                        List<String> list = listSection.getList();
                        writeWithException(list, dos, dos::writeUTF);
                        break;
                    }
                    case EDUCATION:
                    case EXPERIENCE: {
                        ExperienceSection experienceSection = (ExperienceSection) entrySet.getValue();
                        List<Organization> organizations = experienceSection.getList();
                        writeWithException(organizations, dos, organization -> {
                            Link link = organization.getHomePage();
                            dos.writeUTF(link.getName());
                            String url = link.getUrl();

                            dos.writeUTF(url == null ? "" : url);

                            List<Organization.Experience> experience = organization.getInstitutionPeriod();

                            writeWithException(experience, dos, org -> {
                                dos.writeInt(org.getDateFrom().getYear());
                                dos.writeInt(org.getDateFrom().getMonthValue());
                                dos.writeInt(org.getDateTo().getYear());
                                dos.writeInt(org.getDateTo().getMonthValue());
                                dos.writeUTF(org.getTitle());
                                String description = org.getDescription();

                                dos.writeUTF(description == null ? "" : description);
                            });
                        });
                    }
                    break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithException(dis, () -> {
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
                        ListSection listSection = new ListSection();
                        readWithException(dis, () -> {
                            listSection.addElement(dis.readUTF());
                        });
                        if (sectionType.equals("ACHIEVEMENT")) {
                            resume.addSection(SectionType.ACHIEVEMENT, listSection);
                        } else resume.addSection(SectionType.QUALIFICATIONS, listSection);
                        break;
                    }
                    case "EDUCATION":
                    case "EXPERIENCE": {
                        ExperienceSection experienceSection = new ExperienceSection();
                        List<Organization> organizations = new ArrayList<>();
                        readWithException(dis, () -> {
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
                        });

                        if (sectionType.equals("EDUCATION")) {
                            resume.addSection(SectionType.EDUCATION, experienceSection);
                        } else resume.addSection(SectionType.EXPERIENCE, experienceSection);
                        break;
                    }
                }
            });
            // TODO implements sections
            return resume;
        }
    }

    <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Writer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }

    void readWithException(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    public interface Writer<T> {
        void write(T t) throws IOException;
    }

    public interface Reader {
        void read() throws IOException;
    }
}