package com.ranorex.jenkinsranorexplugin.util;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CmdArgumentTest {
    //Constuctors
    @Test
    void Constructor_ValidArgumentFlagWithoutSlash_CorrectFlag() {
        CmdArgument cmdArg = new CmdArgument("banana");
        assertEquals("banana", cmdArg.getArgumentFlag());
    }

    @Test
    void Constructor_ValidArgumentFlagWithSlash_CorrectFlag() {
        CmdArgument cmdArg = new CmdArgument("/banana");
        assertEquals("banana", cmdArg.getArgumentFlag());
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameWithSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("/banana:test");
        assertEquals("banana", cmdArg.getArgumentFlag());
        assertEquals("test", cmdArg.getArgumentName());
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameWithoutSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("banana:test");
        assertEquals("banana", cmdArg.getArgumentFlag());
        assertEquals("test", cmdArg.getArgumentName());
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameWithSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("/param:test");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' is not allowed", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameWithoutSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("param:test");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' is not allowed", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagWithoutSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("param");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' is not allowed", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagWithSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("/param");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' is not allowed", e.getMessage());
        }
    }

    ////
    @Test
    void Constructor_ValidArgumentFlagAndNameAndValueWithSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("/banana:test=value");
        assertEquals("banana", cmdArg.getArgumentFlag());
        assertEquals("test", cmdArg.getArgumentName());
        assertEquals("value", cmdArg.getArgumentValue());
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameAndValueWithoutSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("banana:test=value");
        assertEquals("banana", cmdArg.getArgumentFlag());
        assertEquals("test", cmdArg.getArgumentName());
        assertEquals("value", cmdArg.getArgumentValue());
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameAndValueWithSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("/param:test=value");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' is not allowed", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameAndValueWithoutSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("param:test=value");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' is not allowed", e.getMessage());
        }
    }
    ////

    @Test
    void isValid_InvalidArgumentParam_False() {
        boolean result = CmdArgument.isValid("param");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentPa_False() {
        boolean result = CmdArgument.isValid("pa");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentListConfigParams_False() {
        boolean result = CmdArgument.isValid("listconfigparams");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentLcp_False() {
        boolean result = CmdArgument.isValid("lcp");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentReportFile_False() {
        boolean result = CmdArgument.isValid("reportfile");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentRf_False() {
        boolean result = CmdArgument.isValid("rf");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentZipReport_False() {
        boolean result = CmdArgument.isValid("zipreport");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentZr_False() {
        boolean result = CmdArgument.isValid("zr");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentJunit_False() {
        boolean result = CmdArgument.isValid("junit");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentJu_False() {
        boolean result = CmdArgument.isValid("ju");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentListGlobalParams_False() {
        boolean result = CmdArgument.isValid("listglobalparams");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentLp_False() {
        boolean result = CmdArgument.isValid("lp");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentListTestCaseParams_False() {
        boolean result = CmdArgument.isValid("listtestcaseparams");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentLtcpa_False() {
        boolean result = CmdArgument.isValid("ltcpa");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentRunConfig_False() {
        boolean result = CmdArgument.isValid("runconfig");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentRc_False() {
        boolean result = CmdArgument.isValid("rc");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentTestRail_False() {
        boolean result = CmdArgument.isValid("testrail");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentTrUser_False() {
        boolean result = CmdArgument.isValid("truser");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentTrPass_False() {
        boolean result = CmdArgument.isValid("trpass");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentTrRunId_False() {
        boolean result = CmdArgument.isValid("trrunid");
        assertFalse(result);
    }

    @Test
    void isValid_InvalidArgumentTrRunName_False() {
        boolean result = CmdArgument.isValid("trrunname");
        assertFalse(result);
    }

    @Test
    void isValid_ValidArgumentEp_True() {
        boolean result = CmdArgument.isValid("ep");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentEndpointconfig_True() {
        boolean result = CmdArgument.isValid("endpointconfig");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentEpc_True() {
        boolean result = CmdArgument.isValid("epc");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentEndpointconfigfilepath_True() {
        boolean result = CmdArgument.isValid("endpointconfigfilepath");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentEpcfp_True() {
        boolean result = CmdArgument.isValid("epcfp");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentReportLevel_True() {
        boolean result = CmdArgument.isValid("reportlevel");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentRl_True() {
        boolean result = CmdArgument.isValid("rl");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTestsuite_True() {
        boolean result = CmdArgument.isValid("testsuite");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTs_True() {
        boolean result = CmdArgument.isValid("ts");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentModule_True() {
        boolean result = CmdArgument.isValid("module");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentMo_True() {
        boolean result = CmdArgument.isValid("mo");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTestcaseparam_True() {
        boolean result = CmdArgument.isValid("testcaseparam");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTestcontainerparam_True() {
        boolean result = CmdArgument.isValid("testcontainerparam");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTcpa_True() {
        boolean result = CmdArgument.isValid("tcpa");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentRunlabel_True() {
        boolean result = CmdArgument.isValid("runlabel");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentRul_True() {
        boolean result = CmdArgument.isValid("rul");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTestcasedatarange_True() {
        boolean result = CmdArgument.isValid("testcasedatarange");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTestcontainerdatarange_True() {
        boolean result = CmdArgument.isValid("testcontainerdatarange");
        assertTrue(result);
    }

    @Test
    void isValid_ValidArgumentTcdr_True() {
        boolean result = CmdArgument.isValid("tcdr");
        assertTrue(result);
    }

    ///////////trySplitArgumentString
    @Test
    void splitArgumentString_Empty_ThrowsInvalidParameterException() {
        try {
            CmdArgument.trySplitArgumentString("");
        } catch (InvalidParameterException e) {
            assertEquals("Can't split empty string", e.getMessage());
        }
    }

    @Test
    void splitArgumentString_NULL_ThrowsInvalidParameterException() {
        try {
            CmdArgument.trySplitArgumentString(null);
        } catch (InvalidParameterException e) {
            assertEquals("Can't split empty string", e.getMessage());
        }
    }

    @Test
    void splitArgumentString_IncorrectArgument_ThrowsInvalidParameterException() {
        try {
            CmdArgument.trySplitArgumentString("NotValid=Value");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'NotValid=Value' is not valid", e.getMessage());
        }
    }

    @Test
    void splitArgumentString_CorrectFlagWithName_SplitArguments() {
        String[] splitArgs = CmdArgument.trySplitArgumentString("/rul:MyRunLabel");
        assertEquals(2, splitArgs.length);
        assertEquals("rul", splitArgs[0]);
        assertEquals("MyRunLabel", splitArgs[1]);
    }

    @Test
    void splitArgumentString_CorrectFlagWithNameAndValue_SplitArguments() {
        String[] splitArgs = CmdArgument.trySplitArgumentString("/testcaseparam:MyParam=MyValue");
        assertEquals(3, splitArgs.length);
        assertEquals("testcaseparam", splitArgs[0]);
        assertEquals("MyParam", splitArgs[1]);
        assertEquals("MyValue", splitArgs[2]);
    }

    @Test
    void splitArgumentString_CorrectFlagWithNameAndValue_Exception() {
        try {
            CmdArgument.trySplitArgumentString("/testcaseparam:MyParam=");
        } catch (Exception e) {
            assertEquals("Value must not be null or empty", e.getMessage());
        }
    }

    ///////////trim
    @Test
    void trim_ValidArgument_TrimmedArgument() {
        CmdArgument cmdarg = new CmdArgument("/tcdr:testcase=25");
    }


}