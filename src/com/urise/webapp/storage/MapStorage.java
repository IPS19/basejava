package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String searchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected boolean isExist(String uuid) {
        return uuid != null;
    }

    @Override
    public Resume getFromStorage(String key) {
        return storage.get(key);
    }

    @Override
    public void saveToStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void updateStorage(String key, Resume resume) {
        storage.put(key, resume);
    }

    @Override
    public void deleteFromStorage(String key) {
        storage.remove(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getAsList() {
        return new ArrayList<>(storage.values());
    }
}