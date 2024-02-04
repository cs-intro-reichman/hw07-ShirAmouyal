
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
	  return str.substring(0,str.length());
	}

	public static int levenshtein(String word1, String word2) {
		word1=word1.toLowerCase();
		word2=word2.toLowerCase();
		int a=word1.length();
		int b=word2.length();
		if (a==0){ return a;}
		if(b==0){return b;}
		if(word1.charAt(0)==word2.charAt(0)){
			levenshtein(tail(word1), tail(word2));
		}
		return Math.min(Math.min(levenshtein(tail(word1),word2), levenshtein(word1,tail(word2))), levenshtein(tail(word1), tail(word2)))+1;
		
		
	}

	public static String[] readDictionary(String fileName) {

	    String[] dictionary = new String[3000];
			In in = new In(fileName);
			for(int i=0;i<dictionary.length;i++){
				dictionary[i]=in.readString();
			}
			
	
			return dictionary;
		}
	

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		word=word.toLowerCase();
		int minLev=levenshtein(word, dictionary[0]);
		String minWord=dictionary[0];
		for(int i=0;i<dictionary.length;i++){
			int lev=levenshtein(word, dictionary[i]);
			if(lev<=minLev){
				minLev=lev;
				minWord=dictionary[i];
			}

		}
		if(minLev<=threshold){
			return minWord;
		}
		return word;
	}

}
