package com.urise.webapp.storage.serializer;

import java.io.IOException;

public interface Writer<T> {
    void write(T t) throws IOException;
}