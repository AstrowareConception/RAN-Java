# Script PowerShell de generation des archives etudiantes (sans teacher/)
$ErrorActionPreference = "Stop"

$ProjectRoot = Resolve-Path "$PSScriptRoot\.."
$DistDir = Join-Path $ProjectRoot "dist"

if (-not (Test-Path $DistDir)) {
    New-Item -ItemType Directory -Path $DistDir | Out-Null
}

Write-Host "=====================================================" -ForegroundColor Cyan
Write-Host "   GENERATION DES ARCHIVES ETUDIANTES (ABYSS EXPLORER)" -ForegroundColor Cyan
Write-Host "=====================================================" -ForegroundColor Cyan
Write-Host "Racine du projet : $ProjectRoot"
Write-Host "Repertoire cible : $DistDir"

# 1. Archive Complete Etudiante (student + docs + examples + tools + README)
$StudentZip = Join-Path $DistDir "abyss-explorer-student.zip"
if (Test-Path $StudentZip) { Remove-Item $StudentZip -Force }

$TempDir = Join-Path $env:TEMP "abyss-student-temp"
if (Test-Path $TempDir) { Remove-Item $TempDir -Recurse -Force }
New-Item -ItemType Directory -Path $TempDir | Out-Null

Copy-Item -Path (Join-Path $ProjectRoot "README.md") -Destination $TempDir
Copy-Item -Path (Join-Path $ProjectRoot "docs") -Destination $TempDir -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "student") -Destination $TempDir -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "examples") -Destination $TempDir -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "tools") -Destination $TempDir -Recurse
Remove-Item -Path (Join-Path $TempDir "tools\package-kits.ps1"), (Join-Path $TempDir "tools\package-kits.sh") -Force
Remove-Item -Path (Join-Path $TempDir "student\conception-objet\starter\target") -Recurse -Force -ErrorAction SilentlyContinue

Compress-Archive -Path "$TempDir\*" -DestinationPath $StudentZip -Force
Remove-Item $TempDir -Recurse -Force
Write-Host "[OK] Archive etudiante globale creee : $StudentZip" -ForegroundColor Green

# 2. Archive Specifique : Parcours Essentiel
$EssentielZip = Join-Path $DistDir "abyss-explorer-essentiel.zip"
$TempEssentiel = Join-Path $env:TEMP "abyss-essentiel-temp"
if (Test-Path $TempEssentiel) { Remove-Item $TempEssentiel -Recurse -Force }
New-Item -ItemType Directory -Path $TempEssentiel | Out-Null

Copy-Item -Path (Join-Path $ProjectRoot "README.md") -Destination $TempEssentiel
Copy-Item -Path (Join-Path $ProjectRoot "docs") -Destination $TempEssentiel -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "examples") -Destination $TempEssentiel -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "tools") -Destination $TempEssentiel -Recurse
Remove-Item -Path (Join-Path $TempEssentiel "tools\package-kits.ps1"), (Join-Path $TempEssentiel "tools\package-kits.sh") -Force
Remove-Item -Path (Join-Path $TempEssentiel "student\essentiel\starter\bin") -Recurse -Force -ErrorAction SilentlyContinue

$StudentSub = Join-Path $TempEssentiel "student"
New-Item -ItemType Directory -Path $StudentSub | Out-Null
Copy-Item -Path (Join-Path $ProjectRoot "student\CHOISIR_MON_PARCOURS.md") -Destination $StudentSub
Copy-Item -Path (Join-Path $ProjectRoot "student\JE_SUIS_BLOQUE.md") -Destination $StudentSub
Copy-Item -Path (Join-Path $ProjectRoot "student\essentiel") -Destination $StudentSub -Recurse

Compress-Archive -Path "$TempEssentiel\*" -DestinationPath $EssentielZip -Force
Remove-Item $TempEssentiel -Recurse -Force
Write-Host "[OK] Archive parcours Essentiel creee : $EssentielZip" -ForegroundColor Green

# 3. Archive Specifique : Parcours Conception Objet
$ConceptionZip = Join-Path $DistDir "abyss-explorer-conception-objet.zip"
$TempConception = Join-Path $env:TEMP "abyss-conception-temp"
if (Test-Path $TempConception) { Remove-Item $TempConception -Recurse -Force }
New-Item -ItemType Directory -Path $TempConception | Out-Null

Copy-Item -Path (Join-Path $ProjectRoot "README.md") -Destination $TempConception
Copy-Item -Path (Join-Path $ProjectRoot "docs") -Destination $TempConception -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "examples") -Destination $TempConception -Recurse
Copy-Item -Path (Join-Path $ProjectRoot "tools") -Destination $TempConception -Recurse
Remove-Item -Path (Join-Path $TempConception "tools\package-kits.ps1"), (Join-Path $TempConception "tools\package-kits.sh") -Force
Remove-Item -Path (Join-Path $TempConception "student\conception-objet\starter\target") -Recurse -Force -ErrorAction SilentlyContinue

$StudentSub2 = Join-Path $TempConception "student"
New-Item -ItemType Directory -Path $StudentSub2 | Out-Null
Copy-Item -Path (Join-Path $ProjectRoot "student\CHOISIR_MON_PARCOURS.md") -Destination $StudentSub2
Copy-Item -Path (Join-Path $ProjectRoot "student\JE_SUIS_BLOQUE.md") -Destination $StudentSub2
Copy-Item -Path (Join-Path $ProjectRoot "student\conception-objet") -Destination $StudentSub2 -Recurse

Compress-Archive -Path "$TempConception\*" -DestinationPath $ConceptionZip -Force
Remove-Item $TempConception -Recurse -Force
Write-Host "[OK] Archive parcours Conception Objet creee : $ConceptionZip" -ForegroundColor Green
Write-Host "Toutes les archives etudiantes ont ete generees sans le dossier teacher/." -ForegroundColor Cyan
