package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object searchKey(String uuid);

    protected abstract boolean isExist(Object index);

    public abstract Resume getFromStorage(Object key);

    public abstract void saveToStorage(Resume r);

    public abstract void updateStorage(Object key, Resume r);

    public abstract void deleteFromStorage(Object key);

    public final Resume get(String uuid) {
        return getFromStorage(returnExistOrException(uuid));
    }

    private int returnExistOrException(String uuid) {
        int index = (int) searchKey(uuid);
        if (isExist(index)) {
            return index;
        }
        throw new NotExistStorageException(uuid);
    }

    public final void save(Resume r) {
        if (isExist(searchKey(r.getUuid()))) {
            throw new ExistStorageException(r.getUuid());
        }
        saveToStorage(r);
    }

    public final void update(Resume resume) {
        updateStorage(returnExistOrException(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        deleteFromStorage(returnExistOrException(uuid));
    }
}