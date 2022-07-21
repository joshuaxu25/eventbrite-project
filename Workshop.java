import java.time.LocalDate;
import java.util.*;

public class Workshop extends AbstractEvent implements Cloneable{
	private ArrayList<Workshop> aPrerequisites = new ArrayList<Workshop>(); // List of Workshop prerequisites
	
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
	 * @param pPrerequisites
	 * 		List of Workshop prerequisites of the event
	 * @pre pPrerequisites != null && !(pPrerequisites.contains(null));
	 */
	public Workshop(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, ArrayList<Workshop> pPrerequisites) {
		super(pName, pPrice, pLocation, pDate, pNumberTickets);
		assert pPrerequisites != null && !(pPrerequisites.contains(null));
		for(Workshop pPrerequisite: pPrerequisites) {
			aPrerequisites.add(pPrerequisite.clone());
		}
	}
	
	/**
	 * Method to get a list of Workshop prerequisites of the event
	 * @return a deep copy of a list of Workshop prerequisites of the event
	 */
	public ArrayList<Workshop> getPrerequisites(){
		ArrayList<Workshop> aPrerequisitesCopy = new ArrayList<Workshop>();
		for(Workshop w: aPrerequisites) {
			aPrerequisitesCopy.add(w.clone());
		}
		return aPrerequisitesCopy;
	}
	
	@Override
	public Optional<Event> acceptCriteriaVisitor(CriteriaVisitor pCriteria) {
		assert pCriteria != null;
		return pCriteria.visitWorkshop(this);
    }
	
    @Override
    public double acceptMetricVisitor(MetricVisitor pMetric) {
    	assert pMetric != null;
    	return pMetric.visitWorkshop(this);
    }
    
    @Override
    public Workshop clone() {
		return new Workshop(this.getName(), this.getPrice().get(), this.getLocation().get(), this.getDate(), this.getNumTickets().get(), this.getPrerequisites());
    }

}
