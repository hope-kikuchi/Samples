package sample.doma2;

import org.seasar.doma.jdbc.tx.TransactionManager;

public class Main {
    
    public static void main(String[] args) {
        TransactionManager tm = MyConfig.singleton().getTransactionManager();
        TestTableDao dao = new TestTableDaoImpl();
        
        tm.required(() -> {
            dao.findAll().forEach(System.out::println);
        });
    }
}