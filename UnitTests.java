import static org.junit.Assert.*;
import org.junit.Assert; import org.junit.Test;
import org.junit.*;
import java.util.*;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class UnitTests {

	EventManagement aEventManager = new EventManagement();
	
	@Test
	public void TestingCreatingDuplicates() {
		
		try {
			aEventManager.addConcertEvent("asdf", 20, Location.BellCentre, LocalDate.of(2011, 11, 11), 120, "Drake", new ArrayList<String>());
			aEventManager.addConcertEvent("as", 20, Location.Multiple, LocalDate.of(2011, 11, 11), 120, "Drake", new ArrayList<String>());
			fail("Assertion Error should have been thrown");
			assertTrue(false);
		} catch (AssertionError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestingProfitCalculationForDifferentEvents() {
		
		aEventManager.addConcertEvent("asdf", 50, Location.BellCentre, LocalDate.of(2014, 11, 11), 110, "Drake", new ArrayList<String>());
		aEventManager.addWorkshopEvent("asdf", 60, Location.BellCentre, LocalDate.of(2021, 12, 11), 100, new ArrayList<Workshop>());
		aEventManager.addGalaEvent("asdf", 70, Location.BellCentre, LocalDate.of(2031, 11, 30), 90, new ArrayList<String>());
		aEventManager.addScreeningEvent("asdf", 80, Location.BellCentre, LocalDate.of(2051, 9, 11), 80, Rating.G);
		aEventManager.addComingSoon("asd", LocalDate.of(2012, 2, 14));
		
		ProfitCalculationVisitor aPercentages = new ProfitCalculationVisitor(0.2, 0.3, 0.4, 0.5);
		FilterRangeVisitor aFilterRange = new FilterRangeVisitor(50, 80);
		
		FilterResult aFilteredEvents = aEventManager.filterEventsBy(aFilterRange);
		double aProfits = aEventManager.calculateProfits(aFilteredEvents, aPercentages);
		
		assertEquals(aProfits, 8620, 0.01); 
		
	}
	
	@Test
	public void TestingProfitCalculationForFestival() {
		
		aEventManager.addConcertEvent("a", 50, Location.BellCentre, LocalDate.of(2013, 11, 11), 110, "Drake", new ArrayList<String>());
		aEventManager.addWorkshopEvent("b", 60, Location.BellCentre, LocalDate.of(2011, 12, 11), 100, new ArrayList<Workshop>());
		aEventManager.addGalaEvent("c", 70, Location.BellCentre, LocalDate.of(2011, 11, 30), 90, new ArrayList<String>());
		aEventManager.addScreeningEvent("d", 80, Location.OlympicStadium, LocalDate.of(2011, 9, 11), 80, Rating.G);
		
		aEventManager.addFestivalEvent("e", aEventManager.getHostedEvents());
		
		
		ProfitCalculationVisitor aPercentages = new ProfitCalculationVisitor(0.2, 0.3, 0.4, 0.5);
		FilterRangeVisitor aFilterRange = new FilterRangeVisitor(50, 200);
		
		FilterResult aFilteredEvents = aEventManager.filterEventsBy(aFilterRange);
		double aProfits = aEventManager.calculateProfits(aFilteredEvents, aPercentages);
		assertEquals(aProfits, 2 * 8620, 0.01);
		
	}
	
	@Test
	public void TestingVIPCalculationForDifferentEvents() {
		ArrayList<String> aConcertVIPs = new ArrayList<String>();
		ArrayList<String> aGalaVIPs = new ArrayList<String>();
		
		aConcertVIPs.add("a");
		aConcertVIPs.add("b");
		aConcertVIPs.add("c");
		aConcertVIPs.add("d");
		aConcertVIPs.add("e");
		
		aGalaVIPs.add("f");
		aGalaVIPs.add("g");
		aGalaVIPs.add("h");
		aGalaVIPs.add("i");
		
		aEventManager.addConcertEvent("asdf", 50, Location.BellCentre, LocalDate.of(2014, 11, 11), 110, "Drake", aConcertVIPs);
		aEventManager.addWorkshopEvent("asdf", 60, Location.BellCentre, LocalDate.of(2021, 12, 11), 100, new ArrayList<Workshop>());
		aEventManager.addGalaEvent("asdf", 70, Location.BellCentre, LocalDate.of(2031, 11, 30), 90, aGalaVIPs);
		aEventManager.addScreeningEvent("asdf", 80, Location.BellCentre, LocalDate.of(2051, 9, 11), 80, Rating.G);
		
		FilterRangeVisitor aFilterRange = new FilterRangeVisitor(50, 80);
		
		FilterResult aFilteredEvents = aEventManager.filterEventsBy(aFilterRange);
		int aVIPNumbers = aEventManager.calculateVIPs(aFilteredEvents);
		
		assertEquals(aVIPNumbers, 9);
	}
	
	@Test
	public void TestingFilterByRangeForDifferentEvents() {
		
		aEventManager.addConcertEvent("concert", 50, Location.BellCentre, LocalDate.of(2014, 11, 11), 110, "Drake", new ArrayList<String>());
		aEventManager.addWorkshopEvent("workshop", 60, Location.BellCentre, LocalDate.of(2021, 12, 11), 100, new ArrayList<Workshop>());
		aEventManager.addGalaEvent("gala", 70, Location.BellCentre, LocalDate.of(2031, 11, 30), 90, new ArrayList<String>());
		aEventManager.addScreeningEvent("screening", 60, Location.BellCentre, LocalDate.of(2051, 9, 11), 80, Rating.G);
		
		FilterRangeVisitor aFilterRange = new FilterRangeVisitor(50, 60);
		FilterResult aFilteredEvents = aEventManager.filterEventsBy(aFilterRange);
		
		/**
		 * Reflection Test
		 */
		try {
			Class aClassFilterResult = aFilteredEvents.getClass();
			Field fieldName = aClassFilterResult.getDeclaredField("aFilteredEvents");
			fieldName.setAccessible(true);
			List<Event> aList = (ArrayList<Event>) fieldName.get(aFilteredEvents);
			assertEquals("concert", aList.get(0).getName());
			assertEquals("workshop", aList.get(1).getName());
			assertEquals("screening", aList.get(2).getName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void TestingFilterByLocationForDifferentEvents() {
		aEventManager.addConcertEvent("concert", 50, Location.OlympicStadium, LocalDate.of(2014, 11, 11), 110, "Drake", new ArrayList<String>());
		aEventManager.addWorkshopEvent("workshop", 60, Location.BellCentre, LocalDate.of(2021, 12, 11), 100, new ArrayList<Workshop>());
		aEventManager.addGalaEvent("gala", 70, Location.OlympicStadium, LocalDate.of(2031, 11, 30), 90, new ArrayList<String>());
		aEventManager.addScreeningEvent("screening", 80, Location.Multiple, LocalDate.of(2051, 9, 11), 80, Rating.G);
		
		FilterLocationVisitor aFilterLocation = new FilterLocationVisitor(Location.OlympicStadium);
		FilterResult aFilteredEvents = aEventManager.filterEventsBy(aFilterLocation);
		
		/**
		 * Reflection Test
		 */
		try {
			Class aClassFilterResult = aFilteredEvents.getClass();
			Field fieldName = aClassFilterResult.getDeclaredField("aFilteredEvents");
			fieldName.setAccessible(true);
			List<Event> aList = (ArrayList<Event>) fieldName.get(aFilteredEvents);
			assertEquals("concert", aList.get(0).getName());
			assertEquals("gala", aList.get(1).getName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test 
	public void TestingFilterByCompositeForDifferentEvents() {
		aEventManager.addConcertEvent("concert", 50, Location.OlympicStadium, LocalDate.of(2014, 11, 11), 110, "Drake", new ArrayList<String>());
		aEventManager.addWorkshopEvent("workshop", 60, Location.BellCentre, LocalDate.of(2021, 12, 11), 100, new ArrayList<Workshop>());
		aEventManager.addGalaEvent("gala", 70, Location.OlympicStadium, LocalDate.of(2031, 11, 30), 90, new ArrayList<String>());
		aEventManager.addScreeningEvent("screening", 80, Location.Multiple, LocalDate.of(2051, 9, 11), 80, Rating.G);
		
		
		FilterRangeVisitor aFilterRange = new FilterRangeVisitor(50, 60);
		FilterLocationVisitor aFilterLocation = new FilterLocationVisitor(Location.BellCentre);
		
		ArrayList<CriteriaVisitor> aFilters = new ArrayList<CriteriaVisitor>();
		
		aFilters.add(aFilterRange);
		aFilters.add(aFilterLocation);
		
		FilterCompositeVisitor aFilterComposite = new FilterCompositeVisitor(aFilters);
		FilterResult aFilteredEvents = aEventManager.filterEventsBy(aFilterComposite);
		
		/**
		 * Reflection Test
		 */
		try {
			Class aClassFilterResult = aFilteredEvents.getClass();
			Field fieldName = aClassFilterResult.getDeclaredField("aFilteredEvents");
			fieldName.setAccessible(true);
			List<Event> aList = (ArrayList<Event>) fieldName.get(aFilteredEvents);
			assertEquals("workshop", aList.get(0).getName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	@Test 
	public void TestingStubVIPCalculationForConcert() {
		/**
		 * Stub Test
		 */
		class EventManagementStub {
			ArrayList<Event> aHostedEventsStub = new ArrayList<Event>();
			
			public void addConcertEventStub(String pName, double pPrice, Location pLocation, LocalDate pDate, int pNumberTickets, String pArtist, ArrayList<String> pVIPs){    	
		    	aHostedEventsStub.add(new Concert(pName, pPrice, pLocation, pDate, pNumberTickets, pArtist, pVIPs));
		    }
			public FilterResult filterEventsByStub(CriteriaVisitor pCriteria) {
				return new FilterResult(pCriteria, aHostedEventsStub);
			}
		}
		
		EventManagementStub aEventManagerStub = new EventManagementStub();
		
		ArrayList<String> aConcertVIPs = new ArrayList<String>();
		
		aConcertVIPs.add("a");
		aConcertVIPs.add("b");
		aConcertVIPs.add("c");
		aConcertVIPs.add("d");
		aConcertVIPs.add("e");
		
		aEventManagerStub.addConcertEventStub("concert", 50, Location.OlympicStadium, LocalDate.of(2014, 11, 11), 110, "Drake", aConcertVIPs);
		FilterLocationVisitor aFilterLocation = new FilterLocationVisitor(Location.OlympicStadium);
		FilterResult aFilteredEvents = aEventManagerStub.filterEventsByStub(aFilterLocation);
		
		/**
		 * Reflection Test
		 */
		try {
			Class aClassFilterResult = aFilteredEvents.getClass();
			Field fieldName = aClassFilterResult.getDeclaredField("aFilteredEvents");
			fieldName.setAccessible(true);
			List<Event> aList = (ArrayList<Event>) fieldName.get(aFilteredEvents);
			assertEquals("concert", aList.get(0).getName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
