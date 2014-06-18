package com.objectaware.fission.repository;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.FeedType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedRepository extends PagingAndSortingRepository<Feed, Long> {
    List<Feed> findByName(@Param("name") String name);
    Feed findByIdentifier(@Param("identifier") String identifier);

    @Modifying
    @Query(value = "delete from FeedGroup fg where fg.groupFeedId = :feedId", nativeQuery = true)
    void deleteGroup(@Param("feedId") Long feedId);

    @Query("from Feed f where f.feedType = :feedType")
    List<Feed> findAll(@Param("feedType") int feedType);
}
