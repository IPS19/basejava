package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    public abstract Resume getFromStorage(int index, String uuid);

    public abstract void saveToStorage(Resume r);

    public abstract void updateStorage(int index, Resume resume);

    public abstract void deleteFromStorage(int index, String uuid);

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return getFromStorage(index, uuid);
        }
        throw new NotExistStorageException(uuid);
    }

    public final void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        saveToStorage(r);
    }

    public final void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateStorage(index, resume);
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(index, uuid);
    }
}