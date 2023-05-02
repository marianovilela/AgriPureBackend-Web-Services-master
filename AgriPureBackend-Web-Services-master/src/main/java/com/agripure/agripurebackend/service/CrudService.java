package com.agripure.agripurebackend.service;

import java.util.Optional;
import java.util.List;

public interface CrudService<T> {
    T save(T t) throws Exception;
    void delete(Long id) throws Exception;
    List<T> getAll() throws Exception;
    Optional<T> getById(Long id) throws Exception;
}
