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
    public void save(Resume r) {
        if (storage[0] == null) {
            storage[0] = r;
            return;
        }
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("резюме с данным id ( " + r.getUuid() + " ) уже существует, введите другой id");
        } else {
            index = index * (-1);
            System.arraycopy(storage, index, storage, index + 1, (size - index +1));
            storage[index] = r;
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("резюме " + uuid + " не найдено");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, (size - index));
            storage[size - 1] = null;
            size--;
        } else
            System.out.println("резюме " + uuid + " не найдено");
    }

    @Override
    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else
            System.out.println("резюме " + resume.getUuid() + " не найдено");
    }
}