
package model.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOLogin {
    public String userPass(String username, String password) throws SQLException;
    public String userRole(String username) throws SQLException;
}
