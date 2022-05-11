package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object searchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    public abstract Resume getFromStorage(Object searchKey);

    public abstract void saveToStorage(Resume r);

    public abstract void updateStorage(Object searchKey, Resume r);

    public abstract void deleteFromStorage(Object searchKey);

    public final Resume get(String uuid) {
        return getFromStorage(findExistedSearchKey(uuid));
    }

    public final void save(Resume r) {
        if (isExist(searchKey(r.getUuid()))) {
            throw new ExistStorageException(r.getUuid());
        }
        saveToStorage(r);
    }

    public final void update(Resume resume) {
        updateStorage(findExistedSearchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        deleteFromStorage(findExistedSearchKey(uuid));
    }

    private Object findExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        }
        throw new NotExistStorageException(uuid);
    }
}