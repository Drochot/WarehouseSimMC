package com.nhlstenden.amazonsimulatie.models;

/* Een simpele klasse om gemakkelijk de x en z van objecten in lists te kunnen zetten. */

public class IntPair {
 double x;
 double z;
 
 	public IntPair(double x, double z){
 		this.x = x;
 		this.z = z;
	 }
	 
	 /**
	  * @return the x
	  */
	 public double getX() {
		 return x;
	 }

	 /**
	  * @return the z
	  */
	 public double getZ() {
		 return z;
	 }

	 // een simpele equals override om intpairs te kunnen vergelijken
	 // checkt gelijke obj, null, class en value
	 @Override
	 public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntPair other = (IntPair) obj;
        if (x != other.x || z != other.z)
            return false;
    
        return true;
    }
}
