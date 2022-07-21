import java.util.*;

/**
 * 
 * Criteria that can contain multiple criteria (Composite Design Pattern)
 *
 */
public class FilterCompositeVisitor implements CriteriaVisitor {
	private ArrayList<CriteriaVisitor> aCriterias = new ArrayList<CriteriaVisitor>(); // List of criteria
	
	/**
	 * Constructor
	 * @param pCriterias
	 * 		List of criteria
	 * @pre pCriterias != null && !(pCriterias.contains(null)) && pCriterias.size() >= 0;
	 */
	public FilterCompositeVisitor(ArrayList<CriteriaVisitor> pCriterias) {
		assert pCriterias != null && !(pCriterias.contains(null)) && pCriterias.size() >= 0;
		this.aCriterias = new ArrayList<CriteriaVisitor>();
		for(CriteriaVisitor c: pCriterias) {
			this.aCriterias.add(c.clone());
		}
	}
	
	/**
	 * Visitor Pattern to check if the gala meets the list of criteria.
	 * @param pGala
	 * @return an optional event of Gala if it meets all the requirements. If not, return an empty optional.
	 * @pre pGala != null;
	 */
	@Override
	public Optional<Event> visitGala(Gala pGala) {
		assert pGala != null;
		int n = 0;
		for(CriteriaVisitor c: this.aCriterias) {
			if (c.visitGala(pGala).isPresent()) {
				n += 1;
			} 
		}
		return (n < this.aCriterias.size()) ? Optional.empty() : Optional.of(pGala);
	}
	
	/**
	 * Visitor Pattern to check if the festival meets the list of criteria.
	 * @param pFestival
	 * @return an optional event of Festival if it meets all the requirements. If not, return an empty optional.
	 * @pre pFestival != null;
	 */
	@Override
	public Optional<Event> visitFestival(Festival pFestival){
		assert pFestival != null;
		int n = 0;
		for(CriteriaVisitor c: this.aCriterias) {
			if (c.visitFestival(pFestival).isPresent()) {
				n += 1;
			}
		}
		return (n < this.aCriterias.size()) ? Optional.empty() : Optional.of(pFestival);
	}
	
	/**
	 * Visitor Pattern to check if the screening meets the list of criteria.
	 * @param pScreening
	 * @return an optional event of Screening if it meets all the requirements. If not, return an empty optional.
	 * @pre pScreening != null;
	 */
	@Override
	public Optional<Event> visitScreening(Screening pScreening){
		assert pScreening != null;
		
		int n = 0;
		for(CriteriaVisitor c: this.aCriterias) {
			if (c.visitScreening(pScreening).isPresent()) {
				n += 1;
			}
		}
		return (n < this.aCriterias.size()) ? Optional.empty() : Optional.of(pScreening);
	}
	
	/**
	 * Visitor Pattern to check if the workshop meets the list of criteria.
	 * @param pWorkshop
	 * @return an optional event of Workshop if it meets all the requirements. If not, return an empty optional.
	 * @pre pWorkshop != null;
	 */
	@Override
	public Optional<Event> visitWorkshop(Workshop pWorkshop){
		assert pWorkshop != null;
		int n = 0;
		for(CriteriaVisitor c: this.aCriterias) {
			if (c.visitWorkshop(pWorkshop).isPresent()) {
				n += 1;
			}
		}
		return (n < this.aCriterias.size()) ? Optional.empty() : Optional.of(pWorkshop);
	}
	
	/**
	 * Visitor Pattern to check if the concert meets the list of criteria.
	 * @param pConcert
	 * @return an optional event of Concert if it meets all the requirements. If not, return an empty optional.
	 * @pre pConcert != null;
	 */
	@Override
	public Optional<Event> visitConcert(Concert pConcert){
		assert pConcert != null;
		int n = 0;
		for(CriteriaVisitor c: this.aCriterias) {
			if (c.visitConcert(pConcert).isPresent()) {
				n += 1;
			}
		}
		return (n < this.aCriterias.size()) ? Optional.empty() : Optional.of(pConcert);
	}
	
	/**
	 * Visitor Pattern to check if the comingsoon event meets the list of criteria.
	 * @param pComingSoon
	 * @return an optional event of Screening if it meets all the requirements. If not, return an empty optional.
	 * @pre pComingSoon != null;
	 */
	@Override
	public Optional<Event> visitComingSoon(ComingSoon pComingSoon){
		assert pComingSoon != null;
		int n = 0;
		for(CriteriaVisitor c: this.aCriterias) {
			if (c.visitComingSoon(pComingSoon).isPresent()) {
				n += 1;
			}
		}
		return (n < this.aCriterias.size()) ? Optional.empty() : Optional.of(pComingSoon);
	}
	
	/**
	 * Method to clone the criteria.
	 * @return a clone of the criteria.
	 */
	@Override
	public CriteriaVisitor clone() {
		assert this.aCriterias.size() >= 0;
		try {
			FilterCompositeVisitor pCriteria = (FilterCompositeVisitor) super.clone();
			pCriteria.aCriterias = new ArrayList<CriteriaVisitor>();
			for(CriteriaVisitor c: this.aCriterias) {
				pCriteria.aCriterias.add(c.clone());
			}
			return pCriteria;
		} catch(CloneNotSupportedException e) {
			assert false;
			return null;
		}
	}
}
