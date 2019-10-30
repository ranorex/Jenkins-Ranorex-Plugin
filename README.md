
# Jenkins Ranorex Plugin

## About

This plugin provides an easy way to run a [Ranorex](https://www.ranorex.com/) test suite as a build step in your Jenkins job. You can run a test suite as is without any need for configuration, or you can make several different advanced settings.

## Installation
1.  Open Jenkins and click "Manage Jenkins".
2.  Click "Manage Plugins".
3.  Switch to the "Available" tab and search for “Ranorex” in the filter box.
4.  Check the box next to the plugin and click “Install without restart”.

![Plugin installation](https://wiki.jenkins.io/download/attachments/121471012/install%20plugin2.png?version=1&modificationDate=1494934029000&api=v2)
## Using the plugin
1.  Add a new build step in your Jenkins job configuration (please refer to the [Jenkins documentation](https://jenkins.io/doc/) for details on how to do so).
2.  Select “Run a Ranorex test suite” from the drop-down menu
3.  In the field next to “Ranorex test suite file”, enter the path to the test suite file (\*.rxtst) located in the output folder of your solution.  ![Plugin build step](https://wiki.jenkins.io/download/attachments/121471012/image2017-5-12_13-48-19.png?version=1&modificationDate=1494934187000&api=v2)
4.  Finally, save your changes to the Jenkins job configuration.

The test suite is now ready and can be run as a build step in your Jenkins job. You can also make additional advanced settings.

### Pipeline usage  
When setting up Jenkins Pipelines, the Ranorex plugin can be invoked via the pipeline step "ranorex", e.g.: ranorex rxTestSuiteFilePath: 'C:\\MyTestPath\\MyTest.rxtst'  
The plugin is also compatible with the Snippet Generator. To generate the pipeline script:  
1. Under Pipeline, go to Pipeline Syntax.  
2. From the drop-down menu, select ranorex > Run a Ranorex test suite.  
3. Enter the path to the test suite file (*.rxtst) and any optional settings under Advanced settings.  
4. Click Generate Pipeline Script.

The documentation for the advanced field options can be found under Steps Reference in the left bar of the Snippet Generator.

### Advanced settings

Once you have provided the path to your Ranorex test suite file, click on “Advanced…” below the path field. The following settings will appear. Configure them according to your requirements and save your Jenkins job configuration to activate them.

![Plugin advanced settings](https://wiki.jenkins.io/download/attachments/121471012/image2017-6-26%209:13:4.png?version=1&modificationDate=1498483006000&api=v2)

##### Ranorex run configuration

Runs the test suite using one of the run configurations available in the test suite. Enter the exact name of the run configuration you want to use. By default, the run configuration currently selected in the test suite is used.

If you want to create or edit run configurations, please use Ranorex Studio or the Ranorex Test Suite Runner.

#### Ranorex report directory

Allows you to specify the directory that your report will be saved to. If you don’t specify a path, the directory where your test executable is located will be used.

#### Ranorex report file name

Allows you to specify the file name of the report with the standard placeholders available in the [test suite settings](https://www.ranorex.com/support/user-guide-20/lesson-4-ranorex-test-suite.html#c16095). By default, the file name specified in the test suite settings is used (for example: %S\_%Y%M%D\_%T.rxlog).

#### Ranorex report file extension

Allows you to specify the Ranorex report file extension. Available values ("rxlog", "html").

#### JUnit-compatible report

If enabled, Ranorex will create both a JUnit-compatible report and a Ranorex report.

#### Compressed copy of Ranorex report

Compresses the report and the associated files into a single archive with the .rxzlog extension. The following additional input fields will appear when this option is enabled:

#### Compressed report directory

Allows you to specify the directory that your compressed report will be saved to. If you don’t specify a path, the directory where your test executable is located will be used.

#### Compressed report file

Allows you to specify the file name of the compressed report with the standard placeholders available in the test suite Settings. If no filename is specified, the same name as the "Ranorex Report File Name" is used.

#### Global parameters

Allows you to create or override values for global parameters set in the test suite.
Enter parameters according to the following pattern: "ParameterName=Value"
Separate parameters with semicolons or newlines.

#### Command line arguments

Allows you to add Ranorex command line arguments. Some of the most important ones include:

-   **config \| cfg:\<config parameter name\>=\<value\>**

    -   Set values for configuration parameters.

-   **reportlevel \| rl: Debug\|Info\|Warn\|Error\|Success\|Failure\|\<any integer\>**  
    -   Sets the minimum report level that log messages need to have in order to be included in the log file. Specify 'None' to
        completely disable reporting. These levels correspond to the following integer values:
        Debug=10,Info=20,Warn=30,Error=40,Success=110,Failure=120

-   **testcase \| tc:\<name or guid of test case\>**  
    -   Runs this test case only.

-   **testsuite \| ts:\<path to test suite file\>**  

    -   Runs the test cases defined by the test suite (rxtst) file. By default the rxtst file with the same name as the
        \<TestSuiteExe\> is used or the first rxtst file in the same folder as \<TestSuiteExe\>.

-   **module \| mo:\<module name or guid\>**  
    -   Runs the module with the specified name or guid. Assemblies loaded by \<TestSuiteExe\> and assemblies referenced in the rxtst file are searched.

-   **testcaseparam \| tcpa:\<name or guid of test case\>:\<parameter name\>=\<value\>**  
    -   Creates or overrides values for test case parameters specified
        in the test suite.

-   **runlabel \| rul:\<custom value\>**  

    -   Sets a custom run label for the test run.

-   **testcasedatarange \| tcdr:\<name or guid of test case\>=\<data range\>**  

    -   Sets the data range for a test case.

Please find additional information in the following blog [Integrate Ranorex test automation into Jenkins continuous integration process](https://www.ranorex.com/blog/integrating-ranorex-automation-in-jenkins-continuous-integration-process/)
