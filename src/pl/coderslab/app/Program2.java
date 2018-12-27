package pl.coderslab.app;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.entity.Exercise;
import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {

        ExerciseDao exerciseDao = new ExerciseDao();
        Scanner scan = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {

            System.out.println("List of exercises:");
            List<Exercise> exerciseList = exerciseDao.loadAll();

            for (Exercise exe : exerciseList) {
                System.out.println(exe);
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
                        addExercise();
                        check = true;
                        break;
                    case "e":
                    case "edit":
                        editExercise();
                        check = true;
                        break;
                    case "d":
                    case "delete":
                        deleteExercise();
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
        private static void deleteExercise() {
            ExerciseDao exeDao = new ExerciseDao();
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter id of the exercise to delete:");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("You must enter a number");
            }
            exeDao.delete(scan.nextInt());
            scan.nextLine();
        }

        private static void addExercise() {
            Exercise exercise = new Exercise();
            ExerciseDao exeDao = new ExerciseDao();

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter new exercise title:");
            exercise.setTitle(scan.nextLine());
            System.out.println("Enter description:");
            exercise.setDescription(scan.nextLine());
            exeDao.save(exercise);
        }

        private static void editExercise() {
            Exercise exercise = new Exercise();
            ExerciseDao exeDao = new ExerciseDao();
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter id of the exercise to edit:");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("You must enter a number");
            }
            exercise.setId(scan.nextInt());
            scan.nextLine();
            System.out.println("Enter new exercise title:");
            exercise.setTitle(scan.nextLine());
            System.out.println("Enter description:");
            exercise.setDescription(scan.nextLine());
            exeDao.save(exercise);
        }





}
