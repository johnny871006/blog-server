package com.johnny.blogserver.service;

import com.johnny.blogserver.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    Type saveTyp(Type type);

    Type getType(Long id);

    Type getTypeName(String name);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
