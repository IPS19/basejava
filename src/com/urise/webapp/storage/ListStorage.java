package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected Object searchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid))
                return i;
        }
        return -1;
    }

    @Override
    protected boolean isExist(String uuid) {
        //return ((Integer) searchKey((uuid))) >= 0;
        return storage.contains(new Resume(uuid));
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void saveToStorage(Resume r) {
        storage.add(r);
    }

    @Override
    public final Resume getFromStorage(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public void updateStorage(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }

    @Override
    public void deleteFromStorage(Object key) {
        storage.remove((Integer) key);
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