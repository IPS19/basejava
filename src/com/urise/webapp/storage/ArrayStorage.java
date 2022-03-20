package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r == null) {
            return;
        }
        if (isExist(r)) {
            System.out.println("резюме с данным id ( " + r.getUuid() + " ) уже существует, введите другой id");
            return;
        }
        if (size == storage.length) {
            System.out.println("хранилище переполнено, запись невозможна");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (findIndex(uuid) != -1) {
            int index = findIndex(uuid);
            return storage[index];
        } else return null;
    }

    public void delete(String uuid) {
        if (findIndex(uuid) != -1) {
            int index = findIndex(uuid);
            System.arraycopy(storage, index + 1, storage, index, (size - index));
            storage[size] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        storage[index] = resume;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        System.out.println("резюме " + uuid + " не найдено");
        return -1;
    }

    private boolean isExist(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid()))
                return true;
        }
        return false;
    }
}