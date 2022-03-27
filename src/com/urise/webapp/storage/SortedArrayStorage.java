package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public boolean checkExistResume(String uuid) {
        return findIndex(uuid) >= 0;
    }

    @Override
    public void writeToStorage(Resume r) {
        int index = findIndex(r.getUuid());
        index = -index;
        System.arraycopy(storage, index - 1, storage, index + 1, (size - index) + 1);
        storage[index - 1] = r;
        size++;
    }
}