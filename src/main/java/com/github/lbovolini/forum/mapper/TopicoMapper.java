package com.github.lbovolini.forum.mapper;

import com.github.lbovolini.forum.model.Topico;
import com.github.lbovolini.forum.request.TopicoRequest;
import com.github.lbovolini.forum.response.TopicoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TopicoMapper {

    TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);

    Topico toModel(TopicoRequest topicoRequest);

    TopicoResponse toResponse(Topico topico);
}
