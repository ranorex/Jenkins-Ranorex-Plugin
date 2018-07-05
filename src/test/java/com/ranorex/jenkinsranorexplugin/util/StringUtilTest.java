/*
 * The MIT License
 *
 * Copyright 2018 mstoegerer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ranorex.jenkinsranorexplugin.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author mstoegerer
 */
public class StringUtilTest {
    private final String _textWithQuotes = "\"This is a sample text\"";
    private final String _textWithoutQuotes = "This is a sample text";
    private String _textWithBackslash = "This is a sample text\\";
    private String _textWithoutBackslash = "This is a sample text";

    public StringUtilTest() {
    }

    /**
     * Test appendQuote method
     *
     * @Input Text without quotes
     * @ExpectedResult Text with quotes
     */
    @Test
    public void AppendQuote_TextWithoutQuotes_TextWithQuotes() {
        String actualResult = StringUtil.appendQuote(_textWithoutQuotes);
        assertEquals(_textWithQuotes, actualResult);
    }

    /**
     * Test appendQuote method
     *
     * @Input Text with quotes
     * @ExpectedResult Text with quotes
     */
    @Test
    public void AppendQuote_TextWithQuotes_TextWithQuotes() {
        String actualResult = StringUtil.appendQuote(_textWithQuotes);
        assertEquals(_textWithQuotes, actualResult);
    }

    /**
     * Test isNullOrSpace method
     *
     * @Input NULL
     * @ExpectedResult true
     */
    @Test
    public void IsNullOrSpace_NULL_True() {
        String input = null;
        boolean actualResult = StringUtil.isNullOrSpace(input);
        assertEquals(true, actualResult);
    }

    /**
     * Test isNullOrSpace method
     *
     * @Input SPACE
     * @ExpectedResult true
     */
    @Test
    public void IsNullOrSpace_SPACE_True() {

        String input = " ";
        boolean actualResult = StringUtil.isNullOrSpace(input);
        assertEquals(true, actualResult);
    }

    /**
     * Test isNullOrSpace method
     *
     * @Input EMPTY
     * @ExpectedResult true
     */
    @Test
    public void IsNullOrSpace_EMPTY_True() {
        String input = "";
        boolean actualResult = StringUtil.isNullOrSpace(input);
        assertEquals(true, actualResult);
    }

    /**
     * Test appendBackslash method
     *
     * @Input Text without backslash
     * @ExpectedResult Text with backslash
     */
    @Test
    public void AppendBackslash_TextWithoutBackslash_TextWithBackslash() {
        String expectedResult = _textWithBackslash;
        String actualResult = StringUtil.appendBackslash(_textWithoutBackslash);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test appendBackslash method
     *
     * @Input Text with backslash
     * @ExpectedResult Text with backslash
     */
    @Test
    public void AppendBackslash_TextWithBackslash_TextWithBackslash() {
        String actualResult = StringUtil.appendBackslash(_textWithBackslash);
        assertEquals(_textWithBackslash, actualResult);
    }

    /**
     * Test splitPath method
     *
     * @Input Absolute Path with backslashes
     * @ExpectedResult Array of strings (directories)
     */
    @Test
    public void SplitPath_PathWithBackslashes_ArrayOfDirectories() {
        String inputPath = "C:\\Temp\\Test Directory\\Banana\\";
        String[] splittedPath = {"C:", "Temp", "Test Directory", "Banana"};
        String[] actualResult = StringUtil.splitPath(inputPath);
        assertArrayEquals(splittedPath, actualResult);
    }

    /**
     * Test splitPath method
     *
     * @Input Absolute Path with slashes
     * @ExpectedResult Array of strings (directories)
     */
    @Test
    public void SplitPath_PathSlashes_ArrayOfDirectories() {
        String inputPath = "C:/Temp/Test Directory/Banana/";
        String[] splittedPath = {"C:", "Temp", "Test Directory", "Banana"};
        String[] actualResult = StringUtil.splitPath(inputPath);
        assertArrayEquals(splittedPath, actualResult);
    }
}
