import java.util.*;

/**
 * 
 * Range Filter for filtering events 
 *
 */
public class FilterRangeVisitor implements CriteriaVisitor {
	private final double aLowerPrice; // Lowest Price of the filter
	private final double aUpperPrice; // Highest Price of the filter
	
	/**
	 * Constructor
	 * @param pPriceOne
	 * 		The first price of criteria
	 * @param pPriceTwo
	 * 		The second price of the criteria
	 * @pre pPriceTwo >= 0 && pPriceOne >= 0; 
	 */
	public FilterRangeVisitor(double pPriceOne, double pPriceTwo) {
		assert pPriceTwo >= 0 && pPriceOne >= 0; 
		this.aLowerPrice = (pPriceOne < pPriceTwo) ? pPriceOne : pPriceTwo;
		this.aUpperPrice = (pPriceOne > pPriceTwo) ? pPriceOne : pPriceTwo;  
	}
	
	/**
	 * Visitor Pattern to check if the gala price is between the range.
	 * @param pGala
	 * @return an optional event of Gala if it meets all the requirements. If not, return an empty optional.
	 * @pre pGala != null;
	 */
	@Override
	public Optional<Event> visitGala(Gala pGala) {
		assert pGala != null;
		return (this.aLowerPrice <= pGala.getPrice().get() && pGala.getPrice().get() <= this.aUpperPrice) ? Optional.of(pGala) : Optional.empty();
	}
	
	/**
	 * Visitor Pattern to check if the festival price is between the range.
	 * @param pFestival
	 * @return an optional event of Festival if it meets all the requirements. If not, return an empty optional.
	 * @pre pFestival != null;
	 */
	@Override
	public Optional<Event> visitFestival(Festival pFestival){
		assert pFestival != null;
		return (this.aLowerPrice <= pFestival.getPrice().get() && pFestival.getPrice().get() <= this.aUpperPrice) ? Optional.of(pFestival) : Optional.empty();
	}
	
	/**
	 * Visitor Pattern to check if the screening price is between the range.
	 * @param pScreening
	 * @return an optional event of Screening if it meets all the requirements. If not, return an empty optional.
	 * @pre pScreening != null;
	 */
	@Override
	public Optional<Event> visitScreening(Screening pScreening){
		assert pScreening != null;
		return (this.aLowerPrice <= pScreening.getPrice().get() && pScreening.getPrice().get() <= this.aUpperPrice) ? Optional.of(pScreening) : Optional.empty();
	}
	
	/**
	 * Visitor Pattern to check if the workshop price is between the range.
	 * @param pWorkshop
	 * @return an optional event of Workshop if it meets all the requirements. If not, return an empty optional.
	 * @pre pWorkshop != null;
	 */
	@Override
	public Optional<Event> visitWorkshop(Workshop pWorkshop){
		assert pWorkshop != null;
		return (this.aLowerPrice <= pWorkshop.getPrice().get() && pWorkshop.getPrice().get() <= this.aUpperPrice) ? Optional.of(pWorkshop) : Optional.empty();
	}
	
	/**
	 * Visitor Pattern to check if the concert price is between the range.
	 * @param pConcert
	 * @return an optional event of Concert if it meets all the requirements. If not, return an empty optional.
	 * @pre pConcert != null;
	 */
	@Override
	public Optional<Event> visitConcert(Concert pConcert){
		assert pConcert != null;
		return (this.aLowerPrice <= pConcert.getPrice().get() && pConcert.getPrice().get() <= this.aUpperPrice) ? Optional.of(pConcert) : Optional.empty();
	}
	
	/**
	 * Visitor Pattern to check if the comingsoon price is between the range. It will always return an empty
	 * optional since a comingsoon event does not have a price.
	 * @param pComingSoon
	 * @return an optional event of ComingSoon if it meets all the requirements. If not, return an empty optional.
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
		return new FilterRangeVisitor(this.aLowerPrice, this.aUpperPrice);
	}
}
