class Program10 {
	public static void main(String[] args) {
		// Initialize char with int.
		// Figure out the answer and fill in correct name.
		char special = 177;
		System.out.print("The common name for the " + special + " symbol is the ");
		System.out.print("Plus-Minus");
		System.out.println(" symbol.");
		
		// Initialize char with Unicode constant.
		char another = '\u0009';
		System.out.print("This" + another + "char is the ");
		System.out.print("Tab");
		System.out.println(" symbol.");
	}
}