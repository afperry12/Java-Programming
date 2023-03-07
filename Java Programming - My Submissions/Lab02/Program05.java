class Program05 {
	public static void main(String[] args) {
		double radius = 5; // 5 centimeters
		float height = 10;
		float baseArea = (float) (Math.PI * radius * radius);
		float coneVolume = (((float)1/3) * baseArea * height);
		System.out.println("The volume of our cone is " + coneVolume);
	}
}