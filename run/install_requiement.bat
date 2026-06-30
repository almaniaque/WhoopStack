@echo off
title Installation de WhoopStack

cd /d "%~dp0.."

echo Installation du backend SpringBoot...
cd backend/springboot/devis
call mvnw.cmd clean install
cd /d "%~dp0.."

echo Installation du Frontend Angular
cd frontend/angular/my-app
call npm install
cd /d "%~dp0.."

echo Installation terminee
pause