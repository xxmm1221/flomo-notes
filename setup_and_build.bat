@echo off
echo ========================================
echo Flomo Notes APK 构建脚本
echo ========================================
echo.

echo 1. 检查Java环境...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [错误] 未找到Java环境
    echo 请先下载并安装 Java 8 或更高版本
    echo 下载地址: https://www.oracle.com/java/technologies/javase-downloads.html
    echo.
    pause
    exit /b 1
) else (
    echo [成功] Java环境已安装
)

echo.
echo 2. 检查Android SDK...
if not exist "%ANDROID_HOME%\platform-tools" (
    if not exist "%LOCALAPPDATA%\Android\Sdk\platform-tools" (
        echo [错误] 未找到Android SDK
        echo 请安装Android Studio或手动配置Android SDK
        echo 下载地址: https://developer.android.com/studio
        echo.
        pause
        exit /b 1
    ) else (
        set ANDROID_HOME=%LOCALAPPDATA%\Android\Sdk
        echo [成功] 找到Android SDK
    )
) else (
    echo [成功] Android SDK已配置
)

echo.
echo 3. 开始构建APK...
if exist "gradlew.bat" (
    call gradlew.bat assembleDebug
) else (
    echo [错误] 找不到gradlew.bat
    echo 请确保在项目根目录运行此脚本
    pause
    exit /b 1
)

echo.
if %errorlevel% equ 0 (
    echo [成功] APK构建完成！
    echo APK位置: app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 现在你可以将APK文件传输到手机并安装
    echo 记得在手机设置中允许安装未知来源的应用
) else (
    echo [失败] APK构建失败，请检查错误信息
)

echo.
pause