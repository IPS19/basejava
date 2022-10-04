package com.urise.webapp.util;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Sections;
import com.urise.webapp.model.TextSection;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.UUID;

public class JsonParserTest extends TestCase {

    public void testRead() {
        Resume resumeToWrite = ResumeTestData.createResume(UUID.randomUUID().toString(), "name");
        String json = JsonParser.write(resumeToWrite);
        System.out.println(json);
        Resume resumeToRead = JsonParser.read(json, Resume.class);
        Assert.assertEquals(resumeToWrite, resumeToRead);
    }

    public void testWrite() {
        TextSection section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Sections.class);
        System.out.println(json);
        Sections section2 = JsonParser.read(json, Sections.class);
        Assert.assertEquals(section1, section2);
    }
}