package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("резюме с данным id '" + uuid + "' уже существует, введите другой id", uuid);
    }
}
