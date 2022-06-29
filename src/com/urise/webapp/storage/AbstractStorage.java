package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK searchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    public abstract Resume getFromStorage(SK searchKey);

    public abstract void saveToStorage(Resume r, SK findExistedSearchKey);

    public abstract void updateStorage(SK searchKey, Resume r);

    public abstract void deleteFromStorage(SK searchKey);

    public abstract List<Resume> getAsList();

    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getFromStorage(findExistedSearchKey(uuid));
    }

    public final void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = searchKey(r.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        saveToStorage(r, searchKey);
    }

    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        updateStorage(findExistedSearchKey(resume.getUuid()), resume);
    }

    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteFromStorage(findExistedSearchKey(uuid));
    }

    private SK findExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        }
        LOG.warning("Resume " + uuid + " not exist");
        throw new NotExistStorageException(uuid);
    }

    public final List<Resume> getAllSorted() {
        Comparator<Resume> compareIdThenFullName = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);
        List<Resume> list = getAsList();
        list.sort(compareIdThenFullName);
        return list;
    }
}