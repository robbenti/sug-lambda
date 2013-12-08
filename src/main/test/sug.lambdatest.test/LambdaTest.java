package sug.lambdatest.test;

import org.junit.*;
import sug.lambdatest.*;
import sug.lambdatest.model.*;

public class LambdaTest {

    // Tutte le linee che passano per una determinata stazione (input: una lista di Route)
    // Elenco dei servizi per tratta e relativa occorrenza (input: una lista di Route)
    // Tutti i treni che passano per una determinata stazione (input: una lista di TimeTable)
    // (es. Tratta X = 2 Bar, 1 Banca, 3 Parcheggi, 3 Toilet)
    // La stazione con più passaggi di treni (input: una lista di TimeTable)
    // La lista di tutte le tratte possibili tenendo conto dei cambi/coincidenze (input: una lista di Route)
    // TimeTable per un giorno x dalle ore y per una determinata tratta tenendo conto delle coincidenze (input: una lista di TimeTable)

    @Test
    public void testAllRoutesPassingFromAStation() {
        System.out.println(RouteService.get().getAllRoutesPassingFromAStation("Firenze S.Maria Novella"));
    }

    @Test
    public void testFacilitiesCountInARoute() {
        System.out.println(RouteService.get().getFacilitiesInRoute(1));
    }

    @Test
    public void testAllTimeTablesForAStation() {
        System.out.println(TimeTableService.get().getAllTimeTablesForAStation("Firenze S.Maria Novella"));
    }

    @Test
    public void testFindStationWithMaxNumberOfTrain() {
        System.out.println(TimeTableService.get().getStationWithMaxNumberOfTrains());
    }

    @Test
    public void testConnections() {
        System.out.println(TimeTableService.get().getTrainsPathWithConnection("Napoli Centrale", "Torino Porta Nuova"));
    }

    @Test
    public void testAfter() {
        System.out.println(TimeTableService.get().getTrainsAfter("Bologna Centrale", new Time(10, 00)));
    }
}
