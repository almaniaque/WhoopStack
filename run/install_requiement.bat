@echo off
title Installation de WhoopStack

echo Installation du backend Spring-Boot...
cd backend/springboot/devis
call .\mvnw clean install
cd ..

echo Installation du Frontend Angular
cd frontend/angular/my-app
call npm install
cd ..

echo Installation terminee
pause