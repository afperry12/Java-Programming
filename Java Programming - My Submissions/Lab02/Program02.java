class Program02 {
	public static void main(String[] args) {
		double pizzaPrice = 24.50; // dollars
		double taxRate = 0.08625; // percent
		double tip = 0.20; // percent
		double total = (pizzaPrice*taxRate) + (tip*pizzaPrice) + pizzaPrice;
		System.out.println("Total price of a $" + pizzaPrice + " pizza is $" + total);
	}
}