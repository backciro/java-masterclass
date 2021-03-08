import java.time.LocalDate;


public class Person {

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;
    
    public Person(String n, Sex s, LocalDate b, String em) {
    	name = n;
    	gender = s;
    	birthday = b;
    	emailAddress = em;
    }
    
    public String getName() {
        return name;
    }

    public int getAge() {
        return LocalDate.now().compareTo(birthday);
    }
    
    public Sex getGender() {
        return gender;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void printPerson() {
    	System.out.println( this + ", " + emailAddress );
    }
    
    public String toString() {
        return name+ ", "+ gender+", "+ birthday;
    }

}