public class Jewellery {
 	public static void main(String[] args) {

		ArrayList<Arraylist<Double>> vault = new ArrayList<ArrayList<Double>>();
		int maxWeight = weight;

		ArrayList<Double> eachBag = new ArrayList<Double>();
		//ArrayList<ArrayList<Double>> valuePerKg = new ArrayList<ArrayList<Double>>();
		double result = 0;

        for(int i = 0; i <= vault.size(); i++) {
        	eachBag = vault.get(i);
        	eachBag.add(eachBag.get(1)/eachBag.get(0));
        	vault.set(i, eachBag);
        }

        

        while(maxWeight > 0) {
        	for(int i = 0; i <= vault.size(); i++) {


        	}
        	maxWeight = maxWeight - x;
        }

    }
}