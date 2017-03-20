package controllers;

import model.Aktie;
import play.api.db.Database;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import play.db.jpa.JPAApi;
import play.mvc.Result;

/**
 * Created by user on 20.03.2017.
 */
public class DatabaseJPA extends Controller {

    private final JPAApi jpaApi;

    @Inject
    public DatabaseJPA(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    //---------------------------------------------------------------------------------
    // fuer DATABASE wird folgende Config benoetigt:
    // - H:\DATEN\IntelliJ-Play\empty\build.sbt
    // - H:\DATEN\IntelliJ-Play\empty\conf\application.conf
    // - H:\DATEN\IntelliJ-Play\empty\conf\META-INF\persistence.xml  (nur fuer JPA)
    //---------------------------------------------------------------------------------

    // JPA
    // siehe auch H:\DATEN\IntelliJ-Play\play-java-crud\app\controllers\ProductsJPA.java

    @Transactional(readOnly = true)
    public Result findAll() {
        List<Aktie> aktienListe = (List<Aktie>) jpaApi.em().createQuery("select p from Aktie p").getResultList();
        return ok(Json.toJson(aktienListe));
    }

}
