package com.ranorex.jenkinsranorexplugin.util;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class StringUtilTest {
    private final String _textWithQuotes = "\"This is a sample text\"";
    private final String _textWithBackslash = "This is a sample text\\";


    @Test
    public void AppendQuote_TextWithoutQuotes_TextWithQuotes() {
        String _textWithoutQuotes = "This is a sample text";
        String actualResult = StringUtil.appendQuote(_textWithoutQuotes);
        assertEquals(_textWithQuotes, actualResult);
    }


    @Test
    public void AppendQuote_TextWithQuotes_TextWithQuotes() {
        String actualResult = StringUtil.appendQuote(_textWithQuotes);
        assertEquals(_textWithQuotes, actualResult);
    }


    @Test
    public void IsNullOrSpace_NULL_True() {
        boolean actualResult = StringUtil.isNullOrSpace(null);
        assertTrue(actualResult);
    }


    @Test
    public void IsNullOrSpace_SPACE_True() {

        String input = " ";
        boolean actualResult = StringUtil.isNullOrSpace(input);
        assertTrue(actualResult);
    }


    @Test
    public void IsNullOrSpace_EMPTY_True() {
        String input = "";
        boolean actualResult = StringUtil.isNullOrSpace(input);
        assertTrue(actualResult);
    }


    @Test
    public void AppendBackslash_TextWithoutBackslash_TextWithBackslash() {
        String _textWithoutBackslash = "This is a sample text";
        String actualResult = StringUtil.appendBackslash(_textWithoutBackslash);
        assertEquals(_textWithBackslash, actualResult);
    }


    @Test
    public void AppendBackslash_TextWithBackslash_TextWithBackslash() {
        String actualResult = StringUtil.appendBackslash(_textWithBackslash);
        assertEquals(_textWithBackslash, actualResult);
    }


    @Test
    public void SplitPath_PathWithBackslashes_ArrayOfDirectories() {
        String inputPath = "C:\\Temp\\Test Directory\\Banana\\";
        String[] splitPath = {"C:", "Temp", "Test Directory", "Banana"};
        String[] actualResult = StringUtil.splitPath(inputPath);
        assertArrayEquals(splitPath, actualResult);
    }


    @Test
    public void SplitPath_PathSlashes_ArrayOfDirectories() {
        String inputPath = "C:/Temp/Test Directory/Banana/";
        String[] splitPath = {"C:", "Temp", "Test Directory", "Banana"};
        String[] actualResult = StringUtil.splitPath(inputPath);
        assertArrayEquals(splitPath, actualResult);
    }

    //Remove heading slash
    @Test
    public void RemoveHeadingSlash_Empty_ThrowsInvalidParameterException() {
        try {
            StringUtil.removeHeadingSlash("");
        } catch (InvalidParameterException e) {
            assertEquals("Argument is empty", e.getMessage());
        }
    }

    @Test
    public void RemoveHeadingSlash_NULL_ThrowsInvalidParameterException() {
        try {
            StringUtil.removeHeadingSlash(null);
        } catch (InvalidParameterException e) {
            assertEquals("Argument is empty", e.getMessage());
        }
    }

    @Test
    public void RemoveHeadingSlash_ValidInputWithSlash_ValidOutputWithoutSlash() {
        String result = StringUtil.removeHeadingSlash("/bananaRama");
        assertEquals("bananaRama", result);
    }

    @Test
    public void RemoveHeadingSlash_ValidInputWithoutSlash_ValidOutputWithoutSlash() {
        String result = StringUtil.removeHeadingSlash("bananaRama");
        assertEquals("bananaRama", result);
    }
}
