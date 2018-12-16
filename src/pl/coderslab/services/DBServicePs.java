package pl.coderslab.services;

import java.sql.SQLException;
import java.util.List;

public class DBServicePs {

    private static String dbName = "programming_school";

    public static void createDB() {
        String query = "Create database `"+dbName+"` Character set utf8 collate utf8_unicode_ci";
        try {
            DBService.executeQuery(query, null,"");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static int executeInsert(String query, String[] params) throws Exception {
       return DBService.executeInsert(query,params, dbName);
    }

    public static void executeQuery(String query, String[] params) throws SQLException {
        DBService.executeQuery(query, params, dbName);
    }

    public static List<String[]> getData(String query, String[] params) throws SQLException {
        return DBService.getData(query, params, dbName);
    }
}