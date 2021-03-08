import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MainClass {
	
	static List<Person> roster = new ArrayList<>();

	public static void main(String[] args) {
		roster.add(new Person("Aldo", Sex.MALE, LocalDate.of(1980, 10, 24), "aldo@polito.it"));
		roster.add(new Person("Chiara", Sex.FEMALE, LocalDate.of(1975, 1, 2), "chiara@polito.it"));
		roster.add(new Person("Enzo", Sex.MALE, LocalDate.of(1946, 3, 14), "enzo@polito.it"));
		roster.add(new Person("Paolo", Sex.MALE, LocalDate.of(1953, 5, 7), "paolo@polito.it"));
		roster.add(new Person("Elettra", Sex.FEMALE, LocalDate.of(2005, 12, 26), "elettra@polito.it"));
		roster.add(new Person("Anna", Sex.FEMALE, LocalDate.of(1978, 8, 15), "anna@polito.it"));
/*
		addPosition("programmer1","java", "c++", "c");
		addPosition("gui designer junior", "javascript", "html");
*/		
    // Approach 1: Create Methods that Search for Persons that Match One Characteristic
		System.out.println("******* Approach 1 *******");
        System.out.println("Persons older than 14:");
        printPersonsOlderThan(roster, 14);
        System.out.println();
        
    // Approach 2: Create More Generalized Search Methods
        System.out.println("******* Approach 2 *******");
        System.out.println("Persons between the ages of 14 and 53:");
        printPersonsWithinAgeRange(roster, 14, 53);
        System.out.println();
        
    // Approach 3: Specify Search Criteria Code in a Local Class
        System.out.println("******* Approach 3 *******");
        System.out.println("Persons who are eligible for Selective Service:");
        class CheckPersonEligibleForSelectiveService implements CheckPerson {
           public boolean test(Person p) {
                return p.getGender() == Sex.MALE
                		&& p.getAge() >= 14
                		&& p.getAge() <= 53;
            }
        }
        printPersons(roster, new CheckPersonEligibleForSelectiveService());
        System.out.println();
        
    // Approach 4: Specify Search Criteria Code in an Anonymous Class
        System.out.println("******* Approach 4 *******");
        System.out.println("Persons who are eligible for Selective Service (anonymous class):");
        printPersons(roster, new CheckPerson() {
        public boolean test(Person p) {
        	   return p.getGender() == Sex.MALE
            									&& p.getAge() >= 14
            									&& p.getAge() <= 53;
            						}
            					 }
        );
        System.out.println();
        
    // Approach 5: Specify Search Criteria Code with a Lambda Expression
        System.out.println("******* Approach 5 *******");
        System.out.println("Persons who are eligible for Selective Service (lambda expression):");
        printPersons(roster, (Person p) -> p.getGender() == Sex.MALE
                							&& p.getAge() >= 14
                							&& p.getAge() <= 53
        );
        System.out.println();
        
    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
        System.out.println("******* Approach 6 *******");
        System.out.println("Persons who are eligible for Selective Service (with Predicate parameter):");
        printPersonsWithPredicate(roster, p -> p.getGender() == Sex.MALE
        										&& p.getAge() >= 14
        										&& p.getAge() <= 53
        );
        System.out.println(); 
        
    // Approach 7: Use Lambda Expressions Throughout Your Application
        System.out.println("******* Approach 7 *******");
        System.out.println("Persons who are eligible for Selective Service (with Predicate and Consumer parameters):");
        processPersons(roster, p -> p.getGender() == Sex.MALE
        						&& p.getAge() >= 14
        						&& p.getAge() <= 53,
        				p -> p.printPerson()
        );
        System.out.println();
        
    //  Sorting by means of Comparator with Lambda Expressions
        System.out.println("Persons ordered by age:");
    	Comparator<Person> comp = (p1, p2) -> p1.getAge() - p2.getAge();
    	Collections.sort(roster, comp);
//      Collections.sort(roster, (p1,p2) -> p1.getAge() - p2.getAge());
        for (Person p : roster)
        	p.printPerson();
        System.out.println();
        
        System.out.println("Persons ordered by name:");
        Collections.sort(roster, (p1, p2) -> p1.getName().compareTo(p2.getName()));
        for (Person p : roster)
        	p.printPerson();
        System.out.println();
        
    //	****** Streams ******   
        
    //	Print sorted values of an array 
        System.out.println("Sorted array values:");
        int[] values = {3,6,7,2,9};
        IntStream.of(values)
        	.sorted()
        	.forEach(v -> System.out.print(" " + v));
        System.out.println();
        System.out.println();
        
    // Approach 8: Print names of members, forEach operation   
        System.out.println("******* Approach 8 *******");
        System.out.println("Members of the collection (bulk data operations):");
        roster
            .stream()
            .forEach(e -> System.out.println(e.getName()));
        System.out.println();
        
    // Approach 9: Print names of male members, forEach operation
        System.out.println("******* Approach 9 *******");
        System.out.println("Male members of the collection (bulk data operations):");
        roster
            .stream()
            .filter(e -> e.getGender() == Sex.MALE)
            .forEach(e -> System.out.println(e.getName()));
        System.out.println();

    // Approach 10: Use Bulk Data Operations That Accept Lambda Expressions as Parameters
        System.out.println("******* Approach 10 *******");
        System.out.println("Persons who are eligible for Selective Service (with bulk data operations):");
        roster
            .stream()
            .filter(p -> p.getGender() == Sex.MALE
                    	&& p.getAge() >= 14
                    	&& p.getAge() <= 53)
            .map(p -> p.getEmailAddress())
            .forEach(email -> System.out.println(email));
        System.out.println();
        
    // Approach 11: Get average age of male members of the collection: 
        System.out.println("******* Approach 11 *******");
        double average = roster
            .stream()
            .filter(p -> p.getGender() == Sex.MALE)
            .mapToInt(p -> p.getAge())
            .average()
            .getAsDouble();            
        System.out.println("Average age of male members (bulk data operations): " + average);
        System.out.println();
        
    // Approach 12: Sum of ages with sum operation 
        System.out.println("******* Approach 12 *******");
        int totalAge = roster
            .stream()
            .mapToInt(p -> p.getAge())
            .sum();            
        System.out.println("Sum of ages (sum operation): " + totalAge);
        System.out.println();
        
        // Approach 13: Sum of ages with reduce(accumulator)
        System.out.println("******* Approach 13 *******");
        int totalAgeReduce1 = roster
            .stream()
            .map(p -> p.getAge())
            .reduce((a, b) -> a + b)
            .get();            
        System.out.println("Sum of ages with reduce(accumulator): " + totalAgeReduce1);
        System.out.println();
        
    // Approach 14: Sum of ages with reduce(identity, accumulator) 
        System.out.println("******* Approach 14 *******");
        int totalAgeReduce2 = roster
            .stream()
            .map(p -> p.getAge())
            .reduce(0, (a, b) -> a + b);            
        System.out.println("Sum of ages with reduce(identity, accumulator): " + totalAgeReduce2);
        System.out.println();
        
    // Approach 15: Names of male members with collect operation
        System.out.println("******* Approach 15 *******");
        System.out.println("Names of male members with collect operation: ");         
        List<String> namesOfMaleMembersCollect = roster
            .stream()
            .filter(p -> p.getGender() == Sex.MALE)
            .map(p -> p.getName())
            .collect(Collectors.toList());
        namesOfMaleMembersCollect
            .stream()
            .forEach(p -> System.out.println(p));
        System.out.println();
        
     // Male persons grouped by name
        System.out.println("Groups of males with the same name:");
    	Map<String,List<Person>> MalesByName = roster
    						.stream()
    						.filter(p -> p.getGender() == Sex.MALE)
    						.collect(Collectors.groupingBy(Person::getName));
    	System.out.println(MalesByName);
    	System.out.println();

     // Male persons partitioned by age 54 
    	System.out.println("Males partitioned by younger than 54:");
    	Map<Boolean,List<Person>> MalesBy54 = roster
    					.stream()	
    					.filter(p -> p.getGender() == Sex.MALE)
    				    .collect(Collectors.partitioningBy(p -> p.getAge() <= 54));
    	System.out.println(MalesBy54);
    	System.out.println();
    	
     // Number of male persons older than 54 
    	System.out.println("Number of males older than 54:");
    	Long MalesOlder54 = roster
    				.stream()	
    				.filter(p -> p.getGender() == Sex.MALE
    					   && p.getAge() >= 54)
    				.collect(Collectors.counting());
    	System.out.println(MalesOlder54);
    	System.out.println();
     
	} // end of main
	
	
    // Approach 1: Create Methods that Search for Persons that Match One Characteristic	
	public static void printPersonsOlderThan(List<Person> roster, int age) {
	    for (Person p : roster) 
	        if (p.getAge() >= age) 
	            p.printPerson();
	}
	
    // Approach 2: Create More Generalized Search Methods	
	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		for (Person p : roster) 
			if (low <= p.getAge() && p.getAge() < high) 
				p.printPerson(); 
	}
	
    // Approach 3: Specify Search Criteria Code in a Local Class
    // Approach 4: Specify Search Criteria Code in an Anonymous Class	
	// Approach 5: Specify Search Criteria Code with a Lambda Expression
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for (Person p : roster) 
			if (tester.test(p)) 
				p.printPerson();	    
	}
	
    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) 
            if (tester.test(p)) 
                p.printPerson();
    }
    
    // Approach 7: Use Lambda Expressions Throughout Your Application
    public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : roster) 
            if (tester.test(p)) 
                block.accept(p);
    }
 /*  
    public static void addPosition(String... s) {
    	for(String ss : s)
    	System.out.println(ss);
    	System.out.println();
    }
 */
}
