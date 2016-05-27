package hello;

public class HelloSpring {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public void printHello() {
		System.out.println("Spring 4 : Hello ! " + name);
	}

	public HelloSpring(String name) {
		super();
		this.name = name;
	}

}
