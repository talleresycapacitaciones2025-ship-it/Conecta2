# Conecta2

Aplicación Android nativa de ciberseguridad familiar desarrollada con Kotlin y Jetpack Compose.

## 📱 Características

- **Perfiles separados**: Persona adulta/cuidador y Aventurera/Aventurero (menor)
- **Juego de la huella digital**: Descubre qué información compartes en internet
- **Juego de emparejar**: Une conceptos de ciberseguridad
- **Generador de contraseñas**: Crea contraseñas seguras con animación de tragamonedas
- **Sección Aprende**: Tarjetas interactivas sobre peligros, protección y consejos familiares
- **Convenio familiar**: Editor de reglas para el uso responsable de internet (solo adultos)
- **Modo claro/oscuro**: Tema adaptable con paleta de colores naranjas

## 🛠️ Requisitos técnicos

- SDK mínimo: 24
- SDK objetivo: 34
- Kotlin 1.9.20
- Jetpack Compose con Material 3
- DataStore para almacenamiento local

## 📂 Estructura del proyecto

```
Conecta2/
├── app/
│   ├── src/main/
│   │   ├── java/com/conecta2/
│   │   │   ├── MainActivity.kt
│   │   │   ├── data/
│   │   │   │   └── Data.kt (modelos y datos)
│   │   │   ├── ui/
│   │   │   │   ├── navigation/
│   │   │   │   ├── screens/
│   │   │   │   └── theme/
│   │   │   └── ...
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   ├── mipmap-*/
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── .github/workflows/
│   └── build-apk.yml
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🚀 Subir a GitHub

1. Crea un nuevo repositorio en GitHub llamado `Conecta2`
2. En tu terminal local:
```bash
cd Conecta2
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/Conecta2.git
git push -u origin main
```

## 🔐 Configurar secretos para APK Release (opcional)

Si deseas firmar el APK release, configura los siguientes secretos en GitHub:

1. Ve a tu repositorio → **Settings** → **Secrets and variables** → **Actions**
2. Añade los siguientes secretos:

| Nombre | Descripción |
|--------|-------------|
| `KEYSTORE_BASE64` | Tu archivo keystore codificado en base64 |
| `KEYSTORE_PASSWORD` | Contraseña del keystore |
| `KEY_ALIAS` | Alias de la clave |
| `KEY_PASSWORD` | Contraseña de la clave |

### Generar keystore y obtener base64

```bash
# Generar keystore
keytool -genkey -v -keystore release.keystore -alias conecta2 -keyalg RSA -keysize 2048 -validity 10000

# Codificar en base64 (Linux/Mac)
base64 release.keystore

# O en Windows PowerShell
[Convert]::ToBase64String([IO.File]::ReadAllBytes("release.keystore"))
```

**Nota**: Si no configuras los secretos, el workflow generará automáticamente un APK debug.

## 🏗️ Compilar y descargar APK

### Desde GitHub Actions

1. El workflow se ejecuta automáticamente al hacer push a la rama `main`
2. También puedes ejecutarlo manualmente:
   - Ve a la pestaña **Actions** en GitHub
   - Selecciona **Build APK**
   - Haz clic en **Run workflow**
3. Una vez completado:
   - Haz clic en la ejecución del workflow
   - Baja hasta la sección **Artifacts**
   - Descarga `app-release-apk` o `app-debug-apk`

### Localmente

```bash
# Debug
./gradlew assembleDebug

# Release (requiere secrets configurados)
./gradlew assembleRelease
```

Los APK se generan en:
- `app/build/outputs/apk/debug/app-debug.apk`
- `app/build/outputs/apk/release/app-release.apk`

## 🎨 Paleta de colores

La app usa exclusivamente tonos naranjas:

| Color | Hex | Uso |
|-------|-----|-----|
| Naranja oscuro | `#af5700` | Acento fuerte |
| Naranja principal | `#ffa333` | Primario modo claro |
| Naranja claro | `#ffba6a` | Secundario teen |
| Crema | `#ffe8cd` | Fondo teen |
| Oscuro fondo | `#1E1E2E` | Fondo modo oscuro |

## 📄 Licencia

Este proyecto es de código abierto para fines educativos.

---

**Conecta2** - Enseñando ciberseguridad de forma divertida y segura 🧡
