package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object searchKey(String uuid);

    protected abstract boolean isExist(String uuid);

    public abstract Resume getFromStorage(Object key);

    public abstract void saveToStorage(Resume r);

    public abstract void updateStorage(Object key, Resume r);

    public abstract void deleteFromStorage(int index, String uuid);

    public final Resume get(String uuid) {
        if (isExist(uuid)) {
            return getFromStorage(searchKey(uuid));
        }
        throw new NotExistStorageException(uuid);
    }

    public final void save(Resume r) {
        if(isExist(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        saveToStorage(r);
    }

    public final void update(Resume resume) {
        if(!isExist(resume.getUuid())) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateStorage(searchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        int index = searchKey(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteFromStorage(index, uuid);
    }
}