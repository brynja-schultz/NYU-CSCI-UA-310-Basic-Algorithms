/* file: Zoombini.java
 *
 * BACKGROUND: I asked students to generate random strings as
 * 		data for their binary search tree (homework 3).
 *		We want random strings that are pronounceable in English,
 *  	E.g., random string such as
 *			Seteo, Di, Swofixewubagluequ, Veejesufograbluda, Ha, 
 *			Jeflexo, Beyei, Mugeo, Lue, Fa
 *	We call such strings a "Zoombini name".
 *	The idea comes from a children's game (1990s) called "Zoombini" w
 *	which involves any number of mythical critters called Zoombinis,
 *	each with a unique name.  Read about the game "Zoombini" in Google!
 *	I had used similar ideas to generate "Ethnic Zoombinis names" such as
 *  American/Chinese/Indian/Korean/Italian Zoombinis in Data Structures.
 *
 * NEW DESIGN in 2023fall:
 *		-- focus on small letters only.  We can easily convert
 *			to caps (or initial cap) as desired
 *		-- avoid using the entire ascii set of 256 chars!
 *		-- introduce AtoZ="abc...xyz" and VOWELS="aeiou".
 *		-- USEFUL string methods:  let sss be a string
 *				sss.charAt(int i)		
 *				sss.setCharAt(int i, char c) -- insert c at pos i
 *
 *				sss.indexOf(char c)			-- return -1 if not found
 *				sss.indexOf(char c, int i) -- search for c from pos i
 *				sss.indexOf(String s)		-- search for s in sss
 *				sss.lastIndexOf(char c)		-- indexing from the end
 *
 *				sss.substring(int i, int j)	-- sss[i..j]  (i>=0)
 *				sss.substring(int i) 		-- sss[i..$]  ($=end)
 *
 *  For information about the Character class,
 *		see the notes at the end of this file.
 *
 * Author: Chee Yap
 * 	   Data Structures Class
 *
 ************************************************** */

import java.util.Random;

public class Zoombini {
	/* MEMBERS *********************/
	/* *************************** */
	static int n;  // number to words to generate
	static Random rg;
	static final String VOWELS = "aeiou"; // no caps!
	static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz"; // no caps!
	static final String AtoZ = "abcdefghijklmnopqrstuvwxyz"; //unused!
	static final String DIPHTHONGS =
		"aeaiaueeeieoeuieioiuooouuaueui"; //unused!
	 	// "ae ai au ee ei eo eu ie io iu oo ou ua ue ui"; //unused!
	static final String CONSONANT2 =
		"blclflglplslbrcrdrfrgrprtrscsksmsnspstswtw";
		// "bl, cl, fl, gl, pl, sl, br, cr, dr, fr, gr, pr, tr, sc, sk,
		// 	sm, sn, sp, st, sw, tw"
	static final String CONSONANT3 = "scrshrsplsprstrthr";
		// scr- shr- spl- spr- str- thr-
	static final String CONSONANTend = "ntspst";
		// nt, sp, st
// See consonant blends in:
// https://study.com/academy/lesson/consonant-blends-definition-examples.html
// https://scholarwithin.com/consonant-blends


	/* CONSTRUCTORS *********************/
	/* *************************** */

	// initialize the random number generator!
	Zoombini(){
	  	rg = new Random(); }
	Zoombini(Random rgen){
	  	rg = rgen; }

	/* METHODS *********************/
	/* *************************** */
	public static boolean isVowel(char c) {
		if (VOWELS.indexOf(c)<0) return false;
		return true; }

	// return a random vowel (it is a string!)
	public static String getVowel(){
		if (rg.nextInt(5)==0){
				int ll = DIPHTHONGS.length();
				int ii = rg.nextInt(ll/2);
				return (DIPHTHONGS.substring(2*ii,2+2*ii));
		}
		return (""+VOWELS.charAt(rg.nextInt(5))); }

	// return a random small consonant:
	public static String getConsonant(){
		if (rg.nextInt(5)==0){
				int ll = CONSONANT2.length();
				int ii = rg.nextInt(ll/2);
				return (CONSONANT2.substring(2*ii,2+2*ii));
		}
		return (""+CONSONANTS.charAt(rg.nextInt(21))); }

	// return a random syllable of form "consonant+vowel"
	// NOTES: we can make this more sophisticated by
	//		-- allowing syllables with no consonants
	//		-- adding terminal consonants
	public static String getSyllable(){
		return ("" + getConsonant() + getVowel()); }

	// Replace the character at pos i by char c:
	//	We need to roll our own!!!
	public static String replaceChar(String sss, int i, char c){
		if (i==0)
			return("" + c + sss.substring(i+1)); 
		return(sss.substring(0,i-1) + c + sss.substring(i+1)); }

	// this returns a random word of with expected 1+k syllables:
	// NOTE: the initial char is alway upper case
	public static String getWord(int k){
		String first = getSyllable();
			first = replaceChar(first,0,
						Character.toUpperCase(first.charAt(0)));
		while (rg.nextInt(k)>0)
			first += getSyllable();
		return first; }

	// Default random word: expected number of syllables is 3=1+2:
	public static String getWord(){
		return getWord(2); }

	// Default Zoombini name: expected number of syllables is 3=2+1:
	//  !!!! This is the only non-static member of the class!
	public String name(){
		return getWord(2); }

	/* MAIN METHOD*********************/
	/* *************************** */
	public static void main(String[] args){
		int ss = (args.length>0)? Integer.valueOf(args[0]) : 0;
		int nn = (args.length>1)? Integer.valueOf(args[1]) : 6;
		int mm = (args.length>2)? Integer.valueOf(args[2]) : 0;

		rg = (ss==0)? new Random() : new Random(ss);

		System.out.print("Random vowels: (");
		for (int i=1; i<nn; i++){
			System.out.print(getVowel() + ", ");
		}
		System.out.println(getVowel() + ")");

		System.out.print("Random consonants: (");
		for (int i=1; i<nn; i++){
			System.out.print(getConsonant() + ", ");
		}
		System.out.println(getConsonant() + ")");

		System.out.print("Random syllable: (");
		for (int i=1; i<nn; i++){
			System.out.print(getSyllable() + ", ");
		}
		System.out.println(getSyllable() + ")");

		Zoombini zoom = new Zoombini(rg);

		System.out.print("Random Zoombini name: (");
		for (int i=1; i<nn; i++){
			System.out.print(zoom.name() + ", ");
		}
		System.out.println(zoom.name() + ")");

	}//main
}// class Zoombini


/* ************************************************** 
 * NOTES for the Character class:
 *	(These notes may be obsolete since we no longer use Character class)
 *
 * First of all, the Java primitive type called "char"
 * represents a 2-byte character (in an encoding called Unicode).
 * Each character has an associated integer value: for the
 * usual character set in the US (called ASCII character set), the
 * integer values
 * lies in the range 0-255.  So if you generate a random integer between
 * 0 and 255, you can convert this integer into a character by casting. 
 * Here is how you back-and-forth between "int" and "char" by casting:
 *
 *	System.out.println("A is " + (int)'A');
 *	System.out.println("65 is " + (char)65);
 *
 * Just as Integer class is a wrapper around the primitive type "int",
 * Java has the Character class as wrapper around the type "char".
 *
 *       Character c = new Character ('A');
 *
 * The class Character has many useful static methods.  Because it 
 * stores knowledge abut Unicode characters, this class is actually quite
 * complex.  If you want to generate a random string over the English
 * alphabet, there is a useful method in Character class to check if a
 * character is a letter:
 *
 * 	public static boolean isLetter(char c)
 *
 * E.g., you can use it like this:
 *
 * 	 Random generator = new Random(seed);
 *
 *       char c = (char)generator.nextInteger(256);	// random character
 *
 *       while (!Character.isLetter(c))
 *       	c = (char)generator.nextInteger(256);	// try another one
 *
 *
 * Two useful methods in Character class are:
 *
 * 	c.charValue() -- returns the char stored in the Character object c.
 * 	c.toString()  -- returns the char as a String object.
 *
 * You may also want to compare characters:
 * 	public int compareTo(Character cc)
 * 	public int compareTo(Object cc)	-- alternatively, use an object
 *		formulation to let Java Collection to sort characters
 * 		in a collection.
 * 	final boolean equals(Object o)
 *
 * Other utilities: 
 * 	public static boolean isDigit(char c)
 * 	public static boolean isLetter(char c)
 * 	public static boolean isUpperCase(char c)
 *
 * 	public static char toUpperCase(char c)
 * 	public static char toLowerCase(char c)
 * 
 *************************************************** */
