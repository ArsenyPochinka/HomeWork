package server;

import java.sql.*;
import java.util.*;

public class AuthenticationService {

    public Optional<String> findUsernameByLoginAndPassword(String login, String password) {

            Connection connection = DatabaseConnector.getConnection();

            try {
                PreparedStatement ps = connection.prepareStatement("SELECT username FROM public.users WHERE login=? AND password=?");
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    return Optional.of(rs.getString("username"));
                }
                else {
                    return Optional.ofNullable(null);
                }
            } catch (SQLException e) {
                throw new RuntimeException("SWW during a fetching operation.", e);
            } finally {
                DatabaseConnector.close(connection);
            }
        }
    }

