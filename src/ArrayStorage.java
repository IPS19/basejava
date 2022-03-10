import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
                storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                System.out.println("резюме с данным id уже существует, введите другой id");
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        System.out.println("резюме не найдено");
        return null;
    }

    void delete(String uuid) {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                isExist = true;
                while (storage[i + 1] != null) {
                    storage[i] = storage[i + 1];
                    i++;
                }
                    storage[i] = null;
                size--;
            }
        }
        if (!isExist) {
            System.out.println("резюме не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    int size() {
        return size;
    }
}