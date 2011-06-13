package com.gale.anagramfinder;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AnagramsPerformanceTest {

	private String[] dictionary;

	@Before
	public void setUp() throws IOException {
		InputStream file = this.getClass().getClassLoader().getResourceAsStream("com/gale/anagramfinder/dictionary.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		List<String> terms = new ArrayList<String>();
		String term = "";
		while((term = reader.readLine()) != null) {
			terms.add(term);
		}
		dictionary = (String[])terms.toArray(new String[0]);
	}
	
	@Test
	public void shouldBeFast() throws Exception {
		long startMillis = System.currentTimeMillis();
		List<List<String>> findAnagrams = Anagrams.find(dictionary);
		long endMillis = System.currentTimeMillis();
		long time = endMillis - startMillis;
		assertEquals(27, findAnagrams.size());
		assertTrue("Expected time to be: \n\tless than: 50\n\tbut was: " + time, time < 50);
	}
}
