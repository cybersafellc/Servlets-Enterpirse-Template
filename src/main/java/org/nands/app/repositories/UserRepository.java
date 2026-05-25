// src/main/java/org/example/app/repositories/UserRepository.java

package org.nands.app.repositories;

import org.nands.app.config.DatabaseConfig;
import org.nands.app.exceptions.DatabaseException;
import org.nands.app.models.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {

    private static final Logger log =
            LoggerFactory.getLogger(
                    UserRepository.class
            );

    private final DataSource dataSource;

    public UserRepository() {

        this.dataSource =
                DatabaseConfig.getDataSource();
    }

    /**
     * CREATE USER
     */
    public void save(User user) {

        String sql = """
                INSERT INTO users (
                    id,
                    email,
                    password,
                    name,
                    role
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    user.getId()
            );

            ps.setString(
                    2,
                    user.getEmail()
            );

            ps.setString(
                    3,
                    user.getPassword()
            );

            ps.setString(
                    4,
                    user.getName()
            );

            ps.setString(
                    5,
                    user.getRole()
            );

            ps.executeUpdate();

            log.info(
                    "User created: {}",
                    user.getEmail()
            );

        } catch (SQLException e) {

            log.error(
                    "Failed create user",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * FIND BY ID
     */
    public User findById(
            String id) {

        String sql = """
                SELECT *
                FROM users
                WHERE id = ?
                LIMIT 1
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, id);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return mapUser(rs);
                }

                return null;
            }

        } catch (SQLException e) {

            log.error(
                    "Failed find user by id",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * FIND BY EMAIL
     */
    public User findByEmail(
            String email) {

        String sql = """
                SELECT *
                FROM users
                WHERE email = ?
                LIMIT 1
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return mapUser(rs);
                }

                return null;
            }

        } catch (SQLException e) {

            log.error(
                    "Failed find user by email",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * FIND ALL
     */
    public List<User> findAll() {

        String sql = """
                SELECT *
                FROM users
                ORDER BY created_at DESC
                """;

        List<User> users =
                new ArrayList<>();

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                users.add(
                        mapUser(rs)
                );
            }

            return users;

        } catch (SQLException e) {

            log.error(
                    "Failed find all users",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * UPDATE USER
     */
    public void update(User user) {

        String sql = """
                UPDATE users
                SET
                    email = ?,
                    password = ?,
                    name = ?,
                    role = ?
                WHERE id = ?
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    user.getEmail()
            );

            ps.setString(
                    2,
                    user.getPassword()
            );

            ps.setString(
                    3,
                    user.getName()
            );

            ps.setString(
                    4,
                    user.getRole()
            );

            ps.setString(
                    5,
                    user.getId()
            );

            ps.executeUpdate();

            log.info(
                    "User updated: {}",
                    user.getEmail()
            );

        } catch (SQLException e) {

            log.error(
                    "Failed update user",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * DELETE USER
     */
    public void deleteById(
            String id) {

        String sql = """
                DELETE FROM users
                WHERE id = ?
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, id);

            ps.executeUpdate();

            log.info(
                    "User deleted: {}",
                    id
            );

        } catch (SQLException e) {

            log.error(
                    "Failed delete user",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * EXISTS EMAIL
     */
    public boolean existsByEmail(
            String email) {

        String sql = """
                SELECT 1
                FROM users
                WHERE email = ?
                LIMIT 1
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            log.error(
                    "Failed exists email",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * COUNT USERS
     */
    public long count() {

        String sql = """
                SELECT COUNT(*)
                FROM users
                """;

        try (
                Connection conn =
                        dataSource.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getLong(1);
            }

            return 0;

        } catch (SQLException e) {

            log.error(
                    "Failed count users",
                    e
            );

            throw new DatabaseException(e);
        }
    }

    /**
     * MAP RESULTSET TO USER OBJECT
     */
    private User mapUser(
            ResultSet rs)
            throws SQLException {

        User user = new User();

        user.setId(
                rs.getString("id")
        );

        user.setEmail(
                rs.getString("email")
        );

        user.setPassword(
                rs.getString("password")
        );

        user.setName(
                rs.getString("name")
        );

        user.setRole(
                rs.getString("role")
        );

        return user;
    }

    /**
     * HELPER CREATE USER
     */
    public static User create(
            String email,
            String password,
            String name,
            String role) {

        User user = new User();

        user.setId(
                UUID.randomUUID().toString()
        );

        user.setEmail(email);

        user.setPassword(password);

        user.setName(name);

        user.setRole(role);

        return user;
    }
}