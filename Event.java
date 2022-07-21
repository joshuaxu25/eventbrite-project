import java.time.LocalDate;
import java.util.Optional;

/*
Representation of a type of Event that can exist
 */

/**
* Interface to group all types of event.
*  
*/
public interface Event {
    public String getName();
    public Optional<Location> getLocation();
    public LocalDate getDate();
    public Optional<Double> getPrice();
    public Optional<Integer> getNumTickets();
    public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria);
    public double acceptMetricVisitor(MetricVisitor pMetric);
    public Event clone();
}
