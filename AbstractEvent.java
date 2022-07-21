import java.time.LocalDate;
import java.util.Optional;
 
/**
 * Abstract event that implements the interface Event. This is to support code reuse between all the concrete
 * events.
 */
public abstract class AbstractEvent implements Event{
	private final String aName; // Name of the event
	private final Optional<Double> aPrice; // Price of the event
	private Optional<Location> aLocation; // Location of the event
	private final LocalDate aDate; // Date of the event
	private final Optional<Integer> aNumberTickets; // Number of tickets of the event

	/**
	 * 
	 * @param pName
	 * 		Name of the event
	 * @param pPrice
	 * 		Price of the event
	 * @param pLocation
	 * 		Location of the event
	 * @param pDate
	 * 		Date of the event
	 * @param pNumberTickets
	 * 		Number of tickets of the event
	 * @pre pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0;
	 */
	protected AbstractEvent(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets) {
		assert pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0;
		aName = pName;
		aPrice = Optional.of(pPrice);
		aLocation = Optional.of(pLocation);
		aDate = pDate;
		aNumberTickets = Optional.of(pNumberTickets);
	}
	
	/**
	 * @return the name of the event
	 */
	@Override
	public String getName() {
		return this.aName;
	}
	
	/**
	 * @return the optional location of the event
	 */
	@Override
    public Optional<Location> getLocation() {
		return this.aLocation;
	}
	
	/**
	 * @return the date of the event
	 */
	@Override
    public LocalDate getDate() {
		return this.aDate;
	}
	
	/**
	 * @return the optional price of the event
	 */
	@Override
    public Optional<Double> getPrice() {
		return this.aPrice;
	}
	
	/**
	 * @return the optional number of tickets of the event
	 */
	@Override
    public Optional<Integer> getNumTickets() {
		return this.aNumberTickets;
	}
	
	/**
	 * Visitor Design pattern for filtering criteria.
	 * @return an optional event if it meets the criteria. If not, an empty optional.
	 * @pre pCriteria != null;
	 */
	public abstract Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria);
	
	/**
	 * Visitor Design pattern for calculating profits and VIPs.
	 * 
	 * @return a numeric value of the metric.
	 * @pre pMetric != null;
	 */
    public abstract double acceptMetricVisitor(MetricVisitor pMetric);
    
    /**
     * Cloneable Design pattern to allow cloning (deep copy) of the event. Preserves encapsulation
	 * @return a deep copy of the event.
     */
    public abstract Event clone();
    
}
