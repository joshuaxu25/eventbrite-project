import java.time.LocalDate;
import java.util.*;

public class Gala extends AbstractEvent{
	private ArrayList<String> aVIPs = new ArrayList<String>(); // List of VIPs
	
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
	 * @param pVIPs
	 * 		List of VIPs of the event
	 * @pre pVIPs != null && !(pVIPs.contains(null));
	 */
	public Gala(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, ArrayList<String> pVIPs) {
		super(pName, pPrice, pLocation, pDate, pNumberTickets);
		assert pVIPs != null && !(pVIPs.contains(null));
		for(String aVIP: pVIPs) {
			aVIPs.add(aVIP);
		}
	}
	
	/**
	 * Method to get the list of VIPs
	 * @return a deep copy of the list of VIPs
	 */
	public ArrayList<String> getVIPList(){
		ArrayList<String> aVIPsCopy = new ArrayList<String>();
		for(String aVIP: this.aVIPs) {
			aVIPsCopy.add(aVIP);
		}
		return aVIPsCopy;
	}
	
	/**
	 * Method to return the number of VIPs
	 * @return the number of VIPs
	 */
	public int getVIPNumbers() {
		assert aVIPs.size() >= 0;
		return this.aVIPs.size();
	}
	
	
	@Override
	public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria) {
		assert pCriteria != null;
		return pCriteria.visitGala(this);
    }
    
    @Override
    public double acceptMetricVisitor(MetricVisitor pMetric) {
    	assert pMetric != null;
    	return pMetric.visitGala(this);
    }

    @Override
    public Gala clone() {
    	return new Gala(this.getName(), this.getPrice().get(), this.getLocation().get(), this.getDate(), this.getNumTickets().get(), this.getVIPList());
    }
}
