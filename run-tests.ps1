<<<<<<< HEAD
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
=======
$javaCmd = Get-Command java -ErrorAction SilentlyContinue
$javacCmd = Get-Command javac -ErrorAction SilentlyContinue
if (-not $javaCmd -or -not $javacCmd) {
	throw "JDK (java + javac) is not available on PATH. Install JDK 17+ and add it to PATH."
}

if (-not $env:JAVA_HOME) {
	# Derive JAVA_HOME from javac to avoid pointing at a JRE runtime folder.
	$env:JAVA_HOME = Split-Path -Parent (Split-Path -Parent $javacCmd.Source)
}

$mvnCmd = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $mvnCmd) {
	$ideaMaven = Get-ChildItem "$env:ProgramFiles\JetBrains\IntelliJ IDEA*\plugins\maven\lib\maven3\bin\mvn.cmd" -ErrorAction SilentlyContinue |
		Sort-Object LastWriteTime -Descending |
		Select-Object -First 1

	if ($ideaMaven) {
		$mvnCmd = @{ Source = $ideaMaven.FullName }
	}
	else {
		throw "Maven is not available on PATH. Install Maven and add it to PATH."
	}
}

& $mvnCmd.Source test
>>>>>>> aed06dac5c8b3e3064c1c0605503d7a86f11b1c2

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
