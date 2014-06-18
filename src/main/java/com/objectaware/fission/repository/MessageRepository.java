package com.objectaware.fission.repository;

import com.objectaware.fission.model.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    List<Message> findAll(Sort sort);
}
