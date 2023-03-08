package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

   private Connection conn; // dependencia com a conexão

   // força a injenção de dependência
   public SellerDaoJDBC(Connection conn){
      this.conn = conn;
   }

    @Override
    public void insert(Seller obj) {
        
    }

    @Override
    public void update(Seller obj) {
        
        
    }

    @Override
    public void deleteById(Integer id) {
       
    }

    @Override
    public Seller findById(Integer id) {
       PreparedStatement st = null;
       ResultSet rs = null;

       try{

         st = conn.prepareStatement(
              "SELECT seller.*,department.Name as DepName "
            + "FROM seller INNER JOIN department "
            + "ON seller.DepartmentId = department.Id "
            + "WHERE seller.Id = ?");
            // Não esquecer de colocar os espaços no final de cada linha de comando

             
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
            
               Department dep = instantiateDepartment(rs);
               Seller obj = instantiateSeller(rs, dep);
               
               return obj;

            }

            return null;

       }catch(SQLException e){
         throw new DbException(e.getMessage());

       }finally{
         DB.closeResultSet(rs);
         DB.closeStatement(st);
       }
       
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
      
      Seller obj = new Seller();
      obj.setId(rs.getInt("Id"));
      obj.setName(rs.getString("Name"));
      obj.setEmail(rs.getString("Email"));
      obj.setBirthDate(rs.getDate("BirthDate"));
      obj.setBaseSalary(rs.getDouble("BaseSalary"));
      obj.setDepartment(dep);
      
      return obj;
   }

   private Department instantiateDepartment(ResultSet rs) throws SQLException {
      
      Department dep = new Department();
      dep.setId(rs.getInt("DepartmentId"));
      dep.setName(rs.getString("DepName")); // CORREÇÃO --> Name para DepName 

      return dep;
      
   }

   @Override
    public List<Seller> findAll() {
      PreparedStatement st = null;
      ResultSet rs = null;

      try{

         st = conn.prepareStatement(
           "SELECT seller.*,department.Name as DepName "
         + "FROM seller INNER JOIN department "
         + "ON seller.DepartmentId = department.Id "
         + "ORDER BY Name ");

         rs = st.executeQuery();

         List<Seller> list = new ArrayList<>();
         Map<Integer, Department> map = new HashMap<>(); // criado um map vazio

         while(rs.next()){

            Department dep = map.get(rs.getInt("DepartmentId"));

            // so instancia um departamento se ele ainda não existir
            if (dep == null){
               dep = instantiateDepartment(rs);
               map.put(rs.getInt("DepartmentId"), dep); // joga o departmento no map
            }

            // feito dessa forma para que seja criado um departamento com varios vendedores e não um departamento por vendedor, gerando repetições de departamento

            Seller obj = instantiateSeller(rs, dep);
            list.add(obj);

         }
         return list;


      }catch(SQLException e){
         throw new DbException(e.getMessage());

      }finally{
         DB.closeStatement(st);
         DB.closeResultSet(rs);

      }
    }

    // ficou parecido com o findById e findAll com algumas particularidades
   @Override
   public List<Seller> findByDepartment(Department department) {
      PreparedStatement st = null;
      ResultSet rs = null;

      try{

         st = conn.prepareStatement(
           "SELECT seller.*,department.Name as DepName "
         + "FROM seller INNER JOIN department "
         + "ON seller.DepartmentId = department.Id "
         + "WHERE DepartmentId = ? "
         + "ORDER BY Name ");

         st.setInt(1, department.getId());

         rs = st.executeQuery();

         List<Seller> list = new ArrayList<>();
         Map<Integer, Department> map = new HashMap<>(); // criado um map vazio

         while(rs.next()){

            Department dep = map.get(rs.getInt("DepartmentId"));

            // so instancia um departamento se ele ainda não existir
            if (dep == null){
               dep = instantiateDepartment(rs);
               map.put(rs.getInt("DepartmentId"), dep); // joga o departmento no map
            }

            // feito dessa forma para que seja criado um departamento com varios vendedores e não um departamento por vendedor, gerando repetições de departamento

            Seller obj = instantiateSeller(rs, dep);
            list.add(obj);

         }
         return list;


      }catch(SQLException e){
         throw new DbException(e.getMessage());

      }finally{
         DB.closeStatement(st);
         DB.closeResultSet(rs);

      }
   }


    
}
