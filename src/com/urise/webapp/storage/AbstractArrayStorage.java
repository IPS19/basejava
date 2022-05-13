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

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    public final Resume getFromStorage(Object index) {
        return storage[(Integer) index];
    }

    public final void deleteFromStorage(Object indexArg) {
        int index = (int) indexArg;
        if (index == size - 1) {
            size--;
            storage[index] = null;
        } else {
            size--;
            System.arraycopy(storage, index + 1, storage, index, (size - index));
            storage[size] = null;
        }
    }

    public final void updateStorage(Object key, Resume r) {
        storage[(Integer) key] = r;
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