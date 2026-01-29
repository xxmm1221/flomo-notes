# Flomo Notes APK 构建指南

## 🚀 快速开始 - 3种方式获得APK

### 方式1：本地构建（推荐，如果有开发环境）

#### 准备工作
1. 安装 Java 8 或更高版本
   - 下载：https://www.oracle.com/java/technologies/javase-downloads.html
   - 或者使用OpenJDK：https://adoptium.net/

2. 安装 Android Studio（推荐）
   - 下载：https://developer.android.com/studio
   - 安装时选择包含Android SDK

#### 构建步骤
```bash
# 在项目根目录(FlomoNotes)打开命令行
# Windows:
gradlew.bat assembleDebug

# Linux/Mac:
./gradlew assembleDebug
```

或者双击运行 `setup_and_build.bat` 文件

#### APK位置
构建成功后APK文件在：`app\build\outputs\apk\debug\app-debug.apk`

---

### 方式2：使用Android Studio GUI

1. 下载并安装Android Studio
2. 打开Android Studio
3. 选择 "Open an existing project"
4. 打开 FlomoNotes 文件夹
5. 等待Gradle同步完成
6. 点击菜单 `Build` → `Build Bundle(s) / APK(s)` → `Build APK(s)`
7. 构建完成后会弹出通知，点击"locate"找到APK文件

---

### 方式3：在线构建（无需本地环境）

#### 使用GitHub Actions
1. 在GitHub上创建新仓库
2. 将整个FlomoNotes文件夹上传到仓库
3. GitHub Actions会自动构建APK
4. 在仓库的Actions页面下载构建好的APK

#### 手动上传到在线构建平台
**Appetize.io Build**
- 网址：https://appetize.io/
- 上传项目zip文件
- 在线构建并下载APK

**CodeMagic**
- 网址：https://codemagic.io/
- 连接GitHub仓库
- 自动构建Android应用

**Bitrise**
- 网址：https://www.bitrise.io/
- 支持免费构建
- 可以直接上传项目

---

## 📱 安装到手机

### Android手机安装步骤
1. **开启开发者选项**
   - 设置 → 关于手机 → 连续点击"版本号"7次
   - 返回设置，找到"开发者选项"

2. **允许安装未知来源应用**
   - 设置 → 安全 → 允许安装未知来源应用
   - 或者在安装时系统会提示允许

3. **安装APK**
   - 将APK文件发送到手机（微信、QQ、邮箱、USB等）
   - 打开文件管理器找到APK文件
   - 点击安装

### 通过USB安装（需要开发环境）
```bash
# 连接手机并开启USB调试
adb install app-debug.apk
```

---

## 🛠️ 故障排除

### Java环境问题
```
错误：'java' 不是内部或外部命令
```
**解决**：安装Java并配置环境变量

### Android SDK问题
```
错误：Could not find Android SDK
```
**解决**：
1. 安装Android Studio
2. 或手动下载SDK并设置ANDROID_HOME环境变量

### 构建失败
```
错误：Execution failed for task ':app:processDebugResources'
```
**解决**：
1. 确保所有资源文件都存在
2. 清理项目：`gradlew clean`
3. 重新构建

### 权限问题（Linux/Mac）
```bash
# 给gradlew执行权限
chmod +x gradlew
```

---

## 🎯 应用特性

构建完成的APK包含以下功能：
- ✅ 快速创建和编辑笔记
- ✅ 标签管理系统
- ✅ 实时搜索功能
- ✅ 卡片式界面设计
- ✅ 本地数据存储
- ✅ Material Design风格

## 📏 APK信息
- **包名**: com.flomonotes
- **最小Android版本**: 5.0 (API 21)
- **目标Android版本**: 14 (API 34)
- **大小**: 约 2-4 MB
- **权限**: 无特殊权限需求

---

## 💡 小贴士

- 如果构建速度慢，可以在gradle.properties中添加：
  ```
  org.gradle.parallel=true
  org.gradle.configureondemand=true
  ```
- 第一次构建会下载依赖，需要联网且比较耗时
- 建议使用稳定的网络环境进行构建

---

**需要帮助？**
如果在构建过程中遇到问题，可以：
1. 检查Java和Android SDK是否正确安装
2. 确保网络连接正常（下载依赖需要）
3. 尝试清理项目后重新构建
4. 使用在线构建平台作为备选方案