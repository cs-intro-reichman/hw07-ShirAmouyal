

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for(int i=0;i<dictionary.length;i++){
			dictionary[i]=in.readString();
		}
        

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean found=false;
        for(int i=0;i<dictionary.length;i++){
			if(dictionary[i].equals(word)){
				found=true;
			}
		}
		return found;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		
        hashtag=hashtag.toLowerCase();
        for (int i = 1; i <= N; i++) { 
		if(existInDictionary(hashtag.substring(0,i), dictionary)){
         String word= hashtag.substring(0,i);
		 System.out.println(word);
		 breakHashTag(hashtag.substring(i, N), dictionary);
		 return;
		}
        }
    }

}
