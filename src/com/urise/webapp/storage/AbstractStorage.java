package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    public abstract Resume getViaIndex(int index);

    public abstract void saveToStorage(Resume r);

    public abstract void updateViaIndex(int index, Resume resume);

    public abstract void deleteViaIndex(int index);

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return getViaIndex(index);
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
        if (index >= 0) {
            updateViaIndex(index, resume);
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            deleteViaIndex(index);
            return;
        }
        throw new NotExistStorageException(uuid);
    }
}