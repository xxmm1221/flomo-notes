# 🚀 GitHub在线构建APK指南

## 📝 步骤1：准备GitHub账户

1. 访问：https://github.com
2. 点击"Sign up"创建账户（如果还没有）
3. 登录到GitHub

## 📤 步骤2：创建新仓库

1. 点击右上角的"+"按钮 → "New repository"
2. 填写：
   - Repository name: `flomo-notes`
   - Description: `类似flomo的简洁笔记应用`
   - 选择 Public（这样可以使用免费的GitHub Actions）
   - ✅ 勾选 "Add a README file"
3. 点击"Create repository"

## 📁 步骤3：上传项目文件

### 方法A：使用Web界面（推荐新手）

1. 在你的新仓库页面，点击"uploading an existing file"
2. 将整个 `FlomoNotes` 文件夹的所有文件拖拽到页面上，或点击"choose your files"
3. **重要文件列表**（确保都上传了）：
   ```
   📁 app/                    # 整个app文件夹
   📄 build.gradle
   📄 settings.gradle
   📄 gradle.properties
   📄 gradlew
   📄 gradlew.bat
   📁 gradle/wrapper/        # wrapper文件夹
   📁 .github/workflows/     # 包含build.yml
   📄 README.md
   ```
4. 在页面底部填写提交信息：
   - Title: "初始提交：Flomo笔记应用"
   - Description: "添加完整的Android项目文件"
5. 点击"Commit changes"

### 方法B：使用Git命令（如果熟悉）

```bash
cd FlomoNotes
git init
git add .
git commit -m "初始提交：Flomo笔记应用"
git branch -M main
git remote add origin https://github.com/你的用户名/flomo-notes.git
git push -u origin main
```

## 🔨 步骤4：触发构建

文件上传完成后：

1. **GitHub Actions会自动运行**（可能需要1-2分钟启动）
2. 在仓库页面点击 **"Actions"** 标签
3. 你会看到构建任务正在运行
4. 等待构建完成（通常5-10分钟）

## 📱 步骤5：下载APK

构建成功后：

1. 在Actions页面，点击最新的构建任务
2. 滚动到页面底部找到 **"Artifacts"** 部分
3. 点击 **"flomo-notes-debug.apk"** 下载
4. 解压下载的zip文件，得到 `app-debug.apk`

## 🎯 如果构建失败

如果看到红色的❌标记：
1. 点击失败的构建任务
2. 查看错误日志
3. 通常是文件上传不完整，重新上传缺失的文件

## ⚡ 后续更新

以后如果要修改代码：
1. 编辑仓库中的文件
2. 提交更改
3. GitHub会自动重新构建新的APK

---

## 🆘 需要帮助？

如果在任何步骤遇到问题，请告诉我：
- 你遇到的具体错误信息
- 进行到了哪一步
- 我会立即帮你解决

**准备好了吗？让我们开始吧！** 🚀