package com.objectaware.fission.controller;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {
    @Autowired
    private FeedService feedService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/rss/{identifier}.xml")
    public ModelAndView feed(@PathVariable String identifier) {
        Feed feed = feedService.findByIdentifier(identifier);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("feedGeneratorView");
        mav.addObject("messageList", feed.getMessages());

        return mav;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login-failed.html", method = RequestMethod.GET)
    public String loginFailed() {
        return "login";
    }
}
