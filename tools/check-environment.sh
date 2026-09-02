#!/usr/bin/env bash
echo "======================================================"
echo "   ABYSS EXPLORER - DIAGNOSTIC DE L'ENVIRONNEMENT"
echo "======================================================"
echo ""

echo "[1/3] Verification de l'environnement d'execution Java (java)..."
if command -v java >/dev/null 2>&1; then
    java -version
    echo "[OK] Commande 'java' detectee."
else
    echo "[ATTENTION] La commande 'java' n'est pas dans le PATH."
    echo "Installez un JDK (Java 21 LTS recommande)."
fi
echo ""

echo "[2/3] Verification du compilateur Java (javac)..."
if command -v javac >/dev/null 2>&1; then
    javac -version
    echo "[OK] Commande 'javac' detectee."
else
    echo "[ATTENTION] La commande 'javac' n'est pas dans le PATH."
fi
echo ""

echo "[3/3] Verification de Maven (mvn)..."
if command -v mvn >/dev/null 2>&1; then
    mvn -version
    echo "[OK] Apache Maven detecte."
else
    echo "[INFO] 'mvn' non detecte dans le PATH."
    echo "Note : Maven n'est pas requis pour le parcours Essentiel."
    echo "Pour le parcours Conception Objet, utilisez l'integration Maven de votre IDE."
fi
echo ""
echo "======================================================"
echo "Diagnostic termine."
echo "======================================================"
