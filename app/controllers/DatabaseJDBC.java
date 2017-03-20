package controllers;

import model.Aktie;
import play.api.db.Database;
import play.mvc.Controller;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20.03.2017.
 */
public class DatabaseJDBC extends Controller {

    private static Database db;

    @Inject
    public DatabaseJDBC(Database db) {
        this.db = db;
    }


    // JDBC
    // siehe auch H:\DATEN\IntelliJ-Play\play-java-crud\app\model\ProductJDBC.java
    public static List<Aktie> findAll(){
        // get connection
        Connection connection = db.getConnection();
        Statement stmt = null;

        // aktuelle liste aller produkte
        List<Aktie> aktienListe = new ArrayList<Aktie>();

        try {
            stmt = connection.createStatement();
            String sql = "select * from aktie_t order by id";
            ResultSet rs = stmt.executeQuery(sql);
            // extract data
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int kurs = rs.getInt("kurs");
                // generate new Product
                Aktie aktie = new Aktie(id,name,kurs);
                aktienListe.add(aktie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Aktienliste: " +aktienListe.size());
        return aktienListe;
    }
}
