<#
.Synopsis
   A simple script for build and/or release plugin
.EXAMPLE
   Local Build: build.ps1 -clean
.EXAMPLE
   Release: build.ps1 -release -u USERNAME -p PASSWORD
#>
    [CmdletBinding(DefaultParameterSetName="none", SupportsShouldProcess=$true)]
    Param
    (
        # Clean Workspace
        [Alias("c")]
        [Parameter(Mandatory=$false)]
        [switch]$clean,

        # Release Plugin
        [Alias("r")]
        [Parameter(Mandatory=$false)]
        [switch]$release,
		
		[Alias("d")]
		[Parameter(Mandatory=$false)]
		[switch]$dryRun,
        # Skip Tests
        [Alias("s")]
        [Parameter(Mandatory=$false)]
        [switch]$skipTests,

        # Username for release
        [Alias("u")]
        [Parameter(Mandatory=$false)]
        [string]$username="default",

        # Password for release
        [Alias("p")]
        [Parameter(Mandatory=$false)]
        [string]$password,

        # Debug Build
        [Parameter(Mandatory=$false)]
        [switch]$fdebug,

        # Show Stacktrace
        [Parameter(Mandatory=$false)]
        [switch]$stackTrace
    )

    $parameters=""
    if($clean)
    {     
        $parameters+="clean "
    }
    if($release)
    {
    # Add release parameter Write-Output "release Project!"
	if($username)
	{
		$parameters+="-Dusername "+$username+" "
	}
	
	$parameters+="release:prepare release:perform "
	
    }
	if($dryRun)
	{
	$parameters+="-DdryRun=true release:prepare "
	}
    if($skipTests)
    {
        $parameters+="-DskipTests "
    }
   
    if($fdebug)
    {
        $parameters+="-X "
    }
    if($stackTrace)
    {
        $parameters+="-e "
    }
    $parameters +="package "
    $parameters = $parameters.Trim()
    Write-Output "Executing Command: mvn $parameters"

    Invoke-Expression "mvn $parameters"

    Write-Output "Done!!!"
