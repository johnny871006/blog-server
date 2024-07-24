package com.johnny.blogserver.service.impl;

import com.johnny.blogserver.NotFoundException;
import com.johnny.blogserver.dao.TypeRepository;
import com.johnny.blogserver.model.Type;
import com.johnny.blogserver.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveTyp(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {

        Type type = findOne(id);

        return type;
    }

    @Override
    public Type getTypeName(String name) {

        System.out.println("----getTypeName前----");

        return typeRepository.getTypeByName(name);

    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {

        Type t = findOne(id);

        if (t == null) {
            throw new NotFoundException("不存在此分類");
        }
        BeanUtils.copyProperties(type, t);

        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {

        Type type = findOne(id);

        typeRepository.delete(type);
    }

    private Type findOne(Long id) {

        Type type = new Type();
        type.setId(id);
        Example<Type> example = Example.of(type);
        Optional<Type> optional = typeRepository.findOne(example);

        if (optional.isPresent()) {
            type = optional.get();
            return type;
        } else {
            return null;
        }
    }
}
