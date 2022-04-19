package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("resume '" + uuid + "' is not exist",uuid);
    }
}