package org.gucardev.genericexample.service.impl;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;
import org.gucardev.genericexample.mapper.CardMapper;
import org.gucardev.genericexample.repository.CardRepository;
import org.gucardev.genericexample.service.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends GenericServiceImpl<Card, CardDto, Long> implements CardService {
    public CardServiceImpl(CardRepository repository, CardMapper mapper) {
        super(repository, mapper);
    }
}
