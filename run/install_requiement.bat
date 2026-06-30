@echo off
title Installation de WhoopStack

echo Installation du backend Spring-Boot...
cd backend/springboot/devis
call mvn clean install
cd ..

echo Installation du Frontend Angular
cd frontend/angular/my-app
call npm install
cd ..

echo Installation terminee
pause