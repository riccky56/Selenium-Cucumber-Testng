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

Invoke-Item "cucumber-reports\report-signup.html"
Invoke-Item "cucumber-reports\report-login.html"
Invoke-Item "cucumber-reports\report-cart.html"
Invoke-Item "target\surefire-reports\index.html"
