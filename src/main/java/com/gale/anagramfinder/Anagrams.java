package com.gale.anagramfinder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Anagrams {

	public static boolean isAnagram(String word1, String word2) {
		if (!areAllCharactersInBothWords(word1, word2)) return false;
		if (!areAllCharactersInBothWords(word2, word1)) return false;
		
		if (word1.length() != word2.length()) return false;

		Map<String, Integer> countMap = getCountMapForWord(word1);
		Map<String, Integer> countMap2 = getCountMapForWord(word2);
		for (Entry<String, Integer> map1Entry : countMap.entrySet()) {
			Integer count = countMap2.get(map1Entry.getKey());
			if (!count.equals(map1Entry.getValue())) return false;
		}
		
		return true;
	}

	private static Map<String, Integer> getCountMapForWord(String word) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for (char word1char : word.toCharArray()) {
			Integer charCount = countMap.get(String.valueOf(word1char));
			if (charCount == null) {
				charCount = Integer.valueOf(0);
			}
			countMap.put(String.valueOf(word1char), ++charCount);
		}
		return countMap;
	}

	private static boolean areAllCharactersInBothWords(String testWord, String loopWord) {
		for (char loopWordChar : loopWord.toCharArray()) {
			if (testWord.indexOf(loopWordChar) == -1) {
				return false;
			}
		}
		return true;
	}

	public static List<List<String>> find(String... dictionary) {
		List<List<String>> anagramsList = new ArrayList<List<String>>();
		String[] dictionary2 = dictionary.clone();
		for (String term1 : dictionary) {
			List<String> anagrams = new ArrayList<String>();
			for (String term2 : dictionary2) {
				if (isAnagram(term1, term2)) {
					anagrams.add(term2);
				}
			}
			anagramsList.add(anagrams);
		}
		
		return removeDuplicateLists(anagramsList);
	}

	private static List<List<String>> removeDuplicateLists(
			List<List<String>> anagramsList) {
		Set<String> termSet = new HashSet<String>();
		List<List<String>> finalList = new ArrayList<List<String>>();
		for (List<String> list : anagramsList) {
			if (termSet.add(list.get(0)) && list.size() > 1) {
				finalList.add(list);
			}
		}
		return finalList;
	}
}
