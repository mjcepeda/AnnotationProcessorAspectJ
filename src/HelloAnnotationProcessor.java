import edu.rit.annotation.StreamsFactory;

public class HelloAnnotationProcessor {

	public static void main(String[] args) {
		callOne();
	}
	@StreamsFactory(query="example", name="example")
	public static void callOne() {
		System.out.println("In call One");
	}

}
