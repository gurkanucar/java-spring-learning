package org.gucardev.genericexample.dto;

import lombok.Getter;
import lombok.Setter;
import org.gucardev.genericexample.entity.Account;

@Getter
@Setter
public class CardDto extends BaseDto {

    private String name;
    private Account account;

    @Getter
    @Setter
    public static class AccountDto extends BaseDto {

        private String name;
    }

}
