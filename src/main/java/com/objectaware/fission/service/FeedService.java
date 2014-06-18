package com.objectaware.fission.service;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.FeedType;
import com.objectaware.fission.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeedService {
    @Autowired
    private FeedRepository feedRepository;

    public Iterable<Feed> findAll() {
        return feedRepository.findAll();
    }

    public Feed save(Feed feed) {
        return feedRepository.save(feed);
    }

    public Feed find(Long id) {
        return feedRepository.findOne(id);
    }

    public Feed findWithGroups(Long id) {
        Feed feed = feedRepository.findOne(id);
        feed.getFeeds().size();
        return feed;
    }

    public Feed findByIdentifier(String identifier) {
        Feed feed = feedRepository.findByIdentifier(identifier);
        feed.getMessages().size();
        feed.getFeeds().size();

        for (Feed childFeed : feed.getFeeds()) {
            childFeed.getMessages().size();
        }

        return feed;
    }

    public List<Feed> findAll(FeedType feedType) {
        return feedRepository.findAll(feedType.getId());
    }

    public void delete(Long id) {
        feedRepository.delete(id);
    }

    public void updateGroup(Feed groupFeed, List<Feed> feedList) {
        feedRepository.deleteGroup(groupFeed.getId());

        groupFeed.setFeeds(feedList);

        feedRepository.save(groupFeed);
    }
}
