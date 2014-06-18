package com.objectaware.fission.controller;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.Message;
import com.objectaware.fission.service.FeedService;
import com.objectaware.fission.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void postMessage(@RequestParam HashMap<String, String> params) {
        Feed feed = feedService.findByIdentifier(params.get("feedIdentifier"));

        if (feed == null) {
            throw new IllegalArgumentException("Invalid feed identifier");
        }

        Message newMessage = new Message();
        newMessage.setFeed(feed);
        newMessage.setTitle(params.get("title"));
        newMessage.setBody(params.get("body"));

        messageService.save(newMessage);
    }
}
