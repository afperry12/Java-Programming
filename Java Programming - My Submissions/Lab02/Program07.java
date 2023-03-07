class Program07 {
	public static void main(String[] args) {
		short secondsPerMinute = (short)60;
		short minutesPerHour = (short)60;
		int secondsPerHour = (int)secondsPerMinute * (int)minutesPerHour;
		short hoursPerDay = (short)24;
		int secondsPerDay = (int)secondsPerHour * (int)hoursPerDay;
		short daysPerYear = (short)365;
		int secondsPerYear = (int)secondsPerDay * (int)daysPerYear;
		
		System.out.println("There are " + secondsPerYear + " seconds in a year.");
		float approx = (float)Math.PI * (float)Math.pow(10, 7);
		System.out.println("A good approximation is " + approx);
	}
}