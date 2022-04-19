package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>(STORAGE_LIMIT);

    @Override
    public void clear() {
        storage.clear();
    }

    public final void save(Resume r) {
        storage.add(r);
    }

    public final Resume get(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid))
                return r;
        }
        throw new NotExistStorageException(uuid);
    }

    public final void update(Resume resume) {
        int index = 0;
        for (Resume r : storage) {
            if (r.equals(resume)) {
                storage.add(index, resume);
                return;
            } else index++;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public final void delete(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            if (r.getUuid().equals(uuid)) {
                iterator.remove();
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public final Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public final int size() {
        return storage.size();
    }
}

