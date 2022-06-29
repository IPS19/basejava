package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage <Integer>{
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    protected abstract void addToArray(Resume r, Integer index);

    public final void saveToStorage(Resume r, Integer index) {
        if (size == storage.length) {
            throw new StorageException("storage overflow", r.getUuid());
        }
        addToArray(r, index);
        size++;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    public final Resume getFromStorage(Integer index) {
        return storage[ index];
    }

    public final void deleteFromStorage(Integer index) {
        if (index == size - 1) {
            storage[index] = null;
        } else {
            System.arraycopy(storage, index + 1, storage, index, (size - index));
            storage[size] = null;
        }
        size--;
    }

    public final void updateStorage(Integer index, Resume r) {
        storage[index] = r;
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final int size() {
        return size;
    }

    @Override
    public List<Resume> getAsList() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }
}