package pl.coderslab.app;

import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {

            System.out.println("List of users:");
            List<User> users = userDao.loadAll();

            for (User user : users) {
                System.out.println("ID:" + user.getId() + ", " + user.getUsername());
            }

            System.out.println("Choose option:");
            System.out.println("[a]dd, [e]dit, [d]elete or [q]uit");

            boolean check = false;
            while (!check) {
                String option = scan.nextLine();
                option = option.toLowerCase().trim();
                switch (option) {
                    case "a":
                    case "add":
                        addUser();
                        check = true;
                        break;
                    case "e":
                    case "edit":
                        editUser();
                        check = true;
                        break;
                    case "d":
                    case "delete":
                        deleteUser();
                        check = true;
                        break;
                    case "q":
                    case "quit":
                        System.out.println("Bye!");
                        check = true;
                        quit = true;
                        break;
                    default:
                        System.out.println("Try again!");
                        break;
                }
            }
        }
    }

    private static void deleteUser() {
        UserDao userDao = new UserDao();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id of the user to delete:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        userDao.delete(scan.nextInt());
        scan.nextLine();
    }

    private static void addUser() {
        User user = new User();
        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new user name:");
        user.setUsername(scan.nextLine());
        System.out.println("Enter password:");
        user.setPassword(scan.nextLine());
        System.out.println("Enter email:");
        user.setEmail(scan.nextLine());
        System.out.println("Enter user group number:");
        while (true) {
            int userGroupID = scan.nextInt();
            if (userGroupDao.getById(userGroupID) == null ) {
                System.out.println("Wrong ID, try again");
            } else {
                user.setUserGroup(userGroupDao.getById(userGroupID));
                break;
            }
        }
        userDao.save(user);
    }

    private static void editUser() {
        User user = new User();
        UserDao userDao = new UserDao();
        UserGroupDao userGroupDao = new UserGroupDao();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id of the user to edit:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        user.setId(scan.nextInt());
        scan.nextLine();
        System.out.println("Enter new user name:");
        user.setUsername(scan.nextLine());
        System.out.println("Enter password:");
        user.setPassword(scan.nextLine());
        System.out.println("Enter email:");
        user.setEmail(scan.nextLine());
        System.out.println("Enter user group number:");
        while (true) {
            int userGroupID = scan.nextInt();
            if (userGroupDao.getById(userGroupID) == null ) {
            System.out.println("Wrong ID, try again");
            } else {
            user.setUserGroup(userGroupDao.getById(userGroupID));
            break;
            }

        }
        userDao.save(user);
    }

}
