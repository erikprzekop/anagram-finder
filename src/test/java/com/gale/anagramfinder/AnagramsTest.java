package com.gale.anagramfinder;

import static com.gale.anagramfinder.Anagrams.isAnagram;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class AnagramsTest {
	
	@Test
	public void isAnagram_returnsFalse() throws Exception {
		assertThat(isAnagram("a", "b"), is(false));
	}
	
	@Test
	public void isAnagram_returnsTrue() throws Exception {
		assertThat(isAnagram("god", "dog"), is(true));
	}
	
	@Test
	public void isAnagram_handlesDoubleLettersCorrectly() throws Exception {
		assertThat(isAnagram("little", "tiddle"), is(false));
	}
	
	@Test
	public void isAnagram_handlesLengthesCorrectly() throws Exception {
		assertThat(isAnagram("paddle", "daple"), is(false));
	}
	
	@Test
	public void isAnagram_handlesSimilarTerms() throws Exception {
		assertThat(isAnagram("amortizements", "isomerization"), is(false));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void findAnagrams_returnsSingleAnagram() throws Exception {
		List<String> anagram = (List<String>)Arrays.asList("dog", "god");
		assertThat(Anagrams.find("dog", "god"), is((List<List<String>>)Arrays.asList(anagram)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void findAnagrams_doesNotContainListsOfSingleTerms() throws Exception {
		List<String> anagram = (List<String>)Arrays.asList("dog", "god");
		assertThat(Anagrams.find("dog", "god", "anything"), is((List<List<String>>)Arrays.asList(anagram)));
	}
	
	@Ignore
	@Test
	public void findAnagrams_doesNotReturnListsOfWordsThatAreNotAnagrams() throws Exception {
		List<List<String>> anagramList = new ArrayList<List<String>>();
		assertThat(Anagrams.find("amortizements", "isomerization", "memorizations"), is(anagramList));
	}
}
