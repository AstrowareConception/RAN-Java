@echo off
echo ======================================================
echo    ABYSS EXPLORER - DIAGNOSTIC DE L'ENVIRONNEMENT
echo ======================================================
echo.

echo [1/3] Verification de l'environnement d'execution Java (java)...
where java >nul 2>nul
if %ERRORLEVEL% equ 0 (
    java -version
    echo [OK] Commande 'java' detectee.
) else (
    echo [ATTENTION] La commande 'java' n'est pas dans le PATH systeme.
    echo Assurez-vous d'avoir installe un JDK (Java 21 ou superieur recommande).
)
echo.

echo [2/3] Verification du compilateur Java (javac)...
where javac >nul 2>nul
if %ERRORLEVEL% equ 0 (
    javac -version
    echo [OK] Commande 'javac' detectee.
) else (
    echo [ATTENTION] La commande 'javac' n'est pas dans le PATH systeme.
)
echo.

echo [3/3] Verification de Maven (mvn) - Requis pour Parcours Conception Objet...
where mvn >nul 2>nul
if %ERRORLEVEL% equ 0 (
    mvn -version
    echo [OK] Apache Maven detecte.
) else (
    echo [INFO] 'mvn' non detecte dans le PATH.
    echo Note : Maven n'est pas requis pour le parcours Essentiel.
    echo Pour le parcours Conception Objet, utilisez l'integration Maven de votre IDE (IntelliJ, Eclipse, VS Code).
)
echo.
echo ======================================================
echo Diagnostic termine.
echo ======================================================
pause
