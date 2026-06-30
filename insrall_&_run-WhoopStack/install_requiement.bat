echo ========================================
echo Installation de WhoopStack
echo ========================================
echo.


echo ========================================
echo Verification de Java...
echo ========================================
echo.

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
echo Installation et configuration de MySQL
echo ========================================

cd /d "%~dp0.."

set "MYSQL_EXE="

for /f "delims=" %%i in ('dir "C:\Program Files\MySQL\mysql.exe" /s /b 2^>nul ^| findstr /i "MySQL Server.*bin"') do (
    set "MYSQL_EXE=%%i"
)

if "%MYSQL_EXE%"=="" (
    echo MySQL Server introuvable. Installation...
    winget install Oracle.MySQL --accept-package-agreements --accept-source-agreements
)

winget install Oracle.MySQLWorkbench --accept-package-agreements --accept-source-agreements

for /f "delims=" %%i in ('dir "C:\Program Files\MySQL\mysql.exe" /s /b 2^>nul ^| findstr /i "MySQL Server.*bin"') do (
    set "MYSQL_EXE=%%i"
)

if "%MYSQL_EXE%"=="" (
    echo ERREUR : MySQL Server reste introuvable.
    pause
    exit /b 1
)

for %%i in ("%MYSQL_EXE%") do set "MYSQL_BIN=%%~dpi"

setx PATH "%PATH%;%MYSQL_BIN%" >nul

echo Creation de la base whoopstack...
"%MYSQL_EXE%" -u root -p -e "CREATE DATABASE IF NOT EXISTS whoopstack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

if errorlevel 1 (
    echo.
    echo ERREUR MySQL : mot de passe root incorrect, service MySQL arrete, ou configuration incomplete.
    pause
    exit /b 1
)


echo.
echo ========================================
echo Installation du backend Spring Boot...
echo ========================================

cd /d "%~dp0..\backend\springboot\devis"

call mvnw.cmd clean package -Dmaven.test.skip=true

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

echo Installation de Node.js LTS...
winget install OpenJS.NodeJS.LTS --accept-package-agreements --accept-source-agreements

cd /d "%~dp0..\frontend\angular\my-app"

call npm install
call npm install bootstrap bootstrap-icons chartjs-plugin-datalabels chart.js ng2-charts jspdf html2canvas
call npm install --save-dev --save-exact @types/node@20
call npm install --save-dev @types/mocha vitest
call npm install -g @angular/cli
call npx ng add @angular/material

if errorlevel 1 (
    echo.
    echo ERREUR frontend.
    pause
    exit /b 1
)


cd /d "%~dp0.."

echo.
echo ========================================
echo Installation terminee avec succes.
echo ========================================
pause