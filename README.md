# Jenkins Ranorex Plugin
This plugin provides an easy way to run a Ranorex Test Suite in your Jenkins Job as a build step. The simple usage allows the user to run a Ranorex Test without any additional settings whereby the advanced usage allows you to add several parameters.
## How to use
After installing the Jenkins Ranorex Plugin, you have the abillity to choose "Run a Ranorex Test Suite" in the "Add build step" dropdown.

### Configure the build step
#### Simple Usage
##### Ranorex Test Suite File
Path to your Ranorex Test Suite file (*.rxtst) that is located in the output directory of your Ranorex test.

#### Advanced options
Extend the “Advanced…” section to access more options.

##### Ranorex Run Configuration
Runs the test cases of the specified configuration defined by the rxtst file. Configurations can be edited using Ranorex Studio or TestSuiteRunner. By default, the currently selected run config is used.

##### Ranorex Report Directory
Sets the path of the report file. If no path is provided, the current directory is used.

##### Ranorex Report File
Sets the name of the report file. By default, the filename specified in the rxtst file is used. (for example: %S_%Y%M%D_%T.rxlog).

##### Compressed Copy of Ranorex Report
Compresses the report (including associated files) to a single archive (".rxzlog" extension). By enabling this option, you will gain access to additional input fields.
###### Zipped Report Directory
Sets the path of the zipped report file. If no path is provided, the current directory is used.
###### Zipped Report File
Sets the name of the zipped report file. By default, the filename specified in the rxtst file is used. (for example: %S_%Y%M%D_%T.rxlog).

##### Global Parameters
Creates or overrides values for global parameters specified in the test suite.
Currently the parameters must be entered with the following schema: ParameterName="Value" You can either use a semicolon or a new line to separate different parameters
 

##### Other Command Line Arguments
Allows the user to add additional Ranorex command line arguments. For example [LINK TO Command Line Arguments]


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

More information about the plugin can be found here: [LINK TO JENKINS PLUGIN SIDE]
