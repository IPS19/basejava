package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    public Resume getFromStorage(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void saveToStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void updateStorage(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void deleteFromStorage(Object searchKey) {
        Resume key = (Resume) searchKey;
        storage.remove(key.getUuid());
    }

    @Override
    public List<Resume> getAsList() {
        return new ArrayList<Resume>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}

