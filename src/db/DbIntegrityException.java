package db;

public class DbIntegrityException extends RuntimeException {

    // Classe criada para evitar erros de integridade no Banco de Dados

    public DbIntegrityException(String msg) {
        super(msg);
    }
}
