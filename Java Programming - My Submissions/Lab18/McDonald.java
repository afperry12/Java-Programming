abstract class Animal {
	public abstract String sound();

	public abstract String toString();

	void WriteVerse() {
		System.out.println("Old McDonald had a farm, EIEIO,");
		System.out.print("And on his farm he had a " + this.toString());
		System.out.println(" , EIEIO,");
		String s = this.sound();
		System.out.print("With a " + s + ", " + s + " here, ");
		System.out.println("a " + s + ", " + s + " there...");
	}
}

class Duck extends Animal {

	@Override
	public String sound() {
		return "quack";
	}

	@Override
	public String toString() {
		return "duck";
	}

}

class Pig extends Animal {

	@Override
	public String sound() {
		return "oink";
	}

	@Override
	public String toString() {
		return "pig";
	}

}

class Possum extends Animal {

	@Override
	public String sound() {
		return "zook-zook";
	}

	@Override
	public String toString() {
		return "possum";
	}

}

class Cat extends Animal {

	@Override
	public String sound() {
		return "EEEEEEEEAAAAAAAAAAAAAAAAAARRRRRRRRRRRRRRRRR";
	}

	@Override
	public String toString() {
		return "cat";
	}

}

class Dog extends Animal {

	@Override
	public String sound() {
		return "woooooofwoofwooofwooofffffffff";
	}

	@Override
	public String toString() {
		return "dog";
	}

}

public class McDonald {

	public static void main(String[] args) {

		for (int x = 0; x < 10; x++) {
			int randomIntFromZeroThroughFour = (int) (5 * Math.random());
			if (randomIntFromZeroThroughFour == 0) {
				Animal duck = new Duck();
				duck.WriteVerse();
			} else if (randomIntFromZeroThroughFour == 1) {
				Animal pig = new Pig();
				pig.WriteVerse();
			} else if (randomIntFromZeroThroughFour == 2) {
				Animal possum = new Possum();
				possum.WriteVerse();
			} else if (randomIntFromZeroThroughFour == 3) {
				Animal cat = new Cat();
				cat.WriteVerse();
			} else if (randomIntFromZeroThroughFour == 4) {
				Animal dog = new Dog();
				dog.WriteVerse();
			}
			if (x != 9) {
				System.out.println();
			}
		}

	}
}