
/**
 * 
 * Metric to calculate the profits of the events
 *
 */
public class ProfitCalculationVisitor implements MetricVisitor{
	private final double aConcertPercent; // Concert Percentage
	private final double aWorkshopPercent; // Workshop Percentage
	private final double aGalaPercent; // Gala Percentage
	private final double aScreeningPercent; // Screening Percentage
	
	/**
	 * Constructor
	 * @param pConcertPercent
	 * @param pWorkshopPercent
	 * @param pGalaPercent
	 * @param pScreeningPercent
	 */
	public ProfitCalculationVisitor(double pConcertPercent, double pWorkshopPercent, double pGalaPercent, double pScreeningPercent) {
		assert pConcertPercent > 0 && pWorkshopPercent > 0 && pGalaPercent > 0 && pScreeningPercent > 0;
		this.aConcertPercent = pConcertPercent;
		this.aWorkshopPercent = pWorkshopPercent;
		this.aGalaPercent = pGalaPercent;
		this.aScreeningPercent = pScreeningPercent;
	}
	
	/**
	 * Visitor Pattern to calculate the profit of a Gala
	 * @param pGala
	 * @return the profit of a Gala
	 * @pre pGala != null;
	 */
	@Override
	public double visitGala(Gala pGala) {
		assert pGala != null;
		return pGala.getPrice().get() * pGala.getNumTickets().get() * this.aGalaPercent;
	}
	
	/**
	 * Visitor Pattern to calculate the profit of a Screening
	 * @param pScreening
	 * @return the profit of a Screening
	 * @pre pScreening != null;
	 */
	@Override	
	public double visitScreening(Screening pScreening) {
		assert pScreening != null;
		return pScreening.getPrice().get() * pScreening.getNumTickets().get() * this.aScreeningPercent;
	}
	
	/**
	 * Visitor Pattern to calculate the profit of a Workshop
	 * @param pWorkshop
	 * @return the profit of a Workshop
	 * @pre pWorkshop != null;
	 */
	@Override
	public double visitWorkshop(Workshop pWorkshop) {
		assert pWorkshop != null;
		return pWorkshop.getPrice().get() * pWorkshop.getNumTickets().get() * this.aWorkshopPercent;
	}
	
	/**
	 * Visitor Pattern to calculate the profit of a Concert
	 * @param pConcert
	 * @return the profit of a Concert
	 * @pre pConcert != null;
	 */
	@Override
	public double visitConcert(Concert pConcert) {
		assert pConcert != null;
		return pConcert.getPrice().get() * pConcert.getNumTickets().get() * this.aConcertPercent;
	}
	
	/**
	 * Visitor Pattern to calculate the profit of a ComingSoon
	 * @param pComingSoon
	 * @return the profit of a ComingSoon
	 * @pre pComingSoon != null;
	 */
	@Override
	public double visitComingSoon(ComingSoon pComingSoon) {
		assert pComingSoon != null;
		return 0;
	}
	 
}
