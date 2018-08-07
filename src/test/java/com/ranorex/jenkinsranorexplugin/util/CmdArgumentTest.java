package com.ranorex.jenkinsranorexplugin.util;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class CmdArgumentTest {
    //Constuctors
    @Test
    void Constructor_EmptyString_ThrowsException() {
        try {
            CmdArgument empty = new CmdArgument("");
        } catch (IllegalArgumentException e) {
            assertEquals("Argument must be not null or empty!", e.getMessage());
        }
    }

    @Test
    void Constructor_Null_ThrowsException() {
        try {
            CmdArgument empty = new CmdArgument(null);

        } catch (IllegalArgumentException e) {
            assertEquals("Argument must be not null or empty!", e.getMessage());
        }
    }

    @Test
    void Constructor_ValidArgumentFlagWithoutSlash_CorrectFlag() {
        CmdArgument cmdArg = new CmdArgument("banana");
        assertEquals("banana", cmdArg.getFlag());
    }

    @Test
    void Constructor_ValidArgumentFlagWithSlash_CorrectFlag() {
        CmdArgument cmdArg = new CmdArgument("/banana");
        assertEquals("banana", cmdArg.getFlag());
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameWithSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("/banana:test");
        assertEquals("banana", cmdArg.getFlag());
        assertEquals("test", cmdArg.getName());
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameWithoutSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("banana:test");
        assertEquals("banana", cmdArg.getFlag());
        assertEquals("test", cmdArg.getName());
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameWithSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("/param:test");
        } catch (InvalidParameterException e) {
            assertEquals("Argument '/param:test' will be ignored", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameWithoutSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("param:test");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param:test' will be ignored", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagWithoutSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("param");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param' will be ignored", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagWithSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("/param");
        } catch (InvalidParameterException e) {
            assertEquals("Argument '/param' will be ignored", e.getMessage());
        }
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameAndValueWithSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("/banana:test=value");
        assertEquals("banana", cmdArg.getFlag());
        assertEquals("test", cmdArg.getName());
        assertEquals("value", cmdArg.getValue());
    }

    @Test
    void Constructor_ValidArgumentFlagAndNameAndValueWithoutSlash_CorrectFlagAndName() {
        CmdArgument cmdArg = new CmdArgument("banana:test=value");
        assertEquals("banana", cmdArg.getFlag());
        assertEquals("test", cmdArg.getName());
        assertEquals("value", cmdArg.getValue());
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameAndValueWithSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("/param:test=value");
        } catch (InvalidParameterException e) {
            assertEquals("Argument '/param:test=value' will be ignored", e.getMessage());
        }
    }

    @Test
    void Constructor_IgnoredArgumentFlagAndNameAndValueWithoutSlash_ThrowsException() {
        try {
            CmdArgument cmdArg = new CmdArgument("param:test=value");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'param:test=value' will be ignored", e.getMessage());
        }
    }

    ///isIgnored

    @Test
    void isIgnored_EmptyString_ThrowsIllegalArgumentException() {
        try {
            CmdArgument.isIgnored("");
        } catch (IllegalArgumentException e) {
            assertEquals("Argument must be not null or empty!", e.getMessage());
        }
    }

    @Test
    void isIgnored_NULL_ThrowsIllegalArgumentException() {
        try {
            CmdArgument.isIgnored(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Argument must be not null or empty!", e.getMessage());
        }
    }

    @Test
    void isIgnored_InvalidArgumentParam_True() {
        boolean result = CmdArgument.isIgnored("param");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentPa_True() {
        boolean result = CmdArgument.isIgnored("pa");
        assertTrue(result);
    }


    @Test
    void isIgnored_InvalidArgumentListConfigParams_True() {
        boolean result = CmdArgument.isIgnored("listconfigparams");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentLcp_True() {
        boolean result = CmdArgument.isIgnored("lcp");
        assertTrue(result);
    }

    @Test
    void isIgnored_param_true() {
        boolean result = CmdArgument.isIgnored("param");
        assertTrue(result);
    }

    @Test
    void isIgnored_pa_true() {
        boolean result = CmdArgument.isIgnored("pa");
        assertTrue(result);
    }

    @Test
    void isIgnored_banana_false() {
        boolean result = CmdArgument.isIgnored("banana");
        assertFalse(result);
    }

    @Test
    void isIgnored_ArgumentWithColon_false() {
        boolean result = CmdArgument.isIgnored("/banana:");
        assertFalse(result);
    }

    @Test
    void isIgnored_ArgumentWithColonAndName_false() {
        boolean result = CmdArgument.isIgnored("/banana:Name");
        assertFalse(result);
    }

    @Test
    void isIgnored_ArgumentWithColonAndNameAndEqual_false() {
        boolean result = CmdArgument.isIgnored("/banana:Name=");
        assertFalse(result);
    }

    @Test
    void isIgnored_ArgumentWithColonAndNameAndEqualAndValue_false() {
        boolean result = CmdArgument.isIgnored("/banana:Name=Value");
        assertFalse(result);
    }

    @Test
    void isIgnored_InvalidArgumentReportFile_True() {
        boolean result = CmdArgument.isIgnored("reportfile");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentRf_True() {
        boolean result = CmdArgument.isIgnored("rf");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentZipReport_True() {
        boolean result = CmdArgument.isIgnored("zipreport");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentZr_True() {
        boolean result = CmdArgument.isIgnored("zr");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentJunit_True() {
        boolean result = CmdArgument.isIgnored("junit");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentJu_True() {
        boolean result = CmdArgument.isIgnored("ju");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentListGlobalParams_True() {
        boolean result = CmdArgument.isIgnored("listglobalparams");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentLp_True() {
        boolean result = CmdArgument.isIgnored("lp");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentListTestCaseParams_True() {
        boolean result = CmdArgument.isIgnored("listtestcaseparams");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentLtcpa_True() {
        boolean result = CmdArgument.isIgnored("ltcpa");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentRunConfig_True() {
        boolean result = CmdArgument.isIgnored("runconfig");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentRc_True() {
        boolean result = CmdArgument.isIgnored("rc");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentTestRail_True() {
        boolean result = CmdArgument.isIgnored("testrail");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentTrUser_True() {
        boolean result = CmdArgument.isIgnored("truser");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentTrPass_True() {
        boolean result = CmdArgument.isIgnored("trpass");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentTrRunId_True() {
        boolean result = CmdArgument.isIgnored("trrunid");
        assertTrue(result);
    }

    @Test
    void isIgnored_InvalidArgumentTrRunName_True() {
        boolean result = CmdArgument.isIgnored("trrunname");
        assertTrue(result);
    }

    @Test
    void isIgnored_Ep_False() {
        boolean result = CmdArgument.isIgnored("ep");
        assertFalse(result);
    }

    @Test
    void isIgnored_Endpointconfig_false() {
        boolean result = CmdArgument.isIgnored("endpointconfig");
        assertFalse(result);
    }

    @Test
    void isIgnored_Epc_False() {
        boolean result = CmdArgument.isIgnored("epc");
        assertFalse(result);
    }

    @Test
    void isIgnored_Endpointconfigfilepath_False() {
        boolean result = CmdArgument.isIgnored("endpointconfigfilepath");
        assertFalse(result);
    }

    @Test
    void isIgnored_Epcfp_False() {
        boolean result = CmdArgument.isIgnored("epcfp");
        assertFalse(result);
    }

    @Test
    void isIgnored_ReportLevel_False() {
        boolean result = CmdArgument.isIgnored("reportlevel");
        assertFalse(result);
    }

    @Test
    void isIgnored_Rl_False() {
        boolean result = CmdArgument.isIgnored("rl");
        assertFalse(result);
    }

    @Test
    void isIgnored_Testsuite_False() {
        boolean result = CmdArgument.isIgnored("testsuite");
        assertFalse(result);
    }

    @Test
    void isIgnored_Ts_False() {
        boolean result = CmdArgument.isIgnored("ts");
        assertFalse(result);
    }

    @Test
    void isIgnored_Module_False() {
        boolean result = CmdArgument.isIgnored("module");
        assertFalse(result);
    }

    @Test
    void isIgnored_Mo_False() {
        boolean result = CmdArgument.isIgnored("mo");
        assertFalse(result);
    }

    @Test
    void isIgnored_Testcaseparam_False() {
        boolean result = CmdArgument.isIgnored("testcaseparam");
        assertFalse(result);
    }

    @Test
    void isIgnored_Testcontainerparam_False() {
        boolean result = CmdArgument.isIgnored("testcontainerparam");
        assertFalse(result);
    }

    @Test
    void isIgnored_Tcpa_False() {
        boolean result = CmdArgument.isIgnored("tcpa");
        assertFalse(result);
    }

    @Test
    void isIgnored_Runlabel_False() {
        boolean result = CmdArgument.isIgnored("runlabel");
        assertFalse(result);
    }

    @Test
    void isIgnored_Rul_False() {
        boolean result = CmdArgument.isIgnored("rul");
        assertFalse(result);
    }

    @Test
    void isIgnored_Testcasedatarange_False() {
        boolean result = CmdArgument.isIgnored("testcasedatarange");
        assertFalse(result);
    }

    @Test
    void isIgnored_Testcontainerdatarange_False() {
        boolean result = CmdArgument.isIgnored("testcontainerdatarange");
        assertFalse(result);
    }

    @Test
    void isIgnored_Tcdr_False() {
        boolean result = CmdArgument.isIgnored("tcdr");
        assertFalse(result);
    }

    ///////////trySplitArgument
    @Test
    void splitArgumentString_Empty_ThrowsInvalidParameterException() {
        try {
            CmdArgument.trySplitArgument("");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot split empty string", e.getMessage());
        }
    }

    @Test
    void splitArgumentString_NULL_ThrowsInvalidParameterException() {
        try {
            CmdArgument.trySplitArgument(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot split empty string", e.getMessage());
        }
    }

    @Test
    void splitArgumentString_IncorrectArgument_ThrowsInvalidParameterException() {
        try {
            CmdArgument.trySplitArgument("NotValid=Value");
        } catch (InvalidParameterException e) {
            assertEquals("Argument 'NotValid=Value' is not valid", e.getMessage());
        }
    }

    @Test
    void splitArgumentString_CorrectFlagWithName_SplitArguments() {
        String[] splitArgs = CmdArgument.trySplitArgument("/rul:MyRunLabel");
        assertEquals(2, splitArgs.length);
        assertEquals("rul", splitArgs[0]);
        assertEquals("MyRunLabel", splitArgs[1]);
    }

    @Test
    void splitArgumentString_CorrectFlagWithNameAndValue_SplitArguments() {
        String[] splitArgs = CmdArgument.trySplitArgument("/testcaseparam:MyParam=MyValue");
        assertEquals(3, splitArgs.length);
        assertEquals("testcaseparam", splitArgs[0]);
        assertEquals("MyParam", splitArgs[1]);
        assertEquals("MyValue", splitArgs[2]);
    }

    @Test
    void splitArgumentString_CorrectFlagWithNameAndValue_Exception() {
        try {
            CmdArgument.trySplitArgument("/testcaseparam:MyParam=");
        } catch (Exception e) {
            assertEquals("Value must not be null or empty", e.getMessage());
        }
    }

    ///////////trim
    @Test
    void trim_ValidArgument_TrimmedArgument() {
        CmdArgument cmdarg = new CmdArgument("/tcdr:testcase=25");
    }


    ////TryExtractFlag

    @Test
    void tryExtractFlag_EmptyString_ThrowsIllegalArgumentException() {
        try {
            CmdArgument.tryExtractFlag("");
        } catch (IllegalArgumentException e) {
            assertEquals("Argument must not be null or empty", e.getMessage());
        }
    }

    @Test
    void tryExtractFlag_NULL_ThrowsIllegalArgumentException() {
        try {
            CmdArgument.tryExtractFlag(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Argument must not be null or empty", e.getMessage());
        }
    }

    @Test
    void tryExtractFlag_ArgumentWithSlashAndNoColon_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("/ValidFlag");
        assertEquals("ValidFlag", flag);
    }

    @Test
    void tryExtractFlag_ArgumentWithNoSlashAndNoColon_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("ValidFlag");
        assertEquals("ValidFlag", flag);
    }

    @Test
    void tryExtractFlag_ArgumentWithSlashAndColon_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("/ValidFlag:");
        assertEquals("ValidFlag", flag);
    }

    @Test
    void tryExtractFlag_ArgumentWithNoSlashAndColon_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("ValidFlag:");
        assertEquals("ValidFlag", flag);
    }

    @Test
    void tryExtractFlag_ArgumentWithSlashAndColonAndNoEqual_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("/ValidFlag:Test");
        assertEquals("ValidFlag", flag);

    }

    @Test
    void tryExtractFlag_ArgumentWithNoSlashAndColonAndEqual_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("/ValidFlag:Test=");
        assertEquals("ValidFlag", flag);
    }

    @Test
    void tryExtractFlag_ArgumentWithNoSlashAndColonAndEqualAndValue_ValidFlag() {
        String flag = CmdArgument.tryExtractFlag("/ValidFlag:Test=Value");
        assertEquals("ValidFlag", flag);
    }
}