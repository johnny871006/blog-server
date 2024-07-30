package com.johnny.blogserver.service;

import com.johnny.blogserver.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    //依據tagId得到tagName(admin的editTag)
    Tag getTag(Long id);

    //新增標籤時檢查tag名稱是否已經有
    Tag getTagName(String name);

    //tag列表
    Page<Tag> listTag(Pageable pageable);

    //blogInput頁面的tag下拉選單
    List<Tag> listTag();

    //編輯文章內容時的tag轉成list，tag1,tag2,tag3...
    List<Tag> listTag(String ids);

    //顯示在index頁面的標籤數量及如何排序
    List<Tag> listTagTop(Integer size);

//    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);
}
