package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected Integer searchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    public void addToArray(Resume r, Integer searchKey) {
        int index = searchKey(r.getUuid());
        int insertIndex = -index - 1;
        if (insertIndex < storage.length - 1) {
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        }
        storage[insertIndex] = r;
    }
}