package sug.lambdatest.db;

import sug.lambdatest.model.*;

import java.util.*;

import static java.util.Arrays.asList;

public class DataBase {

    private static final DataBase INSTANCE = new DataBase();

    private final List<Route> routes;
    private final List<TimeTable> timeTables;

    public DataBase() {
        routes = asList(toVe1, toVe2, toMi, miNa1, miNa2, boRm, miRm, veTo1, veTo2, miTo, naMi1, naMi2, rmBo, rmMi);
        timeTables = generateTimeTable();
    }

    public static DataBase connect() {
        return INSTANCE;
    }

    public List<Route> getAllRoutes() {
        return routes;
    }

    public List<TimeTable> getAllTimeTables() {
        return timeTables;
    }

    private List<TimeTable> generateTimeTable() {
        Random r = new Random(0);
        List<TimeTable> timeTables = new ArrayList<TimeTable>();
        int i = 0;
        for (Route route : routes) {
            int hour = r.nextInt(14);
            int min = r.nextInt(60);
            int duration = r.nextInt(300) + 60;
            timeTables.add( new TimeTable(i++, route, new Time(hour, min), new Time(hour, min).after(duration)) );
            timeTables.add( new TimeTable(i++, route, new Time(hour + 2, min), new Time(hour + 2, min).after(duration)) );
            timeTables.add( new TimeTable(i++, route, new Time(hour + 4, min), new Time(hour + 4, min).after(duration)) );
        }
        return timeTables;
    }

    private Facility toilette = new Facility("toilette");
    private Facility bank = new Facility("bank");
    private Facility parking = new Facility("parking");
    private Facility bar = new Facility("bar");
    private Facility restaurant = new Facility("restaurant");

    private Station miCentrale = new Station("Milano Centrale", "Milano", asList(toilette, bank, restaurant));
    private Station miLambrate = new Station("Milano Lambrate", "Milano", asList(toilette, parking));
    private Station brescia = new Station("Brescia", "Brescia", asList(toilette, bar, parking));
    private Station toPNuova = new Station("Torino Porta Nuova", "Torino", asList(toilette, bar, restaurant));
    private Station veMestre = new Station("Venezia Mestre", "Venezia", asList(toilette, bank, parking));
    private Station boCentrale = new Station("Bologna Centrale", "Bologna", asList(toilette, parking, bar));
    private Station fiSMNovella = new Station("Firenze S.Maria Novella", "Firenze", asList(restaurant, parking));
    private Station rmTermini = new Station("Roma Termini", "Roma", asList(toilette, bank, restaurant));
    private Station rmTiburtina = new Station("Roma Tiburtina", "Roma", asList(toilette, bank, parking, bar));
    private Station naCentrale = new Station("Napoli Centrale", "Napoli", asList(toilette, parking, bar));

    private Route toVe1 = new Route(1, toPNuova, veMestre, asList(miCentrale));
    private Route toVe2 = new Route(2, toPNuova, veMestre, asList(miLambrate, brescia));
    private Route toMi = new Route(3, toPNuova, miLambrate, Arrays.<Station>asList());
    private Route miNa1 = new Route(4, miCentrale, naCentrale, asList(boCentrale, fiSMNovella, rmTiburtina));
    private Route miNa2 = new Route(5, miCentrale, naCentrale, asList(rmTermini));
    private Route boRm = new Route(6, boCentrale, rmTiburtina, asList(fiSMNovella));
    private Route miRm = new Route(7, miCentrale, rmTermini, Arrays.<Station>asList());

    private Route veTo1 = new Route(11, veMestre, toPNuova, asList(miCentrale));
    private Route veTo2 = new Route(12, veMestre, toPNuova, asList(brescia, miLambrate));
    private Route miTo = new Route(13, miLambrate, toPNuova, Arrays.<Station>asList());
    private Route naMi1 = new Route(14, naCentrale, miCentrale, asList(rmTiburtina, fiSMNovella, boCentrale));
    private Route naMi2 = new Route(15, naCentrale, miCentrale, asList(rmTermini));
    private Route rmBo = new Route(16, rmTiburtina, boCentrale, asList(fiSMNovella));
    private Route rmMi = new Route(17, rmTermini, miCentrale, Arrays.<Station>asList());
}
