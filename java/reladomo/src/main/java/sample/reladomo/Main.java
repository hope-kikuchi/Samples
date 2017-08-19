package sample.reladomo;

import com.gs.fw.common.mithra.MithraManagerProvider;
import com.gs.fw.common.mithra.finder.Operation;
import org.h2.tools.RunScript;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws Exception {
        prepareDatabase();
        prepareConnectionManager();
        
        // list1, 2 を findMany() で取得
        SampleTableList list1 = SampleTableFinder.findMany(SampleTableFinder.all());
        SampleTableList list2 = SampleTableFinder.findMany(SampleTableFinder.all());

        // list1 だけ内容を出力
        System.out.println("[list1]");
        System.out.println(list1);
        
        // one, two を登録
        MithraManagerProvider.getMithraManager().executeTransactionalCommand(tx -> {
            insertSampleTable("one");
            insertSampleTable("two");

            return null;
        });
        
        // list1, 2 の内容を出力
        System.out.println("[list1]");
        System.out.println(list1);

        System.out.println("[list2]");
        System.out.println(list2);

        // three を登録
        MithraManagerProvider.getMithraManager().executeTransactionalCommand(tx -> {
            insertSampleTable("three");

            return null;
        });

        // list2 だけ内容を出力
        System.out.println("[list2]");
        System.out.println(list2);
    }
    
    private static void insertSampleTable(String name) {
        SampleTable sampleTable = new SampleTable();
        sampleTable.setName(name);
        sampleTable.insert();
    }
    
    private static void prepareDatabase() {
        try (
            Connection con = MyConnectionManager.getInstance().getConnection();
            Reader ddl = new InputStreamReader(Main.class.getResourceAsStream("/sql/ddl/create_tables.sql"));
        ) {
            RunScript.execute(con, ddl);
        } catch (SQLException | IOException e) {
            throw new RuntimeException("failed to prepare database.", e);
        }
    }

    private static void prepareConnectionManager() {
        try (InputStream config = Main.class.getResourceAsStream("/ReladomoConfig.xml")) {
            MithraManagerProvider.getMithraManager().readConfiguration(config);
        } catch (IOException e) {
            throw new RuntimeException("failed to prepare ConnectionManager", e);
        }
    }
}