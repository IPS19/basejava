package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private StreamSerializer streamSerializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        Objects.requireNonNull(dir, "directory must not be null");
        this.streamSerializer = streamSerializer;
        directory = Paths.get(dir);

        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::deleteFromStorage);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path searchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public void updateStorage(Path path, Resume r) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid());
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    public void saveToStorage(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Could not create path" + path, getFileName(path), e);
        }

    }

    @Override
    public Resume getFromStorage(Path path) {
        try{
            return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error",getFileName(path),e);
        }
    }

    @Override
    public void deleteFromStorage(Path path) {
        try{
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(path),e);
        }

    }

    @Override
    public List<Resume> getAsList() {
        return getFilesList().map(this::getFromStorage).collect(Collectors.toList());
    }

    private String getFileName(Path path){
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
}

