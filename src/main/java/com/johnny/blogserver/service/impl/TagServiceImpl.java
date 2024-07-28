package com.johnny.blogserver.service.impl;

import com.johnny.blogserver.NotFoundException;
import com.johnny.blogserver.dao.TagRepository;
import com.johnny.blogserver.model.Tag;
import com.johnny.blogserver.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return findOne(id);
    }

    @Transactional
    @Override
    public Tag getTagName(String name) {

        return tagRepository.getTagByName(name);

    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Transactional
    @Override
    public List<Tag> listTag(String ids) { //ids:1,2,3
        return tagRepository.findAllById(converToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"name");
        Pageable pageable = PageRequest.of(0, size,sort);
        return tagRepository.findTop(pageable);
    }

//    @Transactional
//    @Override
//    public Tag updateTag(Long id, Tag tag) {
//
//        Tag t = findOne(id);
//
//        if (t == null) {
//            throw new NotFoundException("不存在此標籤");
//        }
//        BeanUtils.copyProperties(tag, t);
//
//        return tagRepository.save(t);
//    }

    @Transactional
    @Override
    public void deleteTag(Long id) {

        tagRepository.delete(findOne(id));
    }

    private Tag findOne(Long id) {

        Tag tag = new Tag();
        tag.setId(id);
        Example<Tag> example = Example.of(tag);
        Optional<Tag> optional = tagRepository.findOne(example);

        if (optional.isPresent()) {
            tag = optional.get();
            return tag;
        } else {
            return null;
        }
    }

    //ids:1,2,3
    private List<Long> converToList(String ids) {
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null  ) {
            String[] idArray = ids.split(",");
            for(String id : idArray) {
                list.add(Long.valueOf(id));
            }
        }
        return list;
    }
}
