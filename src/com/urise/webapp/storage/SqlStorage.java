package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {

    SqlHelper sqlHelper;

    public SqlStorage(String dbURL, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbURL, dbUser, dbPassword));
    }

    public void clear() {
        sqlHelper.connectAndDo("DELETE FROM resume", (ps) -> {
            ps.execute();
            return true;
        });
    }

    public void save(Resume r) {
        sqlHelper.connectAndDo("INSERT INTO resume(uuid,full_name) VALUES (?,?)", (ps) -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return true;
        });
    }

    public Resume get(String uuid) {
        return sqlHelper.connectAndDo("SELECT *FROM resume as r WHERE r.uuid =?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    public void delete(String uuid) {
        sqlHelper.connectAndDo("DELETE FROM resume WHERE uuid =?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return true;
        });
    }

    public List<Resume> getAllSorted() {
        return sqlHelper.connectAndDo("SELECT * FROM resume as r ORDER BY full_name,uuid", (ps) -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    public int size() {
        return sqlHelper.connectAndDo("SELECT COUNT(uuid) as size FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("size");

        });
    }

    public void update(Resume resume) {
        sqlHelper.connectAndDo("UPDATE resume SET full_name=? WHERE uuid=?", (PreparedStatement ps) -> {

            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (ps.executeUpdate() == 0)
                throw new NotExistStorageException(resume.getUuid());
            return true;
        });
    }

    /*   @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid,full_name) VALUES (?,?)")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

        @Override
        public Resume get(String uuid) {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement ps = conn.prepareStatement("SELECT *FROM resume as r WHERE r.uuid =?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                return new Resume(uuid, rs.getString("full_name"));
            } catch (SQLException e) {
                throw new StorageException(e);
            }
        }
    @Override
    public void delete(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE uuid =?")) {
            ps.setString(1, uuid);
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    @Override
    public List<Resume> getAllSorted() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume as r ORDER BY uuid")) {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString(1), rs.getString(2)));
            }
            return resumes;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    @Override
    public int size() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(uuid) as size FROM resume")) {
            ResultSet rs = ps.executeQuery();
            return rs.getInt("size");
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    @Override
    public void update(Resume resume) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }*/
}