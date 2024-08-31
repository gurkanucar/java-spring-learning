package org.gucardev.genericexample.service.impl;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;
import org.gucardev.genericexample.mapper.CardMapper;
import org.gucardev.genericexample.repository.CardRepository;
import org.gucardev.genericexample.service.CardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl extends GenericServiceImpl<Card, CardDto, Long> implements CardService {

    private final CardRepository repository;
    private final CardMapper mapper;

    public CardServiceImpl(CardRepository repository, CardMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<CardDto> findAllByAccountId(Long customerId) {
        return repository.findAllByAccountId(customerId).stream().map(mapper::toDto).toList();
    }
}
