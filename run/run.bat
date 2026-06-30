@echo off
title WhoopStack

echo Demarrage du Backend...
start cmd /k "cd backend/springboot/devis && mvn spring-boot:run"

timeout /t 5 > nul

echo Demarrage du Frontend...
start cmd /k "cd frontend/angular && ng serve --open"

echo WhoopStack en cours de lancement
pause