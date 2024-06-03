package tables;

import db.MySQLConnector;
import objects.Pet;

import java.util.ArrayList;

public class Main {
 public static void main(String[] args) {
    try {

        PetsTable petsTable;
        petsTable = new PetsTable();


        ArrayList<Pet> pets = petsTable.selectAll();
        if (pets.isEmpty()) {
            petsTable.insert(new Pet("Муся", "муж", 1));
            petsTable.insert(new Pet("Буся", "жен", 3));
            petsTable.insert(new Pet("Муся", "жен", 5));


        }

        petsTable.selectAll();
        System.out.println("выбрать всех");


        petsTable.selectAllWoman();
        System.out.println("---");




        pets.get(2).setAge(4);
        petsTable.update(pets.get(2));
        System.out.println("----------обновили-возраст Муси");



    } finally {
        MySQLConnector.close();
    }
}
}