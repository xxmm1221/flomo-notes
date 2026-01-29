# 安装Java环境指南

## 🔥 快速安装Java

### 方法1：下载OpenJDK（推荐）
1. 访问：https://adoptium.net/
2. 选择：
   - Version: 17 (LTS)
   - Operating System: Windows
   - Architecture: x64
3. 点击"Latest release"下载
4. 运行安装程序，按默认选项安装
5. 安装完成后重启命令行

### 方法2：下载Oracle JDK
1. 访问：https://www.oracle.com/java/technologies/javase-downloads.html
2. 下载 JDK 17 或 JDK 11
3. 运行安装程序
4. 重启命令行

## 📋 验证安装

安装完成后，在新的命令行窗口中运行：
```bash
java -version
javac -version
```

应该看到类似输出：
```
java version "17.0.x" 2023-xx-xx LTS
Java(TM) SE Runtime Environment (build 17.0.x+xx-xxx)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx-xxx, mixed mode, sharing)
```

## 🎯 如果还是没有找到java命令

### 手动设置环境变量
1. 找到Java安装路径（通常在 C:\Program Files\Java\ 或 C:\Program Files\Eclipse Adoptium\）
2. 右键"此电脑" → "属性" → "高级系统设置"
3. 点击"环境变量"
4. 在"系统变量"中点击"新建"：
   - 变量名：JAVA_HOME
   - 变量值：Java安装路径（如：C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot）
5. 找到"Path"变量，点击"编辑"，添加：%JAVA_HOME%\bin
6. 重启命令行

完成后请运行下一步：安装Android SDK