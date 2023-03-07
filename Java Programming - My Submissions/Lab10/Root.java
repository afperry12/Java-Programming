public class Root {
    public static void main(String[] args) {
        try {
        // Set variables for low, high, average, and previous average
        double low = 0.0;
        double high = Double.parseDouble(args[0]);
        double avg = (low+high)/2.0;
        double previousAvg;
        // Handle negatives
        if(String.valueOf(args[0]).contains("-")) {
            System.err.print("ERROR");
            System.exit(0);
        }
        // While the difference between high and low is greater than .1% of low,
        // set previous average, if avg^2 > original number then set high to average
        // if avg^2 < original number, set low to average. Reset avg to new average of high and low values
        // Finally, if the last average and the new average are the same, detect repetition and break.
        while((high-low)>(low*0.001)){
            previousAvg=(low+high)/2.0;
            if(Math.pow(avg,2) > Double.parseDouble(args[0])) {
                high=avg;
            } else if(Math.pow(avg,2) < Double.parseDouble(args[0])) {
                low=avg;
            }
            avg=(low+high)/2.0;
            if(previousAvg==avg){
                break;
            }
        }
        System.out.print(avg);
    } 
    // Catch and report all errors
    catch(Exception e){
        System.err.print("ERROR");
    }
    }
}
