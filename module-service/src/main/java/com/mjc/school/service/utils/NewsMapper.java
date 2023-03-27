package com.mjc.school.service.utils;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    NewsDTOResponse mapNews(News news);

    @Mapping(source = "authorId", target = "author.id")
    News unmapNewsReq(NewsDTORequest request);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "authorName", target = "author.name")
    News unmapNewsResp(NewsDTOResponse resp);
}
