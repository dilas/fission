package com.objectaware.fission.service;

import com.objectaware.fission.model.Message;
import com.objectaware.fission.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

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
}
