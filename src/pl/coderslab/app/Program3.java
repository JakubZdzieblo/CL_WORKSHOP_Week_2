package pl.coderslab.app;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.UserGroup;

import java.util.List;
import java.util.Scanner;

public class Program3 {
    public static void main(String[] args) {

        UserGroupDao ugDao = new UserGroupDao();
        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {

            System.out.println("List of groups:");
            List<UserGroup> ugList = ugDao.loadAll();

            for (UserGroup ug : ugList) {
                System.out.println(ug);
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
                        addGroup();
                        check = true;
                        break;
                    case "e":
                    case "edit":
                        editGroup();
                        check = true;
                        break;
                    case "d":
                    case "delete":
                        deleteGroup();
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

    private static void deleteGroup() {
        UserGroupDao ugDao = new UserGroupDao();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id of the group to delete:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        ugDao.delete(scan.nextInt());
        scan.nextLine();
    }

    private static void addGroup() {
        UserGroupDao ugDao = new UserGroupDao();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new user group name:");
        UserGroup ug = new UserGroup(scan.nextLine());
        ugDao.save(ug);
    }

    private static void editGroup() {
        UserGroupDao ugDao = new UserGroupDao();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id of the group to edit:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        int newId = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter new group name:");
        UserGroup ug = new UserGroup(newId, scan.nextLine());
        ugDao.save(ug);
    }

}
