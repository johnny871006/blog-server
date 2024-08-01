# 這是一個使用Spring Boot 製作一個小型的個人Blog

## 使用技術：
- Spring Boot版本：3.3.2
- SpringMVC(Controller-Service-Dao三層式架構)
- Spring JPA
- MySQL版本：8.0.38
- 前端 : Semantic ui、Thymeleaf

## 實作內容：
- 管理員
  - 管理員可新增/查詢/修改/刪除(CRUD)，文章、分類、標籤
- 帳號
  - 登入 (MD5加密密碼)
  - 登出
- 非管理員
  - 瀏覽文章、分類、標籤、歷史文章、搜尋文章、發文者相關訊息、以及留言

## 功能介紹

 ### 管理員頁面
 - 文章列表 (可列出新增過的blog文章，可依據名稱和分類及是否推薦進行篩選)
 - 分類列表 (可列出新增過的分類)
 - 標籤列表 (可列出新增過的標籤)
