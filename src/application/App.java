package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        System.out.println();

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==========================================");
        System.out.println(" TEST 1: seller findById ");
        System.out.println("==========================================");
        Seller seller = sellerDao.findById(3); // teste
        System.out.println(seller); 

        System.out.println("\n==========================================");
        System.out.println(" TEST 2: seller findByDepartment ");
        System.out.println("==========================================");
        
        Department department  = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
            System.out.println();
        }

        System.out.println("\n==========================================");
        System.out.println(" TEST 3: seller findAll ");
        System.out.println("==========================================");
        
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
            System.out.println();
        }

    }
}
