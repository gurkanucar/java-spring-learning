package org.gucardev.springscopes;


import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class SingletonScope {
    private String value;
}