package com.objectaware.fission.controller;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.Message;
import com.objectaware.fission.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {
    @Autowired
    private FeedService feedService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/rss/{identifier}.xml")
    public ModelAndView feed(@PathVariable String identifier) {
        Feed feed = feedService.findByIdentifier(identifier);
        List<Message> messageList = new ArrayList<Message>();


        ModelAndView mav = new ModelAndView();
        mav.setViewName("feedGeneratorView");

        messageList.addAll(feed.getMessages());

        for (Feed childFeed : feed.getFeeds()) {
            messageList.addAll(childFeed.getMessages());
        }

        mav.addObject("messageList", messageList);

        return mav;
    }
}
