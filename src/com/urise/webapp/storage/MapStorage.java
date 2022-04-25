package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String searchKey(Object key) {
        if (storage.containsKey(key)) {
            return (String) key;
        }
        return null;
    }

    @Override
    public Resume getFromStorage(Object arg) {
        storage.get(arg);

        return null;
    }

    @Override
    public void saveToStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void updateStorage(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    public void deleteFromStorage(int index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}