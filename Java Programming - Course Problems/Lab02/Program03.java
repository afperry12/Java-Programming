class Program3 {
	public static void main(String[] args) {
		int daysPerWeek = 7;
		int weeksPerMonth = (28 + 4*30 + 7*31)/12;
		int monthsPerYear = 12;
		int daysPerYear = daysPerWeek * weeksPerMonth * monthsPerYear
		System.out.println("There are  + daysPerYear + " days in a year");
	}
}