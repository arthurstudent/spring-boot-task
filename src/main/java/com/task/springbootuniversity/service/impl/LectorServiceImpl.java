package com.task.springbootuniversity.service.impl;

import com.task.springbootuniversity.exceptions.LectorNotFoundException;
import com.task.springbootuniversity.io.repository.LectorRepository;
import com.task.springbootuniversity.service.LectorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LectorServiceImpl implements LectorService {

    private final LectorRepository lectorRepository;

    public LectorServiceImpl(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public List<String> findLectorsNamesByKeyword(String keyword) {

        if (keyword == null)
            throw new LectorNotFoundException("Lector can't be found as keyword is null");

        if (keyword.isBlank()) {
            return new ArrayList<>();
        }

        return Optional.ofNullable(lectorRepository.findByTemplate(keyword)).orElseGet(ArrayList::new);
    }
}
