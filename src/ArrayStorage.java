import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null)
                storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                if (storage[i].uuid.equals(r.uuid)) {
                    System.out.println("резюме с данным id уже существует, введите другой id");
                    return;
                }
            }
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume[] get = getAll();
        for (Resume g : get) {
            if (g.uuid.equals(uuid))
                return g;
        }
        System.out.println("резюме не найдено");
        return null;
    }

    void delete(String uuid) {
        boolean isExist = false;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return;
            }
            if (storage[i].uuid.equals(uuid)) {
                isExist = true;
                storage[i] = null;
                if (storage[i + 1] != null) {
                    while (storage[i + 1] != null) {
                        Resume temp = storage[i + 1];
                        storage[i] = temp;
                        i++;
                    }
                    storage[i] = null;
                }
            }
            if (!isExist) {
                System.out.println("резюме не найдено");
                return;
            }

        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null)
                size++;
        }
        return size;
    }
}