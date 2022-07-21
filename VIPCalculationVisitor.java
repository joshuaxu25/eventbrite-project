/**
 * 
 * Metric to calculate the number of VIPs of the events
 *
 */
public class VIPCalculationVisitor implements MetricVisitor{
	
	/**
	 * Visitor Pattern to calculate the number of VIPs of a Gala
	 * @param pGala
	 * @return the profit of a Gala
	 * @pre pGala != null;
	 */
	@Override
	public double visitGala(Gala pGala) {
		assert pGala != null;
		return pGala.getVIPNumbers();
	}
	
	/**
	 * Visitor Pattern to calculate the number of VIPs of a Screening
	 * @param pScreening
	 * @return the profit of a Screening
	 * @pre pScreening != null;
	 */
	@Override
	public double visitScreening(Screening pScreening) {
		assert pScreening != null;
		return 0;
	}
	
	/**
	 * Visitor Pattern to calculate the number of VIPs of a Workshop
	 * @param pWorkshop
	 * @return the profit of a Workshop
	 * @pre pWorkshop != null;
	 */
	@Override
	public double visitWorkshop(Workshop pWorkshop) {
		assert pWorkshop != null;
		return 0;
	}
	
	/**
	 * Visitor Pattern to calculate the number of VIPs of a Concert
	 * @param pConcert
	 * @return the profit of a Concert
	 * @pre pConcert != null;
	 */
	@Override
	public double visitConcert(Concert pConcert) {
		assert pConcert != null;
		return pConcert.getVIPNumbers();
	}
	
	/**
	 * Visitor Pattern to calculate the number of VIPs of a ComingSoon
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
