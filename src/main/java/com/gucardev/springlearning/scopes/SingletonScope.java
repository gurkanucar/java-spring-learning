package com.gucardev.springlearning.scopes;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class SingletonScope {
    private String value;
}