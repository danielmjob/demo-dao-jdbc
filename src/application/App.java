package application;

import java.util.Date;
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

        System.out.println("\n==========================================");
        System.out.println(" TEST 4: seller INSERT ");
        System.out.println("==========================================");

        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

    }
}
