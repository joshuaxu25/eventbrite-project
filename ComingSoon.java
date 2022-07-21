import java.time.LocalDate;
import java.util.*;

/**
 * 
 * ComingSoon event that have unknown price, location and number of tickets. Refer to the Abstract Event class
 * for the documentation of this event.
 *
 */
public class ComingSoon implements Event{
	private final String aName;
	private final Optional<Double> aPrice;
	private Optional<Location> aLocation;
	private final LocalDate aDate; 
	private Optional<Integer> aNumberTickets;
	
	public ComingSoon(String pName, LocalDate pDate) {
		assert pName != null && pDate != null;
		aName = pName;
		aDate = pDate;
		aPrice = Optional.empty();
		aLocation = Optional.empty();
		aNumberTickets = Optional.empty();
	} 
	
	@Override
	public String getName() {
		return this.aName;
	}
	@Override
    public Optional<Location> getLocation() {
		return this.aLocation;
	};
	@Override
    public LocalDate getDate() {
		return this.aDate;
	};
	@Override
    public Optional<Double> getPrice() {
		return this.aPrice;
	};
	@Override
    public Optional<Integer> getNumTickets() {
		return this.aNumberTickets;
	};
		
	@Override
	public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria) {
		return pCriteria.visitComingSoon(this);
    };
    @Override
    public double acceptMetricVisitor(MetricVisitor pMetric) {
    	return pMetric.visitComingSoon(this);
    }
    @Override
    public ComingSoon clone() {
    	return new ComingSoon(this.aName, this.aDate);
    }

}
