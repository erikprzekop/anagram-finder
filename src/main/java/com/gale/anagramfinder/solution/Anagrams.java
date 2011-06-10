package com.gale.anagramfinder.solution;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrams {

	private static Map<String, List<String>> anagramMap;

	private static String createKey(String word1) {
		char[] word1Char = word1.toCharArray();
		Arrays.sort(word1Char);
		String sortedWord1 = new String(word1Char);
		return sortedWord1.toLowerCase();
	}

	public static List<List<String>> find(String... dictionary) {
		anagramMap = new HashMap<String, List<String>>();
		for (String term1 : dictionary) {
			String key = createKey(term1);
			List<String> anagrams = anagramMap.get(key);
			if (anagrams == null) {
				anagrams = new ArrayList<String>();
				anagramMap.put(key, anagrams);
			}
			anagrams.add(term1);
		}
		
		return removeDuplicateLists();
	}

	private static List<List<String>> removeDuplicateLists() {
		List<List<String>> finalList = new ArrayList<List<String>>();
		for (Entry<String, List<String>> entry : anagramMap.entrySet()) {
			List<String> list = entry.getValue();
			if (list.size() > 1) {
				finalList.add(list);
			}
		}
		return finalList;
	}
}
