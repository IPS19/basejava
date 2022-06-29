package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected boolean isExist(Integer index) {
            return index >= 0;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void saveToStorage(Resume r, Integer index) {
        storage.add(r);
    }

    @Override
    public final Resume getFromStorage(Integer index) {
        return storage.get(index);
    }

    @Override
    public void updateStorage(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    @Override
    public void deleteFromStorage(Integer index) {
        storage.remove((int) index);
    }

    @Override
    public final int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getAsList() {
        return storage;
    }
}