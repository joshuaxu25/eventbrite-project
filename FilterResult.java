import java.util.*;

/**
 * 
 * Class that contains the filtered events from a criteria.
 *  
 */
public class FilterResult {
	private final ArrayList<Event> aFilteredEvents = new ArrayList<Event>(); // Filtered Events from a criteria
	
	/**
	 * Constructor
	 * @param pCriteria
	 * 		The criteria which will be used to filter the events
	 * @param pHostedEvents
	 * 		List of events to be filtered
	 * @pre pCriteria != null && pHostedEvents != null && !(pHostedEvents.contains(null));
	 */
	public FilterResult(CriteriaVisitor pCriteria, List<Event> pHostedEvents) {
		assert pCriteria != null && pHostedEvents != null && !(pHostedEvents.contains(null));
		for(Event e: pHostedEvents) {
			if (e.acceptCriteriaVisitor(pCriteria).isPresent()) {
				this.aFilteredEvents.add(e.clone());
			};
		}
	}
	
	/**
	 * Method to calculate the profits of a FilterResult
	 * @param pPercentages
	 * 		An object containing the list of percentages
	 * @return the expected profits of the FilterResult
	 */
	public double calculateExpectedProfits(ProfitCalculationVisitor pPercentages) {
		int aProfit = 0;
		for(Event e: this.aFilteredEvents) {
			aProfit += e.acceptMetricVisitor(pPercentages); 
		}
		return aProfit;
	}
	
	/**
	 * Method to calculate the number of VIPs of a FilterResult
	 * @return the number of VIPs of the FilterResult
	 */
	public int calculateVIPs() {
		int aCount = 0;
		for(Event e: this.aFilteredEvents) {
			aCount += (int) e.acceptMetricVisitor(new VIPCalculationVisitor());
		}
		return aCount;
	}
	
}
