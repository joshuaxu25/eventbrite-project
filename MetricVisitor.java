
/**
 * Visitor Design Pattern (Subject)
 * 
 * Provides an interface to calculate certain metrics of the events.
 *
 */
public interface MetricVisitor {
	public double visitGala(Gala pGala);
	public double visitScreening(Screening pScreening);
	public double visitWorkshop(Workshop pWorkshop);
	public double visitConcert(Concert pConcert);
	public double visitComingSoon(ComingSoon pComingSoon);
}
