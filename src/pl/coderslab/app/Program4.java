package pl.coderslab.app;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Exercise;
import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program4 {
    public static void main(String[] args) {

        SolutionDao solDao = new SolutionDao();
        Scanner scan = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {

            System.out.println("List of solutions:");
            List<Solution> sols = solDao.loadAll();

            for (Solution sol : sols) {
                System.out.println(sol);
            }

            System.out.println("Choose option:");
            System.out.println("[a]dd, [v]iew or [q]uit");

            boolean check = false;
            while (!check) {
                String option = scan.nextLine();
                option = option.toLowerCase().trim();
                switch (option) {
                    case "a":
                    case "add":
                        addSolution();
                        check = true;
                        break;
                    case "v":
                    case "view":
                        viewSolutions();
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

    private static void viewSolutions() {
        UserDao userDao = new UserDao();
        Scanner scan = new Scanner(System.in);
        SolutionDao solutionDao = new SolutionDao();
        listUsers();

        System.out.println("Enter user ID:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        while (true) {
            int userID = scan.nextInt();
            if (userDao.getById(userID) == null ) {
                System.out.println("Wrong ID, try again");
            } else {
                List<Solution> solutions = solutionDao.loadAll();
                for (Solution sol : solutions) {
                    if (sol.getUser().getId() == userID) {
                        System.out.println(sol);
                    }
                }
                break;
            }
        }
        scan.nextLine();

    }

    private static void listUsers() {

        UserDao userDao = new UserDao();
        List<User> list = userDao.loadAll();
        System.out.println("Available users:");
        for (User user : list) {
            System.out.println(user.getId() + " " + user.getUsername());
        }

    }

    public static void listExercises() {

        ExerciseDao exeDao = new ExerciseDao();
        List<Exercise> exes = exeDao.loadAll();
        System.out.println("Available exercises:");
        for (Exercise exe : exes) {
            System.out.println(exe);
        }
    }

    private static void addSolution() {
        Solution solution = new Solution();
        SolutionDao solutionDao = new SolutionDao();
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();

        Scanner scan = new Scanner(System.in);

        listUsers();

        System.out.println("Enter user ID:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        while (true) {
            int userID = scan.nextInt();
            if (userDao.getById(userID) == null ) {
                System.out.println("Wrong ID, try again");
            } else {
                solution.setUser(userDao.getById(userID));
                break;
            }
        }
        scan.nextLine();

        listExercises();

        System.out.println("Enter exercise ID:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        while (true) {
            int exerciseID = scan.nextInt();
            if (exerciseDao.getByIndex(exerciseID) == null ) {
                System.out.println("Wrong ID, try again");
            } else {
                solution.setExercise(exerciseDao.getByIndex(exerciseID));
                break;
            }
        }
        scan.nextLine();

        Date date = new Date();
        solution.setCreated(date);
        solution.setUpdated(null);

        solutionDao.save(solution);
    }



}
