import java.time.LocalDate;
import java.util.*;

public class Festival extends AbstractEvent{
	private List<Event> aEvents = new ArrayList<Event>(); // List of events of a Festival	
		
	/**
	 * Method to create a Festival. This method preprocesses the price, location, date, and number of
	 * tickets before passing the fields to its constructor.
	 * @param pName
	 * @param pEvents
	 * @return a Festival object
	 * @pre pName != null && pEvents != null && !(pEvents.contains(null)) && pEvents.size() > 0;
	 */
	public static Festival createFestival(String pName, ArrayList<Event> pEvents) {
		assert pName != null && pEvents != null && !(pEvents.contains(null)) && pEvents.size() > 0;
		
		/**
		 * The following code assumes that there will always be at least one non-ComingSoon 
		 * event (TA said that we can assume that).
		 */
		
		/**
		 * Calculate the new price.
		 */
		double pPrice = 0;
		for(Event e: pEvents) {
			if(!(e instanceof ComingSoon)) {
				pPrice += e.getPrice().get();
			}
		}
		pPrice *= 0.2;
		
		/**
		 * Calculate the new location.
		 */
		Optional<Location> pOptionalLocation = Optional.empty();
		for (int i = 0; i < pEvents.size(); i++) {
			if (!(pEvents.get(i) instanceof ComingSoon)) {
				if (pOptionalLocation.isEmpty()) {
					pOptionalLocation = pEvents.get(i).getLocation();
				}
				else{
					if (pOptionalLocation != pEvents.get(i).getLocation()) {
						pOptionalLocation = Optional.of(Location.Multiple);
						break;
					}
				}
			}
		}
		Location pLocation = pOptionalLocation.get();
		
		/**
		 * Calculate the new date.
		 */
		LocalDate pDate = pEvents.get(0).getDate();
		for(int i = 1; i < pEvents.size(); i++) {
			LocalDate aCurrentDate = pEvents.get(i).getDate();
			if (pDate.compareTo(aCurrentDate) > 0){
				pDate = aCurrentDate;
			}
		}

		/**
		 * Calculate the new number of tickets.
		 */
		int pNumberTickets = Integer.MAX_VALUE;
		for(Event e: pEvents) {
			if(!(e instanceof ComingSoon)) {
				int currentTickets = e.getNumTickets().get();
				if (pNumberTickets > currentTickets) {
					pNumberTickets = currentTickets;
				}
			}
		}
		
		return new Festival(pName, pPrice, pLocation, pDate, pNumberTickets, pEvents);
	}
	
	/**
	 * Private constructor that calls the Abstract Event for code reuse. 
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
	 * @param pEvents
	 * 		List of events of the Festival
	 * @pre pEvents != null && !(pEvents.contains(null));
	 */
	private Festival(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, ArrayList<Event> pEvents) {
		super(pName, pPrice, pLocation, pDate, pNumberTickets);
		assert pEvents != null && !(pEvents.contains(null));
		for (Event e: pEvents) {
			aEvents.add(e.clone());
		}
		aEvents = Collections.unmodifiableList(aEvents);
	}
	
	/**
	 * Getter method for the events of a Festival
	 * @return a deep copy of a Festival
	 */
	public ArrayList<Event> getEvents(){
		assert this.aEvents.size() > 0;
		ArrayList<Event> aEventsCopy = new ArrayList<Event>();
		for (Event e: this.aEvents) {
			aEventsCopy.add(e.clone());
		}
		return aEventsCopy;
	}
	
	/**
	 * Visitor Design pattern for filtering criteria.
	 * @return an optional event if it meets the criteria. If not, an empty optional.
	 * @pre pCriteria != null;
	 */
	@Override
	public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria) {
		assert pCriteria != null;
		return pCriteria.visitFestival(this);
    }
    
	/**
	 * Visitor Design pattern for calculating profits and VIPs. Method that goes through each event 
	 * of Festival for calculation.
	 * 
	 * @return a numeric value of the metric.
	 * @pre pMetric != null;
	 */
    @Override
    public double acceptMetricVisitor(MetricVisitor pMetric) {
    	assert pMetric != null;
    	double aCount = 0;
    	for(Event e: this.aEvents) {
    		aCount += e.acceptMetricVisitor(pMetric);
    	}
    	return aCount;
    }
    
    /**
     * Cloneable Design pattern to allow cloning (deep copy) of the event. Preserves encapsulation
	 * @return a deep copy of the event.
     */
    @Override
    public Festival clone() {
		return new Festival(this.getName(), this.getPrice().get(), this.getLocation().get(), this.getDate(), this.getNumTickets().get(), this.getEvents());
    }
}






