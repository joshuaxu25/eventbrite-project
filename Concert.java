import java.time.LocalDate;
import java.util.*;

public class Concert extends AbstractEvent{
	private String aArtist; // Artist of the event
	private ArrayList<String> aVIPs = new ArrayList<String>(); // List of VIPs of the event
	
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
	 * @param pArtist
	 * 		Artist of the event
	 * @param pVIPs
	 * 		List of VIPs of the event
	 * @pre pArtist != null && pVIPs != null && !(pVIPs.contains(null));
	 */
	public Concert(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, String pArtist, ArrayList<String> pVIPs) {
		super(pName, pPrice, pLocation, pDate, pNumberTickets);
		assert pArtist != null && pVIPs != null && !(pVIPs.contains(null));
		aArtist = pArtist;
		for(String aVIP: pVIPs) {
			aVIPs.add(aVIP);
		}
	}

	/**
	 * @return the artist of the event
	 * @pre this.aArtist != null;
	 */
	public String getArtist() {
		assert this.aArtist != null;
		return this.aArtist;
	} 
	
	/**
	 * @return the list of VIPs of the event
	 * @pre this.aVIPs != null && !(this.aVIPs.contains(null));
	 */
	public ArrayList<String> getVIPList(){
		assert this.aVIPs != null && !(this.aVIPs.contains(null));
		ArrayList<String> aVIPsCopy = new ArrayList<String>();
		for(String aVIP: this.aVIPs) {
			aVIPsCopy.add(aVIP);
		}
		return aVIPsCopy;
	}
	
	/**
	 * 
	 * @return the number of VIPs of the event
	 * @pre this.aVIPs.size() >= 0;
	 */
	public int getVIPNumbers() {
		assert this.aVIPs.size() >= 0;
		return this.aVIPs.size();
	}
	
	@Override
	public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria) {
		assert pCriteria != null;
		return pCriteria.visitConcert(this);
    }
	
    @Override
    public double acceptMetricVisitor(MetricVisitor pMetric) {
    	assert pMetric != null;
    	return pMetric.visitConcert(this);
    }
    
    @Override
    public Concert clone() {
    	return new Concert(this.getName(), this.getPrice().get(), this.getLocation().get(), this.getDate(), this.getNumTickets().get(), this.getArtist(), this.getVIPList());
    }

}
