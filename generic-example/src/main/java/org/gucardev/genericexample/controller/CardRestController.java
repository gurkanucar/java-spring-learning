package org.gucardev.genericexample.controller;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;
import org.gucardev.genericexample.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardRestController extends GenericRestController<Card, CardDto, Long> {

    @Autowired
    public CardRestController(CardService service) {
        super(service);
    }

    @Override
    protected List<String> getSearchableFields() {
        return List.of("name");
    }

}
