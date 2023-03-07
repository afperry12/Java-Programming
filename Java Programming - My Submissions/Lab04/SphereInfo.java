import java.util.Arrays;
import java.util.List;

public class SphereInfo {
    public static void main(String args[]){
    class Sphere{
        double diameter;

        public void setDiameter(double diameter) {
            this.diameter = diameter;
        }

        public double radius() {
            return diameter/2;
        }

        public double diameter() {
            return diameter;
        }

        public double surfaceArea() {
           return (double)Math.PI*((double)4.0)*(double)Math.pow((double)(diameter/2.0), 2);
        }

        public double volume() {
            return Math.PI*(4.0/3.0)*Math.pow((diameter/2.0), 3);
        }
    }
   
    // Create a list of doubles that will be used as diameters of spheres
    List<Double> diameters = Arrays.asList(0.0,1.0,7.5);

    // Create array of spheres that is the same size as the number of diameters available
    Sphere[] sphere;
    sphere = new Sphere[diameters.size()];

    // Create for loop that creates spheres, sets diameter using the corresponding value in the diameters 
    // list, then calculates and prints required information
    for (int j=0; j<sphere.length; j++) {
       sphere[j] = new Sphere();
       sphere[j].setDiameter(diameters.get(j));
       Double radius = sphere[j].radius();
       Double surfaceArea = sphere[j].surfaceArea();
       Double volume = sphere[j].volume();
       System.out.println("A sphere of radius " + radius + " has surface area " + surfaceArea + " and volume " + volume);
    }    
}
}
