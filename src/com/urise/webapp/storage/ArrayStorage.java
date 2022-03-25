package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (uuid == null) {
            return;
        }
        if (findIndex(uuid) != -1) {
            System.out.println("резюме с данным id ( " + uuid + " ) уже существует, введите другой id");
            return;
        }
        if (size == storage.length) {
            System.out.println("хранилище переполнено, запись невозможна");
            return;
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("резюме " + uuid + " не найдено");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, (storage.length - index - 1));
            storage[size - 1] = null;
            size--;
        } else
            System.out.println("резюме " + uuid + " не найдено");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void update(Resume resume) {
            int index = findIndex(resume.getUuid());
            if (index != -1) {
                storage[index] = resume;
            } else
                System.out.println("резюме " + resume.getUuid() + " не найдено");
    }

    protected int findIndex(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return i;
            }
        }
        return -1;
    }
}