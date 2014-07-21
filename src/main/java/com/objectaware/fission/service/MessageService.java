package com.objectaware.fission.service;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.Message;
import com.objectaware.fission.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    private static final int FEED_LAST_MESSAGES = 20; // TODO extract to configuration file

    public Iterable<Message> findAll() {
        return messageRepository.findAll(new Sort(Sort.Direction.DESC, "createdAt"));
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public Message find(Long id) {
        return messageRepository.findOne(id);
    }

    public void delete(Long id) {
        messageRepository.delete(id);
    }

    public List<Message> findLastMessagesByFeed(Feed feed) {
        return messageRepository.findLastMessagesByDate(feed.getId(), new PageRequest(0, FEED_LAST_MESSAGES));
    }
}
