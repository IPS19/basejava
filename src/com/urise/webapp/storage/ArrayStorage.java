package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void addToArray(Resume r) {
        storage[size] = r;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected int findIndex(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return i;
            }
        }
        return -1;
    }
}