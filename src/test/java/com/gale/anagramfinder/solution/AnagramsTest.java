package com.gale.anagramfinder.solution;

import static com.gale.anagramfinder.Anagrams.find;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AnagramsTest {
	
	private List<List<String>> anagramList;

	private List<List<String>> createAnagramList(String... terms) {
		List<String> anagrams = Arrays.asList(terms);
		anagramList = new ArrayList<List<String>>();
		anagramList.add(anagrams);
		return anagramList;
	}
	
	@Before
	public void setUp() {
		anagramList = new ArrayList<List<String>>();
	}
	
	@Test
	public void find_returnsNoAnagrams() throws Exception {
		assertThat(find("a", "b"), is(anagramList));
	}
	
	@Test
	public void find_returnsAnagrams() throws Exception {
		assertThat(find("god", "dog"), is(createAnagramList("god", "dog")));
	}
	
	@Test
	public void find_handlesDoubleLettersCorrectly() throws Exception {
		assertThat(find("little", "tiddle"), is(anagramList));
	}
	
	@Test
	public void find_handlesLengthesCorrectly() throws Exception {
		assertThat(find("paddle", "daple"), is(anagramList));
	}
	
	@Test
	public void find_handlesSimilarTerms() throws Exception {
		assertThat(find("amortizements", "isomerization"), is(anagramList));
	}
	
	@Test
	public void find_returnsSingleAnagram() throws Exception {
		createAnagramList("dog", "god");
		assertThat(Anagrams.find("dog", "god"), is(anagramList));
	}

	@Test
	public void find_doesNotContainListsOfSingleTerms() throws Exception {
		assertThat(Anagrams.find("dog", "god", "anything"), is(createAnagramList("dog", "god")));
	}
	
	@Test
	public void find_doesNotReturnListsOfWordsThatAreNotAnagrams() throws Exception {
		assertThat(Anagrams.find("amortizements", "isomerization", "memorizations"), is(anagramList));
	}
}
