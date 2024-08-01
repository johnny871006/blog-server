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

### 網站圖片都先使用picsum的隨機圖片 : https://picsum.photos

 ### 管理員頁面
 - 文章列表 (可列出新增過的blog文章，可依據名稱和分類及是否推薦進行篩選、也可對文章狀態選擇保存草稿或發佈)
   
   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/adminBlog.png" height="500em" width="850em" />
   
 - 新增或編輯文章(可依據發佈者選擇是否要顯示作者訊息、留言、按讚、推薦，也可對文章狀態選擇保存草稿或發佈)
   
   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/addOrEditBlog.png" height="500em" width="850em" />
   
 - 分類列表 (可列出新增過的分類)
   
   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/adminType.png" height="500em" width="850em" />
   
 - 標籤列表 (可列出新增過的標籤)
   
   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/adminTag.png" height="500em" width="850em" />

### 非管理員頁面
- 首頁(排序由最新文章最上面，每個文章皆會顯示觀看人數、發佈時間)

  <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/index.png" height="500em" width="850em" />

- 文章詳情(文章內會顯示創建時間、觀看人數、也可依據發佈者選擇是否要顯示作者訊息、留言、按讚、推薦)
  -  若是管理員回覆留言，會特別顯示
  
  <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/blogContent1.png" height="500em" width="750em" />
  <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/blogContent2.png" height="500em" width="750em" />  

- 分類(排序由最新分類最上面，也會顯示每個分類有幾個文章使用中)

   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/type.png" height="500em" width="850em" />

- 標籤(排序由最新標籤最上面，也會顯示每個標籤有幾個文章使用中)

  <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/tag.png" height="500em" width="850em" />
  
- 歷史文章(排序由文章更新日期由醉心到最舊)

   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/archives.png" height="500em" width="850em" />

- 關於我(我的一些簡單個人訊息)

   <img src="https://github.com/johnny871006/blog-server/blob/main/rmImg/about.png" height="500em" width="850em" />
