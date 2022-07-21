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
                SectionType section = entry.getKey();
                switch (section) {
                    case PERSONAL -> {
                        dos.writeUTF("PERSONAL");
                        dos.writeUTF(entry.getValue().toString());
                    }
                    case OBJECTIVE -> {
                        dos.writeUTF("OBJECTIVE");
                        dos.writeUTF(entry.getValue().toString());
                    }
                    case ACHIEVEMENT -> {
                        dos.writeUTF("ACHIEVEMENT");
                        List<String> achievement = (List<String>) entry.getValue();
                        dos.writeInt(achievement.size());
                        for (String element : achievement) {
                            dos.writeUTF(element);
                        }
                    }
                    case QUALIFICATIONS -> {
                        dos.writeUTF("QUALIFICATIONS");
                        List<String> qualifications = (List<String>) entry.getValue();
                        dos.writeInt(qualifications.size());
                        for (String element : qualifications) {
                            dos.writeUTF(element);
                        }
                    }
                    case EDUCATION -> {
                        dos.writeUTF("QUALIFICATIONS");

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
                        TextSection personal = new TextSection();
                        personal.setDescription(dis.readUTF());
                        resume.addSection(SectionType.PERSONAL, personal);
                    }
                    case "OBJECTIVE": {
                        TextSection objective = new TextSection();
                        objective.setDescription(dis.readUTF());
                        resume.addSection(SectionType.OBJECTIVE, objective);
                    }
                    case "ACHIEVEMENT": {
                        int achievementSize = dis.readInt();
                        ListSection achievement = new ListSection();
                        for (int j = 0; j < achievementSize; j++) {
                            achievement.addElement(dis.readUTF());
                        }
                        resume.addSection(SectionType.OBJECTIVE, achievement);
                    }
                    case "QUALIFICATIONS": {
                        int qualificationsSize = dis.readInt();
                        ListSection qualifications = new ListSection();
                        for (int j = 0; j < qualificationsSize; j++) {
                            qualifications.addElement(dis.readUTF());
                        }
                        resume.addSection(SectionType.OBJECTIVE, qualifications);
                    }


                }
            }

            // TODO implements sections
            return resume;
        }
    }
}

