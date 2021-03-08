package tollroad;

@SuppressWarnings("serial")
public class SegmentNotPresent extends Exception {

	public SegmentNotPresent() {
		System.out.println("SEGMENT NOT FOUND IN DATABASE");
	}
	
}
