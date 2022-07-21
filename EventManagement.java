import java.util.*;
import java.time.LocalDate;

/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
	
	/**
	 * List of hosted events that Bob created.
	 */
    private ArrayList<Event> aHostedEvents = new ArrayList<Event>();
    
    /**
     * Map to keep track of all current date and location combinations (Flyweight Pattern) 
     */
    private HashMap<LocalDate, List<Location>> aMap = new HashMap<LocalDate, List<Location>>();
    
    /**
     * Method to host a new concert event
     * @param pName
     * @param pPrice
     * @param pLocation
     * @param pDate
     * @param pNumberTickets
     * @param pArtist 
	 * @pre pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pArtist != null && pVIPs != null;
     */
    public void addConcertEvent(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, String pArtist, ArrayList<String> pVIPs){    	
    	assert pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pArtist != null && pVIPs != null;
    	assert isEventAddeable(pDate, pLocation);
    	aHostedEvents.add(new Concert(pName, pPrice, pLocation, pDate, pNumberTickets, pArtist, pVIPs));
    	this.lockDateAndLocation(pDate, pLocation); 
    }

    /**
     * Method to host a new Gala event
     * @param pName
     * @param pPrice
     * @param pLocation
     * @param pDate
     * @param pNumberTickets
     * @param pVIPs
     * @pre pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pVIPs != null;
     */
    public void addGalaEvent(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, ArrayList<String> pVIPs){
    	assert pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pVIPs != null;
    	assert isEventAddeable(pDate, pLocation);
    	aHostedEvents.add(new Gala(pName, pPrice, pLocation, pDate, pNumberTickets, pVIPs));
    	this.lockDateAndLocation(pDate, pLocation);
    }

    /**
     * Method to host a new Screening event
     * @param pName
     * @param pPrice
     * @param pLocation
     * @param pDate
     * @param pNumberTickets
     * @param pRating
     * @pre pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pRating != null;
     */
    public void addScreeningEvent(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, Rating pRating){
    	assert pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pRating != null;
    	assert isEventAddeable(pDate, pLocation);
    	aHostedEvents.add(new Screening(pName, pPrice, pLocation, pDate, pNumberTickets, pRating));
    	this.lockDateAndLocation(pDate, pLocation);
    }

    /**
     * Method to host a new Workshop event
     * @param pName
     * @param pPrice
     * @param pLocation
     * @param pDate
     * @param pNumberTickets
     * @param pPrerequisites
     * @pre pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pPrerequisites != null;
     */
    public void addWorkshopEvent(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, ArrayList<Workshop> pPrerequisites){
    	assert pName != null && pPrice >= 0 && pLocation != null && pDate != null && pNumberTickets >= 0 && pPrerequisites != null;
    	assert isEventAddeable(pDate, pLocation);
    	aHostedEvents.add(new Workshop(pName, pPrice, pLocation, pDate, pNumberTickets, pPrerequisites));
    	this.lockDateAndLocation(pDate, pLocation);
    }
    
    /**
     * Method to host a new Festival event. Assume that a festival must at least have 2 events.
     * @param pName
     * @param pEvents
     * @pre pName != null && pEvents != null;
     */
    public void addFestivalEvent(String pName, ArrayList<Event> pEvents) {
    	assert pName != null && pEvents != null;
    	assert pEvents.size() > 1;
    	
    	Festival pFestival = Festival.createFestival(pName, pEvents);
    	assert isEventAddeable(pFestival.getDate(), pFestival.getLocation().get());
    	aHostedEvents.add(pFestival);
    	this.lockDateAndLocation(pFestival.getDate(), pFestival.getLocation().get());
    }
    
    /**
     * Method to host a new ComingSoon event.
     * @param pName
     * @param pDate
     * @pre pName != null && pDate != null;
     */
    public void addComingSoon(String pName, LocalDate pDate) {
    	assert pName != null && pDate != null;
    	aHostedEvents.add(new ComingSoon(pName, pDate));
    }
    
    /**
     * Checks if the event can be created by checking duplicates.
     * @param pDate
     * @param pLocation
     * @return true if the event can be added. False otherwise.
     * @pre pDate != null && pLocation != null;
     */
    private boolean isEventAddeable(LocalDate pDate, Location pLocation) {
    	assert pDate != null && pLocation != null;
    	if(aMap.containsKey(pDate)){
    		if(aMap.get(pDate).contains(pLocation)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Lock the date and location in the map to prevent duplicate events.
     * @param pDate
     * @param pLocation
     * @pre pDate != null && pLocation != null;
     */
    private void lockDateAndLocation(LocalDate pDate, Location pLocation) {
    	assert pDate != null && pLocation != null;
    	List<Location> aLocations = new ArrayList<Location>();
    	if (aMap.containsKey(pDate)) {
    		aLocations = aMap.get(pDate);
    	}
    	aLocations.add(pLocation);
    	aMap.put(pDate, aLocations);
    	
    }
    
    
    /*
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }
    
    /**
     * Method to allow the filtering of events from a criteria.
     * @param pCriteria
     * @return a FilterResult containing all the filtered events.
     * @pre pCriteria != null;
     */
    public FilterResult filterEventsBy(CriteriaVisitor pCriteria) {
    	assert pCriteria != null;
    	return new FilterResult(pCriteria, aHostedEvents);
    }
    
    /**
     * Method to calculate the expected profits of a FilterResult
     * @param pFilteredResult
     * 		A list of filtered events
     * @param pPercentages
     * 		An object containing all the percentages of each concrete event
     * @return the expected profit of a FilterResult
     * @pre pFilteredResult != null && pPercentages != null;
     */
    public double calculateProfits(FilterResult pFilteredResult, ProfitCalculationVisitor pPercentages) {
    	assert pFilteredResult != null && pPercentages != null;
    	return pFilteredResult.calculateExpectedProfits(pPercentages);
    }
    
    /**
     * Method to calculate the number of VIPs of a FilterResult
     * @param pFilteredResult
     * 		A list of filtered events
     * @return the number of VIPs of a FilterResult 
     * @pre pFilteredResult != null;
     */
    public int calculateVIPs(FilterResult pFilteredResult) {
    	assert pFilteredResult != null;
    	return pFilteredResult.calculateVIPs();
    }
    
    
}
