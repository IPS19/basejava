package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    public Resume getFromStorage(Resume resume) {
        return resume;
    }

    @Override
    public void saveToStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void updateStorage(Resume resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void deleteFromStorage(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    public List<Resume> getAsList() {
        return new ArrayList<>(storage.values());
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

