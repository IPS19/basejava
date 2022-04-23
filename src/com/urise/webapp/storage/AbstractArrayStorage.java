package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void addToArray(Resume r);

    public final void saveToStorage(Resume r) {
        if (size == storage.length) {
            throw new StorageException("storage overflow", r.getUuid());
        }
        addToArray(r);
        size++;
    }

    public final Resume getFromStorage(int index) {
        return storage[index];
    }

    public final void deleteFromStorage(int index) {
        if (index == size - 1) {
            storage[index] = null;
        } else {
            size--;
            System.arraycopy(storage, index + 1, storage, index, (size - index));
            storage[size] = null;
        }
    }

    public final void updateStorage(int index, Resume resume) {
        storage[index] = resume;
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