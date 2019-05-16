package database.jpa;

import com.google.inject.Guice;
import com.google.inject.Injector;
import database.gamer.Gamer;
import database.gamer.GamerDao;
import guice.PersistenceModule;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Egy osztály, amely adatbázismódosító függvényeket szolgáltat.
 */
public class DBTools {
    /**
     * Egy {@code GamerDao} objektum.
     */
    private GamerDao gmd;

    public DBTools() {
        Injector injector = Guice.createInjector(new PersistenceModule("Gamer"));
        gmd = injector.getInstance(GamerDao.class);
    }
    /**
     * Beszúr egy sort a játékos adataival az adatbázisba, ha az még nincs jelen.
     * @param gamer egy {@link Gamer} objektum, ami a játékos adatait szolgáltatja.
     */
    public void addGamer(Gamer gamer) {
        Gamer tmp = null;
        List<Gamer> list=gmd.findAll();

        for (Gamer g : list)
            if(g.getName().equals(gamer.getName()))
                tmp = g;

        if(tmp == null)
            gmd.persist(gamer);
    }

    /**
     * Frissít egy sort az adatbázisban, ami a megadott {@code gamer} adatait  tartalmazza.
     * @param gamer egy {@link Gamer} objektum, ami a játékos adatait szolgáltatja.
     * @param newScore egy {@code int} érték, amellyel a {@code gamer} score értéke növelődni fog
     */
    public void updateGamer(Gamer gamer, int newScore) {
        Gamer tmp = null;
        List<Gamer> list=gmd.findAll();

        for (Gamer g : list)
            if (g.getName().equals(gamer.getName()) )
                tmp = g;

        tmp.setScore(tmp.getScore()+newScore);
        gmd.update(tmp);
    }

    /**
     * Ez a függvény lekér az adatbázisból minden játékost, aztán kiválasztja a legjobb 10-et pontszám alapján és
     * rendezi pontszám alapján csökkenő sorrendben.
     * @return egy {@code List<Gamer>} típusú objektumot ad vissza, ami a 10 legjobb játékost tartalmazza
     */
    public List<Gamer> getScoreboard() {
        return gmd.findAll().stream().sorted(Comparator.comparingInt(Gamer::getScore).reversed()).limit(10).collect(Collectors.toList());
    }
}
