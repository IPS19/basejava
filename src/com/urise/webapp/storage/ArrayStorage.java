package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage,0,size,null);
        size = 0;
    }

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("резюме с данным id (" + r.getUuid() + ") уже существует, введите другой id");
                return;
            }
        }
        if (size == 10000) {
            System.out.println("хранилище переполнено, запись невозможна");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return storage[i];
        }
        System.out.println("резюме " + uuid + " не найдено");
        return null;
    }

    public void delete(String uuid) {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                isExist = true;
                while (storage[i + 1] != null) {
                    storage[i] = storage[i + 1];
                    i++;
                }
                    storage[i] = null;
                size--;
            }
        }
        if (!isExist) {
            System.out.println("резюме " +  uuid + " не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i] = resume;
                return;
            }
        }
        System.out.println("резюме " +  resume.getUuid() + " не найдено");
    }

}