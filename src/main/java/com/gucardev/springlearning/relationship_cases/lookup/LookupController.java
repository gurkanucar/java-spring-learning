package com.gucardev.springlearning.relationship_cases.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lookups")
@Slf4j
public class LookupController {

    @Autowired
    private LookupRepository lookupRepository;

    @GetMapping
    public List<LookupDto> getAllLookups() {
        return lookupRepository.findAll().stream()
                .map(LookupMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public LookupDto createLookup(@RequestBody LookupDto lookupDto) {
        Lookup lookup = LookupMapper.INSTANCE.toEntity(lookupDto);
        lookup = lookupRepository.save(lookup);
        log.info("created");
        return LookupMapper.INSTANCE.toDto(lookup);
    }

    @GetMapping("/{id}")
    public LookupDto getLookupById(@PathVariable Long id) {
        Lookup lookup = lookupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lookup not found with id " + id));
        return LookupMapper.INSTANCE.toDto(lookup);
    }


    @PutMapping("/{id}")
    public LookupDto updateLookup(@PathVariable Long id, @RequestBody LookupDto updatedLookupDto) {
        Lookup lookup = lookupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lookup not found with id " + id));
        LookupMapper.INSTANCE.updateFromDtoWithNullCheck(updatedLookupDto, lookup);
        lookup = lookupRepository.save(lookup);
        return LookupMapper.INSTANCE.toDto(lookup);
    }

    @DeleteMapping("/{id}")
    public void deleteLookup(@PathVariable Long id) {
        lookupRepository.deleteById(id);
    }
}
