package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;
import com.urise.webapp.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbURL, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbURL, dbUser, dbPassword);
    }


    public void clear() {
        new SqlHelper(connectionFactory).connectAndDo("DELETE FROM resume", (ps) -> {
            ps.execute();
            return true;
        });
    }

    public void save(Resume r) {
        new SqlHelper(connectionFactory).connectAndDo("INSERT INTO resume(uuid,full_name) VALUES (?,?)", (ps) -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return true;
        });
    }

    public Resume get(String uuid) {
        return new SqlHelper(connectionFactory).connectAndDo("SELECT *FROM resume as r WHERE r.uuid =?", (ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    public void delete(String uuid) {
        new SqlHelper(connectionFactory).connectAndDo("DELETE FROM resume WHERE uuid =?", (ps) -> {
            ps.setString(1, uuid);
            ps.execute();
            return true;
        });
    }

    public List<Resume> getAllSorted() {
        return new SqlHelper(connectionFactory).connectAndDo("SELECT * FROM resume as r ORDER BY uuid", (ps) -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString(1), rs.getString(2)));
            }
            return resumes;
        });
    }

    public int size() {
        return new SqlHelper(connectionFactory).connectAndDo("SELECT COUNT(uuid) as size FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.getInt("size");
        });
    }

    public void update(Resume resume) {
        new SqlHelper(connectionFactory).connectAndDo("UPDATE resume SET full_name=? WHERE uuid=?",(PreparedStatement ps) -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.execute();
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