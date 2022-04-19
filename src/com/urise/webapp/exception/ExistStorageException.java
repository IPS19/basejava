package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("resume '" + uuid + "' already exists, type another id", uuid);
    }
}
