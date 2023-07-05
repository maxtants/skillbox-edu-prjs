import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    Station station1;
    Station station2;
    Station station3;
    Station station4;
    Station station5;
    Station station6;

    protected void setUp() throws Exception {

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        Station station1 = new Station("Площадь Ленина", line1);
        Station station2 = new Station("Чернышевская", line1);
        Station station3 = new Station("Площадь восстания", line1);
        Station station4 = new Station("Невский проспект", line2);
        Station station5 = new Station("Маяковская", line3);
        Station station6 = new Station("Гостинный двор", line3);

        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);

        List<Station> connection1 = List.of(station3, station5);
        List<Station> connection2 = List.of(station4, station6);

        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);
    }

    public void testCalculateDuration() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        double actual = routeCalculator.calculateDuration(List.of(station1, station3));
        double expected = 5;
        assertEquals(expected, actual);
    }

    public void testGetShortestRoutNoConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> actual = routeCalculator.getShortestRoute(station1, station3 );
        List<Station> expected = List.of(station1, station2, station3);
        assertEquals(expected, actual);
    }

    public void testGetShortestRoutOneConnection() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> actual = routeCalculator.getShortestRoute(station1, station5);
        List<Station> expected = List.of(station1, station2, station3, station5);
        assertEquals(expected, actual);

    }
    public void testGetShortestRoutTwoConnections() {
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
        List<Station> actual = routeCalculator.getShortestRoute(station1, station4);
        List<Station> expected = List.of(station1, station2, station3, station5, station6, station4);
        assertEquals(expected, actual);
    }

}
