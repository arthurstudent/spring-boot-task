package com.task.springbootuniversity.service;

import java.util.List;

public interface LectorService {

    List<String> findLectorsNamesByKeyword(String keyword);
}
