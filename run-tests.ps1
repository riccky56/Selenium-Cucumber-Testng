param([switch]$CompileOnly)

$ErrorActionPreference = 'Stop'
if (-not $env:JAVA_HOME -or -not (Test-Path "$env:JAVA_HOME\bin\javac.exe")) {
	throw 'Set JAVA_HOME to a JDK 21 or newer installation.'
}
$compilerVersion = & "$env:JAVA_HOME\bin\javac.exe" -version
if ($LASTEXITCODE -ne 0 -or "$compilerVersion" -notmatch '^javac (\d+)' -or [int]$Matches[1] -lt 21) {
	throw "JDK 21 or newer is required. Found: $compilerVersion"
}
$mvn = (Get-Command mvn.cmd -ErrorAction Stop).Source

Push-Location $PSScriptRoot
try {
	$goal = if ($CompileOnly) { 'test-compile' } else { 'test' }
	& $mvn --batch-mode --no-transfer-progress -f "$PSScriptRoot\pom.xml" $goal
	if ($LASTEXITCODE -ne 0) {
		throw "Maven failed with exit code $LASTEXITCODE."
	}
	if (-not $CompileOnly) {
		foreach ($report in @('cucumber-reports\report-signup.html', 'cucumber-reports\report-login.html', 'cucumber-reports\report-cart.html', 'target\surefire-reports\index.html')) {
			$reportPath = Join-Path $PSScriptRoot $report
			if (Test-Path $reportPath) { Invoke-Item $reportPath }
		}
	}
} finally {
	Pop-Location
}
