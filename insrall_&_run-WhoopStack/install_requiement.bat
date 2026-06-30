@echo off
title Installation de WhoopStack

cd /d "%~dp0.."

echo Installation MySQL Server...
winget install Oracle.MySQL --accept-package-agreements --accept-source-agreements

echo Installation MySQL Workbench...
winget install Oracle.MySQLWorkbench --accept-package-agreements --accept-source-agreements

echo Installation du backend SpringBoot...
cd backend\springboot\devis
call mvnw.cmd clean install

if errorlevel 1 (
    echo.
    echo ERREUR backend.
    pause
    exit /b 1
)

cd /d "%~dp0.."

echo Installation du Frontend Angular...
cd frontend\angular\my-app

echo Installation des dependances Angular...
call npm install bootstrap bootstrap-icons chartjs-plugin-datalabels chart.js ng2-charts jspdf html2canvas

echo Installation des dependances de dev Angular...
call npm install --save-dev --save-exact @types/node@20
call npm install --save-dev @types/mocha vitest

echo Installation Angular globale CLI...
call npm install -g @angular/cli

echo Installation Angular Material...
call ng add @angular/material

cd /d "%~dp0.."

echo Installation terminee
pause