package pl.coderslab.dao;

import pl.coderslab.entity.Exercise;
import pl.coderslab.entity.Solution;
import pl.coderslab.entity.User;
import pl.coderslab.services.DBServicePs;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolutionDao {

    public void save(Solution solution){

        if(solution.getId() == 0){
            addToDb(solution);
        }else{
            updateInDb(solution);
        }

    }

    public Solution getById(int id){
        String query = "Select * from `solution` Where `id` = ?;";
        String[] params = new String[1];
        params[0] = String.valueOf(id);

        return getSingleData(query, params);
    }

    public List<Solution> loadAll(){
        String query = "Select * from `solution`;";

        List<Solution> result = new ArrayList<>();

        try {
            List<String[]> data = DBServicePs.getData(query, null);
            for(String[] row : data){
                Solution solution = createSingleSolutionObject(row);
                result.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void addToDb(Solution solution){

        String query = "Insert into `solution` Values (null,?,?,?,?,?);";
        String[] params = new String[5];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params[0] = sdf.format(solution.getCreated());
        params[1] = sdf.format(solution.getUpdated());
        params[2] = solution.getDescription();
        params[3] = String.valueOf(solution.getExercise().getId());
        params[4] = String.valueOf(solution.getUser().getId());

        try {
            int newId = DBServicePs.executeInsert(query, params);
            solution.setId(newId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateInDb(Solution solution){

        String query = "Update `solution` Set " +
                "`created` = ?," +
                "`updated` = ?," +
                "`description` = ?," +
                "`exercise_id` = ?," +
                "`users_id` = ?," +
                " Where `id` = ?;";

        String[] params = new String[6];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        params[0] = sdf.format(solution.getCreated());
        params[1] = sdf.format(solution.getUpdated());
        params[2] = solution.getDescription();
        params[3] = String.valueOf(solution.getExercise().getId());
        params[4] = String.valueOf(solution.getUser().getId());
        params[5] = String.valueOf(solution.getId());

        try {
            DBServicePs.executeQuery(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private Solution getSingleData(String query, String[] params) {
        try {
            List<String[]> data = DBServicePs.getData(query, params);
            if (data.size() > 0) {
                String[] firstElement = data.get(0);
                return createSingleSolutionObject(firstElement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Solution createSingleSolutionObject(String[] el) {
        int elId = Integer.valueOf(el[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date elCreated = null;
        Date elUpdated = null;
        try {
            elCreated = sdf.parse(el[1]);
            elUpdated = sdf.parse(el[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String elDescription = el[3];
        int elExerciseId = Integer.parseInt(el[4]);
        int elUsersId = Integer.parseInt(el[5]);

        Exercise exercise = null;
        if (elExerciseId != 0) {
            ExerciseDao eDao = new ExerciseDao();
            exercise = eDao.getByIndex(elExerciseId);
        }

        User user = null;
        if (elUsersId != 0) {
            UserDao uDao = new UserDao();
            user = uDao.getById(elUsersId);
        }

        Solution solution = new Solution();
        solution.setId(elId);
        solution.setCreated(elCreated);
        solution.setUpdated(elUpdated);
        solution.setDescription(elDescription);
        solution.setExercise(exercise);
        solution.setUser(user);

        return solution;

    }
}
