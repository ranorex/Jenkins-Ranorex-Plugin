# Jenkins Ranorex Plugin
This plugin provides an easy way to run a Ranorex test suite as a build step in your Jenkins job. You can run a test suite as is without any need for configuration, or you can make several different advanced settings.
## How to use
After installing the Jenkins Ranorex Plugin, you have the abillity to choose "Run a Ranorex test suite" in the "Add build step" dropdown.

### Configure the build step
#### Simple Usage
##### Ranorex test suite file
Enter the path to the test suite file (*.rxtst) located in the output folder of your solution.

#### Advanced options
Extend the “Advanced…” section to access more options.

##### Ranorex run configuration
Runs the test suite using one of the run configurations available in the test suite. Enter the exact name of the run configuration you want to use. By default, the run configuration currently selected in the test suite is used.
If you want to create or edit run configurations, please use Ranorex Studio or the Ranorex Test Suite Runner.

##### Ranorex report directory
Allows you to specify the directory that your report will be saved to. If you don’t specify a path, the directory where your test executable is located will be used.

##### Ranorex report file name
Allows you to specify the file name of the report with the standard placeholders available in the <a href ="https://www.ranorex.com/support/user-guide-20/lesson-4-ranorex-test-suite.html#c16095" target="_blank">test suite settings</a>.
By default, the file name specified in the test suite settings is used (for example: %S_%Y%M%D_%T.rxlog).
##### JUnit-compatible report

##### Compressed copy of Ranorex report
Compresses the report and the associated files into a single archive with the .rxzlog extension. The following additional input fields will appear when this option is enabled:
        * Compressed report directory
        * Compressed report file name

###### Compressed report directory
Allows you to specify the directory that your compressed report will be saved to. If you don’t specify a path, the directory where your test executable is located will be used.

###### Compressed report file name
Allows you to specify the file name of the compressed report with the standard placeholders available in the <a href ="https://www.ranorex.com/support/user-guide-20/lesson-4-ranorex-test-suite.html#c16095" target="_blank">test suite settings</a>. By default, the file name specified in the test suite settings is used (for example: %S_%Y%M%D_%T.rxlog).

##### Global parameters
Allows you to create or override values for global parameters set in the test suite.
    Enter parameters according to the following pattern: "ParameterName=Value"
    Separate parameters with semicolons or newlines.

##### Command line arguments
Allows you to add Ranorex command line arguments. See the most important arguments in the [Ranorex online user guide](https://www.ranorex.com/support/user-guide-20/lesson-4-ranorex-test-suite.html#c16113 "Ranorex online User Guide")

Please note that the following arguments will be ignored:
	* /reportfile
	* /rf
	* /zipreport
	* /zr
	* /zipreportfile
	* /zrf
	* /runconfig
	* /rc
	* /param
	* /pa