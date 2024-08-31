package org.gucardev.genericexample.service;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;

import java.util.List;

public interface CardService extends GenericService<Card, CardDto, Long> {
    List<CardDto> findAllByAccountId(Long customerId);
}
