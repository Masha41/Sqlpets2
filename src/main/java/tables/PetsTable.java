package tables;

import db.MySQLConnector;
import objects.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PetsTable extends AbsTable{


    public PetsTable() {
        super("Pet");
        columns = new HashMap<>();
        columns.put("id", "INT PRIMARY KEY AUTO_INCREMENT");
        columns.put("name", "varchar(100)");
        columns.put("gender", "varchar(10)");
        columns.put("age", "INT");
        create();
    }

    public ArrayList<Pet> selectAll() {
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return resultSetToArray(sqlQuery);
    }


    public void selectAllWoman()  {
        String sqlQuery = String.format("SELECT * FROM %s WHERE gender = 'жен' ", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                System.out.println("Девочки: " +
                        rs.getString(1) + "/" +
                        rs.getString(2) + "/" +
                        rs.getString(3) + "/"+
                        rs.getString(4)
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private ArrayList<Pet> resultSetToArray(String sqlQuery) {
        ArrayList<Pet> result = new ArrayList<>();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                result.add(
                        new Pet(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("gender"),
                                rs.getInt("age")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void insert(Pet pet){
        String sqlRequest = String.format("INSERT INTO %s (name, gender, age) VALUES ('%s', '%s', %d);",
                tableName, pet.getName(), pet.getGender(), pet.getAge());
        db.executeRequest(sqlRequest);
    }
    public void update(Pet pet) {
        String sqlQuery = String.format("UPDATE Pet SET " +
                "age = 12  WHERE id = '3' ");
        db.executeRequest(sqlQuery);
    }

}
