package com.cry.it.make.jacoco;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PalindromeTest {
    @Test
    public void whenEmptyString_thenAccept() {
        Palindrome palindromeTester = new Palindrome();
        assertTrue(palindromeTester.isPalindrome(""));
    }

}