package org.gucardev.genericexample.controller;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;
import org.gucardev.genericexample.service.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardRestController extends GenericRestController<Card, CardDto, Long> {

    private final CardService cardService;

    public CardRestController(CardService cardService) {
        super(cardService);
        this.cardService = cardService;
    }

    @Override
    protected List<String> getSearchableFields() {
        return List.of("name");
    }

    @GetMapping("/account/{accountId}")
    public List<CardDto> getCardsByAccountId(@PathVariable("accountId") Long accountId) {
        return cardService.findAllByAccountId(accountId);
    }
}

