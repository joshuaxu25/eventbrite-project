import java.util.*;
/**
 * Visitor Design Pattern (Subject)
 * 
 * Provides an interface for criterias to filter events 
 *
 */
public interface CriteriaVisitor {
	public Optional<Event> visitGala(Gala pGala);
	public Optional<Event> visitFestival(Festival pFestival);
	public Optional<Event> visitScreening(Screening pScreening);
	public Optional<Event> visitWorkshop(Workshop pWorkshop);
	public Optional<Event> visitConcert(Concert pConcert);
	public Optional<Event> visitComingSoon(ComingSoon pComingSoon);
	public CriteriaVisitor clone();
}
