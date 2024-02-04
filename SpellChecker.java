
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
	  return str.substring(1,str.length());
	}

	public static int levenshtein(String word1, String word2) {
		word1=word1.toLowerCase();
		word2=word2.toLowerCase();
		int a=word1.length();
		int b=word2.length();
		if (a==0){ return b;}
		if(b==0){return a;}
		if(word1.charAt(0)==word2.charAt(0)){
			levenshtein(tail(word1), tail(word2));
		}
		int lev1=levenshtein(tail(word1),word2);
		int lev2=levenshtein(word1,tail(word2));
		int lev3=levenshtein(tail(word1), tail(word2));
		return Math.min(Math.min(lev1,lev2),lev3)+1;
		
		
	}

	public static String[] readDictionary(String fileName) {

	    String[] dictionary = new String[3000];
			In in = new In(fileName);
			for(int i=0;i<dictionary.length;i++){
				dictionary[i]=in.readLine();
			}
			
	
			return dictionary;
		}
	

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word=word.toLowerCase();
		int minLev=threshold+1;
		String minWord="";
		for(int i=0;i<dictionary.length;i++){
			int lev=levenshtein(word, dictionary[i]);
			if(lev<minLev){
				minLev=lev;
				minWord=dictionary[i];
			}

		}
		if(minLev>=threshold){
			return word;
		}
		return minWord;
	}

}
