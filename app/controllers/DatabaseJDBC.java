package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Aktie;
import play.api.db.Database;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

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

    //---------------------------------------------------------------------------------
    // fuer DATABASE wird folgende Config benoetigt:
    // - H:\DATEN\IntelliJ-Play\empty\build.sbt
    // - H:\DATEN\IntelliJ-Play\empty\conf\application.conf
    // - H:\DATEN\IntelliJ-Play\empty\conf\META-INF\persistence.xml  (nur fuer JPA)
    //---------------------------------------------------------------------------------
    // Generell ist wichtig:
    // - Controller Methode muss vom Return Type RESULT sein, sonst Fehler im Routing:
    //     "...routes:10: value findAll is not a member of controllers.DatabaseJDBC"
    // Fuer JPA ist folgendes wichtig:
    // - @Entity Klasse muss muss ZWINGEND einen leerer Constructor haben, sonst:
    //    Caused by: org.hibernate.InstantiationException: No default constructor for entity:  : model.Aktie
    //---------------------------------------------------------------------------------

    // JDBC
    // siehe auch H:\DATEN\IntelliJ-Play\play-java-crud\app\model\ProductJDBC.java

    // Achtung der Fehler "...routes:10: value findAll is not a member of controllers.DatabaseJDBC"
    // hat nichts mit dem Routing hier zu tun, sondern mit der Controller Methode! Muss vom Returntyp RESULT sein!!

    public Result findAll(){
        // get connection
        Connection connection = db.getConnection();
        Statement stmt = null;

        // aktuelle liste aller produkte
        List<Aktie> aktienListe = new ArrayList<Aktie>();

        try {
            stmt = connection.createStatement();
            String sql = "select * from aktie order by id";
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

        //return aktienListe;
        //
        // JsonNode : Jackson
        // Json: Json Helper aus play.libs
        JsonNode json = Json.toJson(aktienListe);

        return ok(json);
    }
}
