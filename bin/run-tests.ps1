$env:JAVA_HOME = "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2025.2.2\jbr"
$mvn = "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2025.2.2\plugins\maven\lib\maven3\bin\mvn.cmd"

& $mvn test

Invoke-Item "cucumber-reports\report-signup.html"
Invoke-Item "cucumber-reports\report-login.html"
Invoke-Item "cucumber-reports\report-cart.html"
Invoke-Item "target\surefire-reports\index.html"
