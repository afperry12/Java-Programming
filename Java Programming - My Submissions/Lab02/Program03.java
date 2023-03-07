class Program03 {
	public static void main(String[] args) {
		double daysPerWeek = 7;
		double weeksPerMonth = (double)(28 + (4*30) + (7*31))/12;
		System.out.println(weeksPerMonth);
		double monthsPerYear = 12;
		double daysPerYear = weeksPerMonth * monthsPerYear;
		System.out.println("There are " + daysPerYear + " days in a year");
	}
}