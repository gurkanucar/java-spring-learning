package org.gucardev.springscopes;


import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope("request")
@Component
@Data
public class RequestScope {
    private String value;
}