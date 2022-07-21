import java.time.LocalDate;
import java.util.Optional;

public class Screening extends AbstractEvent{	
	private final Rating aRating; // Rating of a screening

	/**
	 * Constructor that calls the Abstract Event for code reuse. 
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
	 * @param pRating
	 * 		Rating of the event
	 * @pre assert pRating != null;
	 */
	public Screening(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, Rating pRating) {
		super(pName, pPrice, pLocation, pDate, pNumberTickets);
		assert pRating != null;
		aRating = pRating;
	}
	
	/**
	 * Method to get the rating
	 * @return the rating of the event
	 */
	public Rating getRating() {
		return this.aRating;
	}
	
	@Override
	public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria) {
		assert pCriteria != null;
		return pCriteria.visitScreening(this);
    }
    
    @Override
    public double acceptMetricVisitor(MetricVisitor pMetric) {
    	assert pMetric != null;
    	return pMetric.visitScreening(this);
    }
    
    @Override
    public Screening clone() {
    	return new Screening(this.getName(), this.getPrice().get(), this.getLocation().get(), this.getDate(), this.getNumTickets().get(), this.getRating());
    }
}
