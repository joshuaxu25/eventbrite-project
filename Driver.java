import java.time.LocalDate;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {

		/**
		 * Create a new manager.
		 */
		EventManagement bob = new EventManagement();
		
		/**
		 * Create the list of VIPs for the first concert.
		 */
		ArrayList<String> concert1VIPs = new ArrayList<String>();
		concert1VIPs.add("vip1");
		concert1VIPs.add("vip2");
		concert1VIPs.add("vip3");
		concert1VIPs.add("vip4");
		concert1VIPs.add("vip5");
		
		/**
		 * Create the list of VIPs for the first gala.
		 */
		ArrayList<String> galaVIPs1 = new ArrayList<String>();
		galaVIPs1.add("vip7");
		galaVIPs1.add("vip6");
		galaVIPs1.add("vip8");
		galaVIPs1.add("vip9");
		
		/**
		 * Create the list of VIPs for the second concert.
		 */
		ArrayList<String> concert2VIPs = new ArrayList<String>();
		concert2VIPs.add("vip10");
		concert2VIPs.add("vip11");
		concert2VIPs.add("vip12");
		concert2VIPs.add("vip13");
		concert2VIPs.add("vip14");
		concert2VIPs.add("vip15");
		
		/**
		 * Create the list of VIPs for the second gala.
		 */
		ArrayList<String> galaVIPs2 = new ArrayList<String>();
		galaVIPs2.add("vip16");
		galaVIPs2.add("vip17");
		galaVIPs2.add("vip18");
		galaVIPs2.add("vip19");
		galaVIPs2.add("vip20");
		
		/**
		 * Add all the events that Bob wants to create (Contains all types of events). The program will not allow creation
		 * of duplicate events with the same date and location (Flyweight Design Pattern).
		 */
		bob.addConcertEvent("concert1", 50, Location.BellCentre, LocalDate.of(2014, 11, 11), 110, "Drake", concert1VIPs);
		bob.addWorkshopEvent("workshop1", 60, Location.ParcJeanDrapeau, LocalDate.of(2021, 12, 11), 100, new ArrayList<Workshop>());
		bob.addGalaEvent("gala1", 70, Location.BellCentre, LocalDate.of(2031, 11, 30), 90, galaVIPs1);
		bob.addScreeningEvent("Screening", 80, Location.BellCentre, LocalDate.of(2051, 9, 11), 80, Rating.G);
		bob.addComingSoon("comingsoon1", LocalDate.of(2013, 5, 23));
		bob.addConcertEvent("concert2", 50, Location.BellCentre, LocalDate.of(2014, 12, 11), 120, "Drake", concert2VIPs);
		bob.addGalaEvent("gala2", 120, Location.BellCentre, LocalDate.of(2033, 11, 30), 1000, galaVIPs2);
		bob.addScreeningEvent("screening2", 60, Location.BellCentre, LocalDate.of(2051, 9, 11), 80, Rating.G);
		
		/**
		 * Create a festival of all the hosted events (Composite Design Pattern).
		 */
		bob.addFestivalEvent("festival1", bob.getHostedEvents());
		
		/**
		 * Create a criteria to filter a price range.
		 */
		FilterRangeVisitor filterRange = new FilterRangeVisitor(50, 70);
		
		/**
		 * Create a criteria to filter a location.
		 */
		FilterLocationVisitor filterLocation = new FilterLocationVisitor(Location.BellCentre);
		
		/**
		 * Combine all filters into one filter (Composite Design Pattern).
		 */
		ArrayList<CriteriaVisitor> filters = new ArrayList<CriteriaVisitor>();
		
		filters.add(filterRange);
		filters.add(filterLocation);
		
		FilterCompositeVisitor filterComposite = new FilterCompositeVisitor(filters);

		/**
		 * Filter out all events by the composite filter (Visitor Design Pattern). In this case, only 
		 * the following events are filtered: (concert1, gala1, concert2, screening2)
		 */
		FilterResult filteredEvents = bob.filterEventsBy(filterComposite);
		
		/**
		 * Create the percentages for the individual events.
		 */
		ProfitCalculationVisitor aPercentages = new ProfitCalculationVisitor(0.2, 0.15, 0.3, 0.5);
		
		/**
		 * Calculate the expected profits and the number of VIPs (Visitor Design Pattern).
		 * For each filtered event:
		 * - concert1: 50 x 110 x 0.2 = 1100
		 * - gala1: 70 x 90 x 0.3 = 1890
		 * - concert2: 50 x 120 x 0.2 = 1200
		 * - screening2: 60 x 80 x 0.5 = 2400
		 * - total: 1100 + 1890 + 1200 + 2400 = 6590
		 */
		double aExpectedProfits = bob.calculateProfits(filteredEvents, aPercentages);
		
		/**
		 * For each filtered event:
		 * - concert1: 5
		 * - gala1: 4
		 * - concert2: 6
		 * - screening2: 0
		 * - total: 5 + 4 + 6 + 0 = 15
		 */
		int aVIPNumbers = bob.calculateVIPs(filteredEvents);
		
		
		System.out.println("The expected profits from the filtered events is " + aExpectedProfits + "$.");
		System.out.println("The expected number of VIPs from the filtered events is " + aVIPNumbers);
	}

}
