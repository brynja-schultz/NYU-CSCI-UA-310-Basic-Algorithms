//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// Class Item %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
/* ****************************************
 *	An item has two members:
 *		String key;
 *		int data;
 *	The types String and int may be unexpected because, typically,
 *		key is an int, and data is a string!
 *	Study carefully the many ways to construct an Item
 *
 *	Author: Chee Yap, Basic Algorithms
 *
 **************************************** */
import java.util.Random;

class Item {
	// MEMBERS: ====================================
		String key;
		int data;
	// CONSTRUCTORS: ===============================
		Item(String k, int d){// explicit data given
			key = k; data = d; }

		Item(String k, Random rg){// provide rg to generate data
			key = k; data = rg.nextInt(1000); }

		Item(String k){// 	Randomly generate the int data!!!
			key = k; data = (int)(10*Math.random()); }

		Item(int k){ // 	Use string value of k as key!!!
					 //		WARNING: unintuitive ordering, e.g., "25"<"7"
			key = String.valueOf(k); data = k; }

		Item(Item I){//		duplicate another item
			key = I.key; data = I.data; }
	// METHODS: ====================================
		void dump(){
			System.out.printf("<%s:%d>", key, data); }
		String stringValue(){
			return String.format("<%s:%d>", key, data); }
}//class Item
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
