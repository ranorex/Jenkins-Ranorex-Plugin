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

import static org.junit.Assert.assertEquals;

/**
 * @author mstoegerer
 */
public class StringUtilTest {

    public StringUtilTest() {
    }

    /**
     * Test of appendQuote method, of class StringUtil.
     */
    @Test
    public void testAppendQuote() {
        System.out.println("appendQuote");
        String value = "test";
        String expResult = "\"test\"";
        String result = StringUtil.appendQuote(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of appendQuote method, of class StringUtil.
     */
    @Test
    public void testAppendQuote2() {
        System.out.println("appendQuote2");
        String value = "\"test2\"";
        String expResult = "\"test2\"";
        String result = StringUtil.appendQuote(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNullOrSpace method, of class StringUtil.
     */
    @Test
    public void testIsNullOrSpace_NULL() {
        System.out.println("testIsNullOrSpace_NULL");
        String value = "";
        boolean expResult = true;
        boolean result = StringUtil.isNullOrSpace(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of isNullOrSpace method, of class StringUtil.
     */
    @Test
    public void testIsNullOrSpace_space() {
        System.out.println("testIsNullOrSpace_space");
        String value = " ";
        boolean expResult = true;
        boolean result = StringUtil.isNullOrSpace(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of appendBackslash method, of class StringUtil.
     */
    @Test
    public void testAppendBackslash() {
        System.out.println("appendBackslash");
        String value = "TestString";
        String expResult = "TestString\\";
        String result = StringUtil.appendBackslash(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of appendBackslash method, of class StringUtil.
     */
    @Test
    public void testAppendBackslash2() {
        System.out.println("appendBackslash");
        String value = "TestString\\";
        String expResult = "TestString\\";
        String result = StringUtil.appendBackslash(value);
        assertEquals(expResult, result);
    }
}
