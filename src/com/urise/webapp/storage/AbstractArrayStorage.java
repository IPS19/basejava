package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        if (size == storage.length) {
            throw new StorageException("хранилище переполнено, запись невозможна", r.getUuid());
        }
        addToArray(r);
        size++;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } throw new NotExistStorageException(uuid);
        //System.out.println("резюме '" + uuid + "' не найдено");
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            if (index == size - 1) {
                storage[index] = null;
            } else {
                size--;
                System.arraycopy(storage, index + 1, storage, index, (size - index));
                storage[size] = null;
            }
        } else throw new NotExistStorageException(uuid);
    }

    public final void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else throw new NotExistStorageException(resume.getUuid());
           // System.out.println("резюме '" + resume.getUuid() + "' не найдено");
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