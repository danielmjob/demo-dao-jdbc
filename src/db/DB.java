package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
    // Classe carrega as propriedades do arquivo db.properties

    private static Connection conn = null; // importar do sql

    // Abrindo conexão com Banco de Dados
    public static Connection getConnection() {
        if (conn == null) {
            // se estiver nulo conecte ao banco
           
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dbdburl"); // que esta declarada no db.properties
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }

        return conn;
    }

    // Fechando conexão com Banco de Dados
    public static void closeConnection() {
        if (conn != null) {
           
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
   
        }
    }

    //carregando propriedades para conexão
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    // evitando try catch exagerados no código principal

    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
             throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs!= null){
            try {
                rs.close();
            } catch (SQLException e) {
             
                throw new DbException(e.getMessage());
            }
        }
    }





}
