package pl.coderslab.dao;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;
import pl.coderslab.services.DBServicePs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public void save(User user){

        if(user.getId() == 0){
            addToDb(user);
        }else{
            updateInDb(user);
        }

    }


    public User getById(int id){
        String query = "Select * from `users` Where `id` = ?;";
        String[] params = new String[1];
        params[0] = String.valueOf(id);

        return getSingleData(query, params);
    }

    public User getByUsername(String name){
        String query = "Select * from `users` Where `username` = ?;";
        String[] params = new String[1];
        params[0] = name;

        return getSingleData(query, params);
    }


    public List<User> loadAll(){
        String query = "Select * from `users`;";

        //prepare list for data from DB
        List<User> result = new ArrayList<>();

        try {
            List<String[]> data = DBServicePs.getData(query, null);

            for(String[] row : data){
                User user = createSingleUserObject(row);
                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    private void addToDb(User user){

        String query = "Insert into `users` Values (null,?,?,?,?);";
        String[] params = new String[4];
        params[0] = String.valueOf(user.getUsername());
        params[1] = user.getEmail();
        params[2] = user.getPassword();
        params[3] = String.valueOf(user.getUserGroup().getId());

        try {
            int newId = DBServicePs.executeInsert(query, params);
            user.setId(newId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateInDb(User user){

        String query = "Update `users` Set " +
                "`username` = ?," +
                "`email` = ?," +
                "`password` = ?," +
                "`user_group_id` = ?" +
                " Where `id` = ?;";

        String[] params = new String[5];
        params[0] =user.getUsername();
        params[1] = user.getEmail();
        //TODO - encode if changed
        params[2] = user.getPassword();
        params[3] = String.valueOf(user.getUserGroup().getId());
        params[4] = String.valueOf(user.getId());

        try {
            DBServicePs.executeQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(User user){
        String query = "Delete from `users` where `id` = ?;";
        String[] params = new String[1];
        params[0] = String.valueOf(user.getId());

        try {
            DBServicePs.executeQuery(query, params);
            user = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        User user = getById(id);
        delete(user);
    }


    private User getSingleData(String query, String[] params) {
        try {
            List<String[]> data = DBServicePs.getData(query, params);
            if (data.size() > 0) {
                String[] firstElement = data.get(0);
                return createSingleUserObject(firstElement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createSingleUserObject(String[] element) {

        int elementId = Integer.valueOf(element[0]);
        String elementUsername = element[1];
        String elementEmail = element[2];
        String elementPassword = element[3];
        int elementGroupId = Integer.parseInt(element[4]);

        UserGroup group = null;
        if (elementGroupId != 0){
            UserGroupDao userGroupDao = new UserGroupDao();
            group = userGroupDao.getById(elementGroupId);
        }

        User user = new User();
        user.setId(elementId);
        user.setUsername(elementUsername);
        user.setEmail(elementEmail);
        user.setPassword(elementPassword);
        user.setUserGroup(group);
        return user;
    }

}
