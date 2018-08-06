package com.ranorex.jenkinsranorexplugin;

import com.ranorex.jenkinsranorexplugin.util.CmdArgument;
import com.ranorex.jenkinsranorexplugin.util.FileUtil;
import com.ranorex.jenkinsranorexplugin.util.RanorexParameter;
import com.ranorex.jenkinsranorexplugin.util.StringUtil;
import hudson.*;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.ArgumentListBuilder;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.io.PrintStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class RanorexRunnerBuilder extends Builder {

    private static final String ZIPPED_REPORT_EXTENSION = ".rxzlog";
    private static final String ARGUMENT_SEPARATOR = "\t\r\n;";
    private static PrintStream LOGGER;
    /*
     * Builder GUI Fields
     */
    private final String rxTestSuiteFilePath;
    private final String rxRunConfiguration;
    private final String rxReportDirectory;
    private final String rxReportFile;
    private final String rxReportExtension;
    private final Boolean rxJUnitReport;
    private final Boolean rxZippedReport;
    private final String rxZippedReportDirectory;
    private final String rxZippedReportFile;
    private final String rxGlobalParameter;
    private final String cmdLineArgs;
    private final Boolean rxTestRail;
    private final String rxTestRailUser;
    private final String rxTestRailPassword;
    private final String rxTestRailRID;
    private final String rxTestRailRunName;

    /*
     * Other Variables
     */
    private String rxExecuteableFile;
    private String WorkSpace;
    private String usedRxReportDirectory;
    private String usedRxReportFile;
    private String usedRxZippedReportDirectory;
    private String usedRxZippedReportFile;
    private ArgumentListBuilder jArguments;

    /**
     * When this builder is created in the project configuration step, the
     * builder object will be created from the strings below
     *
     * @param rxTestSuiteFilePath     The name/location of the Ranorex Test Suite / Ranorex Test Exe File
     * @param rxRunConfiguration      The Ranorex Run configuration which will be executed
     * @param rxReportDirectory       The directory where the Ranorex Report should be saved
     * @param rxReportFile            The name of the Ranorex Report
     * @param rxReportExtension       The extension of your Ranorex Report
     * @param rxJUnitReport           If true, a JUnit compatible Report will be saved
     * @param rxZippedReport          If true, the report will also be saved as RXZLOG
     * @param rxZippedReportDirectory The directory where the Ranorex Zipped Report should be saved
     * @param rxZippedReportFile      The name of the zipped Ranorex Report
     * @param rxGlobalParameter       Global test suite parameters
     * @param cmdLineArgs             Additional CMD line arguments
     */
    @DataBoundConstructor

    public RanorexRunnerBuilder(String rxTestSuiteFilePath, String rxRunConfiguration, String rxReportDirectory, String rxReportFile, String rxReportExtension, Boolean rxJUnitReport, Boolean rxZippedReport, String rxZippedReportDirectory, String rxZippedReportFile, Boolean rxTestRail, String rxTestRailUser, String rxTestRailPassword, String rxTestRailRID, String rxTestRailRunName, String rxGlobalParameter, String cmdLineArgs) {
        this.rxTestSuiteFilePath = rxTestSuiteFilePath;
        this.rxRunConfiguration = rxRunConfiguration;
        this.rxReportDirectory = rxReportDirectory;
        this.rxReportFile = rxReportFile;
        this.rxReportExtension = rxReportExtension;
        this.rxJUnitReport = rxJUnitReport;
        this.rxZippedReport = rxZippedReport;
        this.rxZippedReportDirectory = rxZippedReportDirectory;
        this.rxZippedReportFile = rxZippedReportFile;
        this.rxTestRail = rxTestRail;
        this.rxTestRailUser = rxTestRailUser;
        this.rxTestRailPassword = rxTestRailPassword;
        this.rxTestRailRID = rxTestRailRID;
        this.rxTestRailRunName = rxTestRailRunName;
        this.rxGlobalParameter = rxGlobalParameter;
        this.cmdLineArgs = cmdLineArgs;

    }

    public String getRxTestSuiteFilePath() {
        return this.rxTestSuiteFilePath;
    }

    public String getRxRunConfiguration() {
        return this.rxRunConfiguration;
    }

    public String getRxReportDirectory() {
        return this.rxReportDirectory;
    }

    public String getRxReportFile() {
        return this.rxReportFile;
    }

    public String getRxReportExtension() {
        return this.rxReportExtension;
    }

    public Boolean getrxJUnitReport() {
        return this.rxJUnitReport;
    }

    public Boolean getRxZippedReport() {
        return this.rxZippedReport;
    }

    public String getRxZippedReportDirectory() {
        return this.rxZippedReportDirectory;
    }

    public String getRxZippedReportFile() {
        return this.rxZippedReportFile;
    }

    public Boolean getRxTestRail() {
        return this.rxTestRail;
    }

    public String getRxTestRailUser() {
        return this.rxTestRailUser;
    }

    public String getRxTestRailPassword() {
        return this.rxTestRailPassword;
    }

    public String getRxTestRailRID() {
        return this.rxTestRailRID;
    }

    public String getRxTestRailRunName() {
        return this.rxTestRailRunName;
    }

    public String getRxGlobalParameter() {
        return this.rxGlobalParameter;
    }

    public String getCmdLineArgs() {
        return this.cmdLineArgs;
    }

    // public String getRxExecuteableFile()
    // {
    // return this.rxExecuteableFile;
    // }


    /**
     * Runs the step over the given build and reports the progress to the
     * listener
     *
     * @param build
     * @param launcher Starts a process
     * @param listener Receives events that happen during a build
     * @return Receives events that happen during a build
     * @throws IOException          - If the build is interrupted by the user (in an
     *                              attempt to abort the build.) Normally the BuildStep implementations may
     *                              simply forward the exception it got from its lower-level functions.
     * @throws InterruptedException - If the implementation wants to abort the
     *                              processing when an IOException happens, it can simply propagate the
     *                              exception to the caller. This will cause the build to fail, with the
     *                              default error message. Implementations are encouraged to catch
     *                              IOException on its own to provide a better error message, if it can do
     *                              so, so that users have better understanding on why it failed.
     */
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
        jArguments = new ArgumentListBuilder("cmd.exe", "/C");
        WorkSpace = FileUtil.getRanorexWorkingDirectory(build.getWorkspace(), rxTestSuiteFilePath).getRemote();
        WorkSpace = StringUtil.appendBackslash(WorkSpace);
        LOGGER = listener.getLogger();
        EnvVars env = build.getEnvironment(listener);
        boolean r = false;

        if (! StringUtil.isNullOrSpace(rxTestSuiteFilePath)) {
            rxExecuteableFile = FileUtil.getExecutableFromTestSuite(rxTestSuiteFilePath);
            jArguments.add(rxExecuteableFile);
            // Ranorex Run Configuration
            if (! StringUtil.isNullOrSpace(rxRunConfiguration)) {
                jArguments.add("/runconfig:" + rxRunConfiguration);
            }

            // Ranorex Reportdirectory
            if (! StringUtil.isNullOrSpace(rxReportDirectory)) {
                LOGGER.println("Reportpath to merge. Base: " + WorkSpace + " Relative: " + rxReportDirectory);
                usedRxReportDirectory = FileUtil.getAbsoluteReportDirectory(WorkSpace, rxReportDirectory);
                LOGGER.println("Merged path: " + usedRxReportDirectory);
            } else {
                usedRxReportDirectory = WorkSpace;
            }
            usedRxReportDirectory = StringUtil.appendBackslash(usedRxReportDirectory);

            // ReportFilename
            if (! StringUtil.isNullOrSpace(rxReportFile)) {
                if (! FileUtil.isAbsolutePath(rxReportFile)) {
                    usedRxReportFile = FileUtil.removeFileExtension(rxReportFile);
                } else {
                    LOGGER.println("'" + rxReportFile + "' is not a valid Ranorex Report filename");
                    return false;
                }
            } else {
                usedRxReportFile = "%S_%Y%M%D_%T";
            }
            jArguments.add("/reportfile:" + usedRxReportDirectory + usedRxReportFile + "." + rxReportExtension);

            // JUnit compatible Report
            if (rxJUnitReport) {
                jArguments.add("/junit");
            }

            // Compressed copy of Ranorex report
            if (rxZippedReport) {
                jArguments.add("/zipreport");
                // Zipped Ranorex Reportdirectory
                if (! StringUtil.isNullOrSpace(rxZippedReportDirectory)) {
                    usedRxZippedReportDirectory = FileUtil.getAbsoluteReportDirectory(WorkSpace, rxZippedReportDirectory);
                } else {
                    usedRxZippedReportDirectory = WorkSpace;
                }
                usedRxZippedReportDirectory = StringUtil.appendBackslash(usedRxZippedReportDirectory);

                // Zipped Report File Name
                if (! StringUtil.isNullOrSpace(rxZippedReportFile)) {
                    if (! FileUtil.isAbsolutePath(rxZippedReportFile)) {
                        usedRxZippedReportFile = FileUtil.removeFileExtension(rxZippedReportFile);
                    } else {
                        LOGGER.println("'" + rxZippedReportFile + "' is not a valid Ranorex Report filename");
                        return false;
                    }
                } else {
                    usedRxZippedReportFile = usedRxReportFile;
                }

                jArguments.add("/zipreportfile:" + usedRxZippedReportDirectory + usedRxZippedReportFile + ZIPPED_REPORT_EXTENSION);
            }

            //Test Rail
            if (rxTestRail) {
                jArguments.add("/testrail");
                //TODO: Use Credentialmanager instead
                if (! StringUtil.isNullOrSpace(rxTestRailUser) && ! StringUtil.isNullOrSpace(rxTestRailPassword)) {
                    jArguments.add("/truser=" + rxTestRailUser);
                    jArguments.add("/trpass=" + rxTestRailPassword);
                } else {
                    LOGGER.println("Testrail username and password are required");
                    return false;
                }
                if (! StringUtil.isNullOrSpace(rxTestRailRID)) {
                    jArguments.add("/trrunid=" + rxTestRailRID);
                }
                if (! StringUtil.isNullOrSpace(rxTestRailRunName)) {
                    jArguments.add("/trrunname=" + rxTestRailRunName);
                }
            }

            // Parse Global Parameters
            if (! StringUtil.isNullOrSpace(rxGlobalParameter)) {
                for (String param : StringUtil.splitBy(rxGlobalParameter, ARGUMENT_SEPARATOR)) {
                    try {
                        RanorexParameter rxParam = new RanorexParameter(param);
                        rxParam.trim();
                        jArguments.add(rxParam.toString());
                    } catch (InvalidParameterException e) {
                        LOGGER.println("Parameter '" + param + "' will be ignored");
                    }
                }
            }

            // Additional cmd arguments
            if (! StringUtil.isNullOrSpace(cmdLineArgs)) {
                for (String argument : StringUtil.splitBy(cmdLineArgs, ARGUMENT_SEPARATOR)) {
                    try {
                        CmdArgument arg = new CmdArgument(argument);
                        jArguments.add(arg.toString());
                    } catch (InvalidParameterException e) {
                        LOGGER.println("Argument '" + argument + "' will be ignored ");
                    }
                }
            }
            // Summarize Output
            if (getDescriptor().isUseSummarize()) {
                LOGGER.println("\n*************Start of Ranorex Summary*************");
                LOGGER.println("Current Plugin version:\t\t" + getClass().getPackage().getImplementationVersion());
                LOGGER.println("Ranorex Working Directory:\t" + WorkSpace);
                LOGGER.println("Ranorex test suite file:\t" + rxTestSuiteFilePath);
                LOGGER.println("Ranorex test exe file:\t\t" + rxExecuteableFile);
                LOGGER.println("Ranorex run configuration:\t" + rxRunConfiguration);
                LOGGER.println("Ranorex report directory:\t" + usedRxReportDirectory);
                LOGGER.println("Ranorex report filename:\t" + usedRxReportFile);
                LOGGER.println("Ranorex report extension:\t" + rxReportExtension);
                LOGGER.println("Junit-compatible report:\t" + rxJUnitReport);
                LOGGER.println("Ranorex report compression:\t" + rxZippedReport);
                if (rxZippedReport) {
                    LOGGER.println("Ranorex zipped report dir:\t" + usedRxZippedReportDirectory);
                    LOGGER.println("Ranorex zipped report file:\t" + usedRxZippedReportFile);
                }
                LOGGER.println("Ranorex Test Rail Integration:\t" + rxTestRail);
                if (rxTestRail) {
                    LOGGER.println("Ranorex Test Rail User:\t\t" + rxTestRailUser);
                    LOGGER.println("Ranorex Test Rail Password:\t" + "*****************");
                    LOGGER.println("Ranorex Test Rail Run ID:\t" + rxTestRailRID);
                    LOGGER.println("Ranorex Test Rail Run Name:\t" + rxTestRailRunName);
                }
                LOGGER.println("Ranorex global parameters:");
                if (! StringUtil.isNullOrSpace(rxGlobalParameter)) {
                    for (String param : StringUtil.splitBy(rxGlobalParameter, ARGUMENT_SEPARATOR)) {
                        try {
                            RanorexParameter rxParam = new RanorexParameter(param);
                            rxParam.trim();
                            LOGGER.println("\t\t" + rxParam.toString());
                        } catch (InvalidParameterException e) {
                            LOGGER.println("\t\t!!Parameter '" + param + "' will be ignored");
                        }
                    }
                } else {
                    LOGGER.println("\t*No global parameters entered");
                }
                LOGGER.println("Command line arguments:");
                if (! StringUtil.isNullOrSpace(cmdLineArgs)) {
                    for (String argument : StringUtil.splitBy(cmdLineArgs, ARGUMENT_SEPARATOR)) {
                        try {
                            CmdArgument arg = new CmdArgument(argument);
                            arg.trim();
                            LOGGER.println("\t\t" + arg.toString());
                        } catch (InvalidParameterException e) {
                            LOGGER.println("\t\tArgument '" + argument + "' will be ignored ");
                        }
                    }
                } else {
                    LOGGER.println("\t*No command line arguments entered");
                }
                LOGGER.println("*************End of Ranorex Summary*************\n");
            }
            r = exec(build, launcher, listener, env); // Start the given exe file with all arguments added before
        } else {
            LOGGER.println("No TestSuite file given");
        }
        return r;
    }


    /**
     * Starts the given executeable file with all arguments and parameters
     *
     * @param build
     * @param launcher Starts a process
     * @param listener Receives events that happen during a build
     * @param env      Environmental variables to be used for launching processes for this build.
     * @return true if execution was succesfull; otherwise false
     * @throws InterruptedException
     * @throws IOException
     */
    private boolean exec(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener, EnvVars env) throws
            InterruptedException, IOException {
        FilePath currentWorkspace = FileUtil.getRanorexWorkingDirectory(build.getWorkspace(), rxTestSuiteFilePath);
        LOGGER.println("Executing : " + jArguments.toString());
        try {
            int r = launcher.launch().cmds(jArguments).envs(env).stdout(listener).pwd(currentWorkspace).join();

            if (r != 0) {
                build.setResult(Result.FAILURE);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace(listener.fatalError("execution failed"));
            return false;
        }
    }

    /**
     * Separates string into substrings
     *
     * @param build
     * @param env        Environmental variables to be used for launching processes for this build.
     * @param values     string containing either parameters or arguments
     * @param isParam    true if the string 'values' contains parameters, otherwise false
     * @param ignoreList List of parameters to ignore
     * @param output     true if logger should write to console
     * @return a list of strings containing parameters or arguments
     */
    private List<String> getParamArgs(AbstractBuild<?, ?> build, EnvVars env, String values, boolean isParam, ArrayList<
            String> ignoreList, Boolean output) {
        ArrayList<String> args = new ArrayList<>();
        StringTokenizer valuesToknzr = new StringTokenizer(values, "\t\r\n;");
        ArrayList<String> separators = new ArrayList<>(Arrays.asList(":", "="));
        String argumentToAdd;
        while (valuesToknzr.hasMoreTokens()) {
            String value = valuesToknzr.nextToken();
            value = Util.replaceMacro(value, env);
            value = Util.replaceMacro(value, build.getBuildVariables());
            String pureArgument = value.replace("/", "");//Remove the '/' from the argument
            for (String val : separators) {
                int position = pureArgument.indexOf(val);
                if (position > 0) {
                    pureArgument = pureArgument.substring(0, position); //only check for the argument without value
                }
            }
            if (! StringUtil.isNullOrSpace(value) && ! ignoreList.contains(pureArgument)) {
                if (isParam) {
                    if (! value.contains(("/pa:"))) {
                        argumentToAdd = "/pa:" + value;
                    } else {
                        argumentToAdd = value;
                    }
                } else {
                    argumentToAdd = value;
                }
                args.add(argumentToAdd.trim());
            } else {
                if (output) {
                    LOGGER.println("\t" + value + " will be ignored since the argument should be set via the desired input fields");
                }
            }
        }
        return args;
    }

    // Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();

    }

    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        /*
         * Configure Variables
         */
        private boolean useSummarize;

        /**
         * In order to load the persisted global configuration, you have to call load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

        //Check Report Directory
        public FormValidation doCheckRxReportDirectory(@QueryParameter String value) {
            if (! StringUtil.isNullOrSpace(value)) {
                return FormValidation.ok();
            } else {
                return FormValidation.warning("Current Ranorex Working directory will be used");
            }
        }

        // Check Report Filename
        public FormValidation doCheckRxReportFile(@QueryParameter String value) {
            if (! StringUtil.isNullOrSpace(value) && ! FileUtil.isAbsolutePath(value)) {
                return FormValidation.ok();
            } else if (FileUtil.isAbsolutePath(value)) {
                return FormValidation.error("'" + value + "' is not a valid Ranorex Report filename");
            } else {
                return FormValidation.warning("'%S_%Y%M%D_%T' will be used");
            }
        }

        // Check Zipped Report Directory
        public FormValidation doCheckRxZippedReportDirectory(@QueryParameter String value) {
            if (! StringUtil.isNullOrSpace(value)) {
                return FormValidation.ok();
            } else {
                return FormValidation.warning("Current Ranorex Working directory will be used");
            }
        }

        // Check Zipped Report Filename
        public FormValidation doCheckRxZippedReportFile(@QueryParameter String value, @QueryParameter String rxReportFile) {
            if (! StringUtil.isNullOrSpace(value) && ! FileUtil.isAbsolutePath(value)) {
                return FormValidation.ok();
            } else if (FileUtil.isAbsolutePath(value)) {
                return FormValidation.error("'" + value + "' is not a valid Ranorex Report filename");
            } else if ((StringUtil.isNullOrSpace(value) && StringUtil.isNullOrSpace(rxReportFile)) || (StringUtil.isNullOrSpace(value) && FileUtil.isAbsolutePath(rxReportFile))) {
                return FormValidation.warning("'%S_%Y%M%D_%T' will be used");
            } else {
                return FormValidation.warning("'" + rxReportFile + "' will be used");
            }
        }

        @SuppressWarnings ("rawtypes")
        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         *
         * @return a human readable string that is shown in the DropDown Menu
         */
        @Override
        public String getDisplayName() {
            return "Run a Ranorex test suite";
        }

        //Formvalidations
        // Check Test Rail Username
        public FormValidation doCheckRxTestRailUser(@QueryParameter String value) {
            if (! StringUtil.isNullOrSpace(value)) {
                return FormValidation.ok();
            }
            return FormValidation.error("Username is required");

        }

        // Check Test Rail Password
        public FormValidation doCheckRxTestRailPassword(@QueryParameter String value) {
            if (! StringUtil.isNullOrSpace(value)) {
                return FormValidation.ok();
            }
            return FormValidation.error("Password is required");
        }


        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            useSummarize = formData.getBoolean("useSummarize");
            save();
            return super.configure(req, formData); // To change body of generated methods, choose Tools | Templates.
        }

        public boolean isUseSummarize() {
            return useSummarize;
        }
    }
}
