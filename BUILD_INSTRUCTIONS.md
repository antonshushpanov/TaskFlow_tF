# Инструкции по сборке TaskFlow для Android 14.0 (API 34)

## Требования

Для сборки приложения необходимо:

1. **JDK 11 или выше** (рекомендуется JDK 17)
   - Скачать можно с [Oracle](https://www.oracle.com/java/technologies/downloads/) или [OpenJDK](https://adoptium.net/)
   - Установить и настроить переменную окружения `JAVA_HOME`

2. **Android SDK с API 34** (Android 14.0)
   - Рекомендуется установить через Android Studio

## Настройка окружения

### 1. Установка JDK

После установки JDK 11+:

**Windows:**
```
1. Откройте "Переменные среды"
2. Добавьте переменную JAVA_HOME, указывающую на папку JDK (например: C:\Program Files\Java\jdk-17)
3. Добавьте %JAVA_HOME%\bin в переменную Path
```

### 2. Проверка установки

Откройте PowerShell и выполните:
```powershell
java -version
# Должна быть версия 11 или выше
```

### 3. Сборка приложения

После настройки JDK выполните в корне проекта:

```powershell
.\gradlew.bat assembleDebug
```

Или через Android Studio:
1. Откройте проект в Android Studio
2. Build → Make Project (Ctrl+F9)
3. Для установки на устройство: Run → Run 'app' (Shift+F10)

## Настройки для Pixel 5 (Android 14.0)

Проект уже настроен для:
- **minSdk**: 34 (Android 14.0)
- **targetSdk**: 34 (Android 14.0)
- **compileSdk**: 34 (Android 14.0)

Для запуска на эмуляторе Pixel 5:
1. Откройте Android Studio
2. Tools → Device Manager
3. Create Device → Pixel 5
4. Выберите систему: API 34 (Android 14.0)
5. Запустите эмулятор

## Альтернатива: Сборка через Android Studio

Если у вас установлена Android Studio:

1. Откройте Android Studio
2. File → Open → Выберите папку проекта D:\ttF
3. Дождитесь синхронизации Gradle
4. Build → Make Project
5. Run → Run 'app' (выберите Pixel 5 эмулятор или подключенное устройство)

Android Studio автоматически использует встроенный JDK.

