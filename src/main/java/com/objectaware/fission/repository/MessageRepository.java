package com.objectaware.fission.repository;

import com.objectaware.fission.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long>, JpaSpecificationExecutor {
    @Query(value = "select m from Message m where m.feed.id = :feedId order by m.createdAt desc")
    List<Message> findLastMessagesByDate(@Param("feedId") Long feedId, Pageable pageable);
}
