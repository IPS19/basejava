package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected int searchKey(Object key) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(key))
                return i;
        }
        return -1;
    }

    @Override
    protected boolean isExist(String uuid) {
        int index = searchKey(uuid);
        return index >= 0;
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
    public final Resume getFromStorage(Object arg) {
        return storage.get(searchKey(arg));
    }

    @Override
    public void updateStorage(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    public void deleteFromStorage(int index, String uuid) {
        storage.remove(index);
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