package com.gucardev.springlearning.relationship_cases.lookup;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LookupDto {
    private Long id;
    private Map<String, String> payloads;
}
