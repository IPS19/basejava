package com.urise.webapp.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractArrayStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }
}