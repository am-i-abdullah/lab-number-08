/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

    // Testing strategy:
    // - poem():
    // - Number of bridge words: 0, 1, n
    // - Multiple bridges weight: same, different
    // - Multiple lines in the poem

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // Make sure assertions are enabled with VM argument: -ea
    }

    // Tests

    // Covers multiple lines in the poem
    @Test
    public void testMultipleLines() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/Where-no-man-has-gone-before.txt"));
        String poem = poet.poem("Seek to explore new and exciting synergies!");

        assertEquals("Test multiple lines", "Seek to explore strange new life and exciting synergies!", poem);
    }

    // Covers no bridge words
    @Test
    public void testNoBridge() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/Where-no-man-has-gone-before.txt"));
        String poem = poet.poem("no bridge");

        assertEquals("Expected no bridge words", "no bridge", poem);
    }

    // Covers one bridge word
    @Test
    public void testOneBridge() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/one-bridge.txt"));
        String poem = poet.poem("one bridge");

        assertEquals("Expected one bridge word", "one simple bridge", poem);
    }

    // Covers multiple bridge words with same weight and case-insensitive matching
    @Test
    public void testMultipleBridgesSameWeightCaseInsensitive() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/multiple-bridges-same-weight-case-insensitive.txt"));
        String poem = poet.poem("MULTIPLE bridges");

        assertTrue("Expected multiple bridge words with same weight",
                poem.equals("MULTIPLE same bridges") || poem.equals("MULTIPLE weight bridges"));
    }

    // Covers multiple bridge words with different weights
    @Test
    public void testMultipleBridgesDifferentWeight() throws IOException {
        GraphPoet poet = new GraphPoet(new File("test/poet/multiple-bridges-different-weight.txt"));
        String poem = poet.poem("multiple bridges");

        assertEquals("Expected multiple bridge words with different weights", "multiple same bridges", poem);
    }
}
