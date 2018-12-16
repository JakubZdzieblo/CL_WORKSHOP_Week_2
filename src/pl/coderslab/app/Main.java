package pl.coderslab.app;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.Exercise;
import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {



        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();
        SolutionDao solutionDao = new SolutionDao();
        ExerciseDao exerciseDao = new ExerciseDao();

        userGroupDao.loadAll();
        userGroupDao.loadAll();
        solutionDao.loadAll();
        exerciseDao.loadAll();

        UserGroup standard = new UserGroup("new");
        userGroupDao.save(standard);

        User newUser = new User();
        newUser.setUsername("Kub2");
        newUser.setEmail("kub2@mail.com");
        newUser.setPassword("pasło");
        newUser.setUserGroup(standard);
        userDao.save(newUser);

//        Exercise exe1 = new Exercise();
//        exe1.setTitle("Hmmm");
//        exe1.setDescription("Still Doing it");
//        exerciseDao.save(exe1);
//
//        Solution sol1 = new Solution();
//        sol1.setCreated(new Date(2018, 11, 20, 14, 30, 00));
//        sol1.setUpdated(new Date(2018, 11, 21, 13, 02,21));
//        sol1.setDescription("It works");
//        sol1.setExercise(exe1);
//        sol1.setUser(newUser);
//        solutionDao.save(sol1);




//        UserGroupDao userGroupDao = new UserGroupDao();
//
//        System.out.println("//GET ALL DATA");
//        List<UserGroup> groups = userGroupDao.loadAll();
//        for(UserGroup group : groups){
//            System.out.println(group.getId());
//            System.out.println(group.getName());
//            System.out.println("====");
//        }
//
//        System.out.println("//GET SINGLE ELEMENT BY ID");
//        UserGroup group = userGroupDao.getById(2);
//        System.out.println(group.getId());
//        System.out.println(group.getName());
//
//        System.out.println("//DELETE NEW GROUP");
//        UserGroup elementToDelete = userGroupDao.getByName("New group 3");
//        userGroupDao.delete(elementToDelete);
//
//        System.out.println("//Create NEW GROUP");
//        UserGroup groupToAdd = new UserGroup("New group 3");
//        userGroupDao.save(groupToAdd);
//
//        UserGroup groupToUpdate = userGroupDao.getById(5);
//        groupToUpdate.setName("Updated");
//        userGroupDao.save(groupToUpdate);
//
//
////        //CREATE NEW USER
////        User userToAdd = new User();
////        userToAdd.setUsername("Darek");
////        userToAdd.setPassword("NiePodamCi");
////        userToAdd.setEmail("darek@mail.com");
////        userToAdd.setUserGroup( userGroupDao.getByName("Admin") );
////
////        //Save new user object to DB
////        UserDao userDao = new UserDao();
////        userDao.save(userToAdd);
//
//
//        //Get user from DB
//        UserDao userDao = new UserDao();
//        User userDarek = userDao.getByUsername("Darek");
//
//        System.out.println(userDarek);
//        System.out.println("Is member of group "+userDarek.getUserGroup());



    }

}
