package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract int findIndex(String uuid);

    protected abstract void addToArray(Resume r);

    public final void save(Resume r) {
        String uuid = r.getUuid();
        if (uuid == null)
            return;
        if (findIndex(uuid) >= 0) {
            System.out.println("резюме с данным id ( " + uuid + " ) уже существует, введите другой id");
            return;
        }
        if (size == storage.length) {
            System.out.println("хранилище переполнено, запись невозможна");
            return;
        }
        addToArray(r);
        size++;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("резюме: " + uuid + " не найдено");
        return null;
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, (size - index));
            storage[size - 1] = null;
            size--;
        } else System.out.println("резюме: " + uuid + " не найдено");
    }

    public final void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else System.out.println("резюме: " + resume.getUuid() + " не найдено");
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }
}