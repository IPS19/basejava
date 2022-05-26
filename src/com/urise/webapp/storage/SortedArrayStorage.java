package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer searchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void addToArray(Resume r) {
        int index = (int) searchKey(r.getUuid());
        int insertIndex = (-index) - 1;
        if (insertIndex < storage.length - 1) {
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        }
        storage[insertIndex] = r;
    }
}