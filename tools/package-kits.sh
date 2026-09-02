#!/usr/bin/env bash
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
DIST_DIR="$PROJECT_ROOT/dist"

mkdir -p "$DIST_DIR"

echo "====================================================="
echo "   GENERATION DES ARCHIVES ETUDIANTES (ABYSS EXPLORER)"
echo "====================================================="
echo "Racine : $PROJECT_ROOT"
echo "Cible  : $DIST_DIR"

# 1. Archive globale
STUDENT_ZIP="$DIST_DIR/abyss-explorer-student.zip"
rm -f "$STUDENT_ZIP"
TEMP_DIR=$(mktemp -d)
cp "$PROJECT_ROOT/README.md" "$TEMP_DIR/"
cp -r "$PROJECT_ROOT/docs" "$TEMP_DIR/"
cp -r "$PROJECT_ROOT/student" "$TEMP_DIR/"
cp -r "$PROJECT_ROOT/examples" "$TEMP_DIR/"
cp -r "$PROJECT_ROOT/tools" "$TEMP_DIR/"
rm -f "$TEMP_DIR/tools/package-kits.ps1" "$TEMP_DIR/tools/package-kits.sh"
rm -rf "$TEMP_DIR/student/conception-objet/starter/target" "$TEMP_DIR/student/essentiel/starter/bin"
(cd "$TEMP_DIR" && zip -r "$STUDENT_ZIP" ./* >/dev/null)
rm -rf "$TEMP_DIR"
echo "[OK] Archive globale etudiante creee : $STUDENT_ZIP"

# 2. Archive Essentiel
ESSENTIEL_ZIP="$DIST_DIR/abyss-explorer-essentiel.zip"
rm -f "$ESSENTIEL_ZIP"
TEMP_ESS=$(mktemp -d)
cp "$PROJECT_ROOT/README.md" "$TEMP_ESS/"
cp -r "$PROJECT_ROOT/docs" "$TEMP_ESS/"
cp -r "$PROJECT_ROOT/examples" "$TEMP_ESS/"
cp -r "$PROJECT_ROOT/tools" "$TEMP_ESS/"
rm -f "$TEMP_ESS/tools/package-kits.ps1" "$TEMP_ESS/tools/package-kits.sh"
rm -rf "$TEMP_ESS/student/essentiel/starter/bin"
mkdir -p "$TEMP_ESS/student"
cp "$PROJECT_ROOT/student/CHOISIR_MON_PARCOURS.md" "$TEMP_ESS/student/"
cp "$PROJECT_ROOT/student/JE_SUIS_BLOQUE.md" "$TEMP_ESS/student/"
cp -r "$PROJECT_ROOT/student/essentiel" "$TEMP_ESS/student/"
(cd "$TEMP_ESS" && zip -r "$ESSENTIEL_ZIP" ./* >/dev/null)
rm -rf "$TEMP_ESS"
echo "[OK] Archive parcours Essentiel creee : $ESSENTIEL_ZIP"

# 3. Archive Conception Objet
CONCEPTION_ZIP="$DIST_DIR/abyss-explorer-conception-objet.zip"
rm -f "$CONCEPTION_ZIP"
TEMP_CONC=$(mktemp -d)
cp "$PROJECT_ROOT/README.md" "$TEMP_CONC/"
cp -r "$PROJECT_ROOT/docs" "$TEMP_CONC/"
cp -r "$PROJECT_ROOT/examples" "$TEMP_CONC/"
cp -r "$PROJECT_ROOT/tools" "$TEMP_CONC/"
rm -f "$TEMP_CONC/tools/package-kits.ps1" "$TEMP_CONC/tools/package-kits.sh"
rm -rf "$TEMP_CONC/student/conception-objet/starter/target"
mkdir -p "$TEMP_CONC/student"
cp "$PROJECT_ROOT/student/CHOISIR_MON_PARCOURS.md" "$TEMP_CONC/student/"
cp "$PROJECT_ROOT/student/JE_SUIS_BLOQUE.md" "$TEMP_CONC/student/"
cp -r "$PROJECT_ROOT/student/conception-objet" "$TEMP_CONC/student/"
(cd "$TEMP_CONC" && zip -r "$CONCEPTION_ZIP" ./* >/dev/null)
rm -rf "$TEMP_CONC"
echo "[OK] Archive parcours Conception Objet creee : $CONCEPTION_ZIP"
echo "Termine sans inclure teacher/."
