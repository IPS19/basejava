package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Disabled
    @Test(expected = StorageException.class)
    public void storageOverflow() throws Exception {
    }
}