import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Terminal> terminals = airport.getTerminals();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 2);

        List<Flight> flights = terminals.stream()
                .map(terminal -> terminal.getFlights())
                .flatMap(flight -> flight.stream())
                .filter(x -> x.getDate().after(date)
                        && x.getDate().before(calendar.getTime())
                        && x.getType() == Flight.Type.DEPARTURE)
                .collect(Collectors.toList());

        return flights;
    }

}