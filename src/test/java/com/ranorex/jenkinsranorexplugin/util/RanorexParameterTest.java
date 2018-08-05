package com.ranorex.jenkinsranorexplugin.util;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class RanorexParameterTest {
    @Test
    void isValid_IgnoredParam_False() {
        boolean result = RanorexParameter.isValidFlag("lcp");
        assertFalse(result);
    }

    @Test
    void istValid_WhiteListPa_True() {
        boolean result = RanorexParameter.isValidFlag("pa");
        assertTrue(result);
    }

    @Test
    void istValid_WhiteListParam_True() {
        boolean result = RanorexParameter.isValidFlag("param");
        assertTrue(result);
    }

    // Split param String
    @Test
    void splitParameterString_Emtpy_ThrowsInvalidParameterException() {
        try {
            RanorexParameter.trySplitParameterString("");
        } catch (InvalidParameterException e) {
            assertEquals("parameterString is null or empty", e.getMessage());
        }
    }

    @Test
    void splitParameterString_InvalidFlag_ThrowsInvalidParameterException() {
        try {
            RanorexParameter.trySplitParameterString("/banana:paramName=test");
        } catch (InvalidParameterException e) {
            assertEquals("Parameter flag is not valid", e.getMessage());
        }
    }

    @Test
    void splitParameterString_ValidStringWithPa_ValidParameter() {
        String[] splitParam = RanorexParameter.trySplitParameterString("/pa:paramName=test");
        assertEquals(3, splitParam.length);
        assertEquals("pa", splitParam[0]);
        assertEquals("paramName", splitParam[1]);
        assertEquals("test", splitParam[2]);
    }

    @Test
    void splitParameterString_ValidStringWithoutPa_ValidParameter() {
        String[] splitParam = RanorexParameter.trySplitParameterString("paramName=test");
        assertEquals(3, splitParam.length);
        assertEquals("pa", splitParam[0]);
        assertEquals("paramName", splitParam[1]);
        assertEquals("test", splitParam[2]);
    }

    @Test
    void splitParameterString_InvalidParameterStringWithPa_ThrowsInvalidParameterException() {
        try {
            String[] splitParam = RanorexParameter.trySplitParameterString("/pa:paramNametest");
        } catch (InvalidParameterException e) {
            assertEquals("Parameter is not valid", e.getMessage());
        }
    }

    @Test
    void splitParameterString_InvalidParameterStringWithouPa_ThrowsInvalidParameterException() {
        try {
            String[] splitParam = RanorexParameter.trySplitParameterString("paramNametest");
        } catch (InvalidParameterException e) {
            assertEquals("Parameter is not valid", e.getMessage());
        }
    }

    // Parsing Input String Constructor
    @Test
    void Constructor_ValidInputStringPa_ValidRanorexParameter() {
        RanorexParameter valid = new RanorexParameter("/pa:TestName=TestValue");
        assertEquals(valid.getParameterFlag(), "pa");
        assertEquals(valid.getParameterName(), "TestName");
        assertEquals(valid.getParameterValue(), "TestValue");
    }

    @Test
    void Constructor_ValidInputStringWithoutPa_ValidRanorexParameter() {
        RanorexParameter valid = new RanorexParameter("TestName=TestValue");
        assertEquals(valid.getParameterFlag(), "pa");
        assertEquals(valid.getParameterName(), "TestName");
        assertEquals(valid.getParameterValue(), "TestValue");
    }

    @Test
    void Constructor_ValidInputStringParam_ValidRanorexParameter() {
        RanorexParameter valid = new RanorexParameter("/param:TestName=TestValue");
        assertEquals(valid.getParameterFlag(), "param");
        assertEquals(valid.getParameterName(), "TestName");
        assertEquals(valid.getParameterValue(), "TestValue");
    }

    @Test
    void Constructor_ValidInputStringPaInvalidParameter_ThrowsInvalidParameterException() {
        try {
            RanorexParameter invalid = new RanorexParameter("/pa:TestNameTestValue");
        } catch (InvalidParameterException e) {
            assertEquals("Parameter is not valid", e.getMessage());
        }
    }

    @Test
    void extractFlag_InvalidInput_ThrowsInvalidParameterException() {
        try {
            RanorexParameter.tryExtractFlag("/banana");
        } catch (InvalidParameterException e) {
            assertEquals("Parameter '/banana' does not contain a separator!", e.getMessage());
        }
    }

    @Test
    void extractFlag_ValidInputWithSlash_ValidFlag() {
        String flag = RanorexParameter.tryExtractFlag("/param:Test=test");
        assertEquals("param", flag);
    }

    @Test
    void extractFlag_ValidInputWithoutSlash_ValidFlag() {
        String flag = RanorexParameter.tryExtractFlag("param:Test=test");
        assertEquals("param", flag);
    }

    @Test
    void isValid_ValidParam_True() {
        assertTrue(RanorexParameter.isValid("/param:Test name = value 1"));
    }

    @Test
    void isValid_ValidPa_True() {
        assertTrue(RanorexParameter.isValid("/pa:Test name = value 1"));
    }

    @Test
    void isValid_NameValuePair_True() {
        assertTrue(RanorexParameter.isValid("Test name = value 1"));
    }

    @Test
    void isValid_InvalidFlag_False() {
        assertFalse(RanorexParameter.isValid("/zr"));
    }

    @Test
    void isValid_IncompleteParam_False() {
        assertFalse(RanorexParameter.isValid("/param:test"));
    }

    @Test
    void isValidIncompleteParamWithEqual_False() {
        assertFalse(RanorexParameter.isValid("/param:test="));
    }

    @Test
    void isValidIncorrectFlagButCorrectNameValuePair_False() {
        assertFalse(RanorexParameter.isValid("/testArgument:TestName=TestValue"));
    }
}