import java.util.*;

/**
 * 
 * Location Filter for filtering events.
 *
 */
public class FilterLocationVisitor implements CriteriaVisitor{
	private Location aLocation; // Location Criteria

	/**
	 * Constructor
	 * @param pLocation
	 * @pre pLocation != null;
	 */
	public FilterLocationVisitor(Location pLocation) {
		assert pLocation != null;
		aLocation = pLocation;
	}
	
	/**
	 * Visitor Pattern to check if the gala is hosted in the same location
	 * @param pGala
	 * @return an optional event of Gala if it meets all the requirements. If not, return an empty optional.
	 * @pre pGala != null;
	 */
	@Override
	public Optional<Event> visitGala(Gala pGala) {
		assert pGala != null;
		return (this.aLocation == pGala.getLocation().get()) ? Optional.of(pGala) : Optional.empty(); 
	}
	
	/**
	 * Visitor Pattern to check if the festival is hosted in the same location
	 * @param pFestival
	 * @return an optional event of Festival if it meets all the requirements. If not, return an empty optional.
	 * @pre pFestival != null;
	 */
	@Override
	public Optional<Event> visitFestival(Festival pFestival){
		assert pFestival != null;
		return (this.aLocation == pFestival.getLocation().get()) ? Optional.of(pFestival) : Optional.empty(); 
	}
	
	/**
	 * Visitor Pattern to check if the screening is hosted in the same location
	 * @param pScreening
	 * @return an optional event of Screening if it meets all the requirements. If not, return an empty optional.
	 * @pre pScreening != null;
	 */
	@Override
	public Optional<Event> visitScreening(Screening pScreening){
		assert pScreening != null;
		return (this.aLocation == pScreening.getLocation().get()) ? Optional.of(pScreening) : Optional.empty(); 
	}
	
	/**
	 * Visitor Pattern to check if the workshop is hosted in the same location
	 * @param pWorkshop
	 * @return an optional event of Workshop if it meets all the requirements. If not, return an empty optional.
	 * @pre pWorkshop != null;
	 */
	@Override
	public Optional<Event> visitWorkshop(Workshop pWorkshop){
		assert pWorkshop != null;
		return (this.aLocation == pWorkshop.getLocation().get()) ? Optional.of(pWorkshop) : Optional.empty(); 
	}
	
	/**
	 * Visitor Pattern to check if the concert is hosted in the same location
	 * @param pConcert
	 * @return an optional event of Concert if it meets all the requirements. If not, return an empty optional.
	 * @pre pConcert != null;
	 */
	@Override
	public Optional<Event> visitConcert(Concert pConcert){
		assert pConcert != null;
		return (this.aLocation == pConcert.getLocation().get()) ? Optional.of(pConcert) : Optional.empty(); 
	}
	
	/**
	 * Visitor Pattern to check if the comingsoon is hosted in the same location. It will always return an empty
	 * optional since a comingsoon event does not have a location.
	 * @param pComingSoon
	 * @return an empty optional.
	 * @pre pComingSoon != null;
	 */
	@Override
	public Optional<Event> visitComingSoon(ComingSoon pComingSoon){
		assert pComingSoon != null;
		return Optional.empty(); 
	}
	
	/**
	 * Method to clone the criteria.
	 * @return a clone of the criteria.
	 */
	@Override
	public CriteriaVisitor clone() {
		return new FilterLocationVisitor(this.aLocation);
	}
}
