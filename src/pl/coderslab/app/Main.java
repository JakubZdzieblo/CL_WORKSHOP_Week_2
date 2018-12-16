package pl.coderslab.app;

import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserGroupDao userGroupDao = new UserGroupDao();

        System.out.println("//GET ALL DATA");
        List<UserGroup> groups = userGroupDao.loadAll();
        for(UserGroup group : groups){
            System.out.println(group.getId());
            System.out.println(group.getName());
            System.out.println("====");
        }

        System.out.println("//GET SINGLE ELEMENT BY ID");
        UserGroup group = userGroupDao.getById(2);
        System.out.println(group.getId());
        System.out.println(group.getName());

        System.out.println("//DELETE NEW GROUP");
        UserGroup elementToDelete = userGroupDao.getByName("New group 3");
        userGroupDao.delete(elementToDelete);

        System.out.println("//Create NEW GROUP");
        UserGroup groupToAdd = new UserGroup("New group 3");
        userGroupDao.save(groupToAdd);

        UserGroup groupToUpdate = userGroupDao.getById(5);
        groupToUpdate.setName("Updated");
        userGroupDao.save(groupToUpdate);


//        //CREATE NEW USER
//        User userToAdd = new User();
//        userToAdd.setUsername("Darek");
//        userToAdd.setPassword("NiePodamCi");
//        userToAdd.setEmail("darek@mail.com");
//        userToAdd.setUserGroup( userGroupDao.getByName("Admin") );
//
//        //Save new user object to DB
//        UserDao userDao = new UserDao();
//        userDao.save(userToAdd);


        //Get user from DB
        UserDao userDao = new UserDao();
        User userDarek = userDao.getByUsername("Darek");

        System.out.println(userDarek);
        System.out.println("Is member of group "+userDarek.getUserGroup());



    }

}
