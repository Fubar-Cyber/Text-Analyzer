package cop2805;

import java.io.*;
import java.util.*;
import org.jsoup.*; //allows manipulation of HTML
import org.jsoup.nodes.*;

public class Main2 {
	public static void main(String[] args) throws IOException {
	
		Document page = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
		Element div = page.select("div.chapter").first(); //specify location of web page to extract data
		String divText = div.text();
	
		Scanner sc = new Scanner(divText); //obtain input
	
		Map<String,Integer> map = new HashMap<String, Integer>(); //map keys to value and create table
	
		while (sc.hasNext()){ //loop
			String word = sc.next();
			word = word.replaceAll(",", ""); // eliminate periods and commas
		
			if(map.containsKey(word) == false) {
				map.put(word, 1); //associate value and key for first time word
			}
				else {
					int count = (int)(map.get(word));
					map.remove(word);
					map.put(word,count + 1); // eliminate repeated word and increase count of repeated words
				}
		}
		// Collections.sort method to sort list
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		List<Map.Entry<String, Integer>> al = new ArrayList<Map.Entry<String, Integer>>(set);
		Collections.sort(al, new Comparator<Map.Entry<String, Integer>>() {
		
			public int compare(Map.Entry<String, Integer> num, Map.Entry<String, Integer> words) {
				return (words.getValue()).compareTo(num.getValue());
			}
		});
		
		System.out.printf("%-15s %-15s%n","Word", "Count"); //decorative header filler for structure
		System.out.printf("%-15s %-15s%n","=========", "=========");
		
		int count = 0;
		for(Map.Entry<String, Integer> i:al) {
			System.out.printf("%-15s %-15s%n", i.getKey(), i.getValue());// print key and value
			count++;
		
			if(count == 20) break; // limit output to top 20
		}
		sc.close(); //close scanner
	}
}



