package pl.coderslab.fixtures;

import pl.coderslab.services.DBService;
import pl.coderslab.services.DBServicePs;

import java.sql.SQLException;

public class InitApp {

    private String queryUserGroupTable ="Create table `user_group` (" +
            "`id` int auto_increment," +
            "`name` varchar(255) unique," +
            "Primary key(`id`)" +
            ")";

    private String queryUsersTable = "Create table `users` (" +
            "`id` int auto_increment," +
            "`username` varchar(255) unique," +
            "`email` varchar(255)," +
            "`password` varchar(255)," +
            "`user_group_id` int," +
            "Primary key(`id`)," +
            "Foreign key(`user_group_id`) References `user_group`(`id`)" +
            ");";

    public String queryExerciseTable = "create table exercise (\n" +
            "  id int(11) auto_increment,\n" +
            "  title varchar(255),\n" +
            "  description text,\n" +
            "  primary key (id)\n" +
            ");";

    private String querySolutionTable = "create table `solution` (\n" +
            "  `id` int(11) auto_increment,\n" +
            "  `created` datetime,\n" +
            "  `updated` datetime,\n" +
            "  `description` text,\n" +
            "  `exercise_id` int(11),\n" +
            "  `users_id` bigint(20),\n" +
            "  primary key (id),\n" +
            "  foreign key (exercise_id) references exercise(id),\n" +
            "  foreign key (users_id) references users(id)\n" +
            ")";


    private String queryUsersGroup = "Insert into `user_group` values (1,'Admin'), (2,'User');";

    public static void main(String[] args) {
        DBServicePs.createDB();

        InitApp init = new InitApp();
        init.createTables();
        init.initStartupData();

    }

    public void createTables(){
        createTable(queryUserGroupTable);
        createTable(queryUsersTable);
        createTable(queryExerciseTable);
        createTable(querySolutionTable);
    }

    public void initStartupData(){
        //init groups
        initData(queryUsersGroup);


    }

    private void createTable(String query){
        try {
            DBServicePs.executeQuery(query, null);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void initData(String query){
        try {
            DBServicePs.executeQuery(query, null);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }








}
