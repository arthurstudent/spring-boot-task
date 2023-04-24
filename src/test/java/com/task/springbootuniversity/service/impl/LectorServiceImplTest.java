package com.task.springbootuniversity.service.impl;

import com.task.springbootuniversity.exceptions.LectorNotFoundException;
import com.task.springbootuniversity.io.repository.LectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LectorServiceImplTest {

    @InjectMocks
    private LectorServiceImpl lectorService;

    @Mock
    private LectorRepository lectorRepository;

    private final List<String> lectorList = List.of("Ivan Petrov", "Petro Ivanov");

    @Test
    void findLectorsNamesByNullKeyword() {

        assertThrows(LectorNotFoundException.class, () -> lectorService.findLectorsNamesByKeyword(null));
    }

    @Test
    void findLectorsNamesByEmptyKeyword() {
        List<String> lectorsNamesByKeyword = lectorService.findLectorsNamesByKeyword("");

        assertEquals(0, lectorsNamesByKeyword.size());
    }


    @Test
    void findLectorsNamesByKeyword() {
        when(lectorRepository.findByTemplate(anyString())).thenReturn(lectorList);
        List<String> lectorsNamesByKeyword = lectorService.findLectorsNamesByKeyword("van");

        assertEquals(2, lectorsNamesByKeyword.size());
    }
}
