package com.johnny.blogserver.service;

import com.johnny.blogserver.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    Type saveTyp(Type type);

    Type getType(Long id);

    Type getTypeName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

//    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
