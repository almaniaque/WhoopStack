echo ========================================
echo Installation de WhoopStack
echo ========================================
echo.

echo Verification de Java...

java -version >nul 2>&1
if errorlevel 1 (
    echo Java n'est pas installe ou pas detecte.
    echo Installation automatique de Java 21 avec Eclipse Temurin...
    echo.

    winget install EclipseAdoptium.Temurin.21.JDK --silent --accept-package-agreements --accept-source-agreements

    if errorlevel 1 (
        echo.
        echo ERREUR: impossible d'installer Java automatiquement.
        echo Verifiez que winget est disponible sur ce PC.
        echo Commande de test:
        echo winget --version
        pause
        exit /b 1
    )
) else (
    echo Java est deja installe.
)

echo.
echo Configuration de JAVA_HOME...

set "JAVA_HOME_CANDIDATE="

for /d %%D in ("C:\Program Files\Eclipse Adoptium\jdk-21*") do (
    set "JAVA_HOME_CANDIDATE=%%D"
)

if "%JAVA_HOME_CANDIDATE%"=="" (
    for /d %%D in ("C:\Program Files\Java\jdk-21*") do (
        set "JAVA_HOME_CANDIDATE=%%D"
    )
)

if "%JAVA_HOME_CANDIDATE%"=="" (
    echo ERREUR: JDK installe mais dossier introuvable.
    echo Verifiez dans C:\Program Files\Eclipse Adoptium\
    pause
    exit /b 1
)

setx JAVA_HOME "%JAVA_HOME_CANDIDATE%" >nul
set "JAVA_HOME=%JAVA_HOME_CANDIDATE%"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo JAVA_HOME configure sur:
echo %JAVA_HOME%
echo.

echo Verification finale de Java...
java -version
javac -version

echo.
echo ========================================
echo Installation du backend Spring Boot...
echo ========================================

cd /d "%~dp0..\backend\springboot\devis"

call mvnw.cmd clean install

if errorlevel 1 (
    echo.
    echo ERREUR backend.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Installation du frontend Angular...
echo ========================================

cd /d "%~dp0..\frontend\angular\my-app"

call npm install

if errorlevel 1 (
    echo.
    echo ERREUR frontend.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Installation terminee avec succes.
echo ========================================
pause