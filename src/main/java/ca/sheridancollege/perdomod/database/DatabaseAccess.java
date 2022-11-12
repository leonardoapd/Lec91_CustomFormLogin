package ca.sheridancollege.perdomod.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.perdomod.beans.User;

@Repository
public class DatabaseAccess {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public User findUserAccount(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user WHERE email = :email";
        namedParameters.addValue("email", email);

        ArrayList<User> userList = (ArrayList<User>) jdbc.query(query, namedParameters,
                new BeanPropertyRowMapper<User>(User.class));

        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;

    }

    public List<String> getRolesById(Long userId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId = sec_role.roleId "
                + "AND userId = :userId";
        namedParameters.addValue("userId", userId);
        return jdbc.queryForList(query, namedParameters, String.class);
    }

    // Method to add a new user to the database
    public void addUser(User user) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO sec_user (email, encryptedPassword, enabled) "
                + "VALUES (:email, :encryptedPassword, :enabled)";
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("encryptedPassword", user.getEncryptedPassword());
        namedParameters.addValue("enabled", user.getEnabled());

        // ROLES ID 1 = USER, 2 = GUEST
        Integer ROLEID = 1;

        if (jdbc.update(query, namedParameters) > 0) {
            // Get the user ID of the new user
            Long userId = getLastID();
            System.out.println("User ID: " + userId);
            // Adding the role for the user
            String query2 = "INSERT INTO user_role (userId, roleId) "
                    + "VALUES (:userId, :roleId)";
            namedParameters.addValue("userId", userId);
            namedParameters.addValue("roleId", ROLEID);
            jdbc.update(query2, namedParameters);
            System.out.println("User added to database");
        } else {
            System.out.println("User not added to database");
        }
    }

    // Getting the last ID inserted in the database H2
    public Long getLastID() {

        String query = "SELECT MAX(userId) FROM sec_user";
        return jdbc.queryForObject(query, new MapSqlParameterSource(), Long.class);
    }

}
