package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class App2 {
    public static void main(String[] args) {
        System.out.println();

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();


        System.out.println("\n==========================================");
        System.out.println(" TEST 1: seller findById  ");
        System.out.println("==========================================");

        Department dep1 = departmentDao.findById(6);
        System.out.println(dep1);

        System.out.println("\n==========================================");
        System.out.println(" TEST 2: seller findAll  ");
        System.out.println("==========================================");

        List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}


        System.out.println("\n==========================================");
        System.out.println(" TEST 3: seller INSERT ");
        System.out.println("==========================================");

        Department newDepartment = new Department(null, "Music");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());


        System.out.println("\n==========================================");
        System.out.println(" TEST 4: seller UPDATE ");
        System.out.println("==========================================");

        Department dep2 = departmentDao.findById(7); // localizando o departamento que será alterado - pelo Id
        dep2.setName("Others");
        departmentDao.update(dep2);
        System.out.println("Update completed");

        
        System.out.println("\n==========================================");
        System.out.println(" TEST 5: seller DELETE ");
        System.out.println("==========================================");
        System.out.print("Enter id for delete test: ");
        Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
        sc.close();
    }

}
