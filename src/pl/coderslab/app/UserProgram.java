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

public class UserProgram {

    public static void main(String[] args) {


        if (args.length == 0) {
            System.out.println("No parameters");
            return;
        }
        int userID = Integer.parseInt(args[0]);
        Scanner scan = new Scanner(System.in);
        boolean exit = false;



        while (!exit) {

            System.out.println("Choose option:");
            System.out.println("[a]dd - add a solution");
            System.out.println("[v]iew - see your solutions");
            System.out.println("[q]uit - exit program");

            switch (scan.nextLine().toLowerCase().trim()) {
                case "a" : case "add":
                    addSolution(userID);
                    System.out.println();
                    break;
                case "v" : case "view":
                    viewSolutions(userID);
                    System.out.println();
                    break;
                case "q" : case "quit":
                    System.out.println("Bye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Something went wrong. Try again.");
                    System.out.println();
                    break;
            }
        }

    }

    private static void viewSolutions(int userID) {

        SolutionDao solutionDao = new SolutionDao();

        List<Solution> solutions = solutionDao.loadAll();

        System.out.println("Your solutions:");

        for (Solution sol : solutions) {
            if (sol.getUser().getId() == userID) {
                System.out.println(sol);
            }
        }

    }

    private static void addSolution(int userID) {

        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scan = new Scanner(System.in);
        List<Exercise> exercises = exerciseDao.loadExcluded(userID);

        System.out.println("Please choose from these:");
        for (Exercise exe : exercises) {
            System.out.println(exe);
        }
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("You must enter a number");
        }
        int exeId = scan.nextInt();
        scan.nextLine();

        boolean exists = false;
        for (Exercise exe : exercises) {
            if (exe.getId() == exeId) {
                exists = true;
            }
        }
        if (!exists) {
            System.out.println("ID out of range");
            return;
        }

        System.out.println("Please type in description of your solution:");
        String solDesc = scan.nextLine();
        UserDao userDao = new UserDao();
        Solution solution = new Solution();
        solution.setUser(userDao.getById(userID));
        solution.setExercise(exerciseDao.getByIndex(exeId));
        solution.setCreated(new Date());
        solution.setDescription(solDesc);

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.save(solution);

        System.out.println("Solution saved");

    }


}
