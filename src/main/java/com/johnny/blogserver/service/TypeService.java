package com.johnny.blogserver.service;

import com.johnny.blogserver.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveTyp(Type type);

    //依據typeId得到type(admin的editType)
    Type getType(Long id);

    //檢查資料庫是否已有重複名稱
    Type getTypeName(String name);

    //以建立的type清單
    Page<Type> listType(Pageable pageable);

    // blogManage的篩選分類清單、新增、編輯blog的下拉分類清單
    List<Type> listType();

    //顯示在index頁面的分類數量及如何排序
    List<Type> listTypeTop(Integer size);

//    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
