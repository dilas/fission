package com.objectaware.fission.controller;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.FeedType;
import com.objectaware.fission.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("feedList", feedService.findAll());
        return "feed/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Feed newFeed(ModelMap model) {
        populateViewModel(model, null);
        return new Feed();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid Feed feed, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            populateViewModel(model, null);
            return "feed/new";
        }
        feedService.save(feed);
        return "redirect:/feed/" + feed.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, ModelMap model) {
        model.addAttribute("feed", feedService.find(id));
        return "feed/show";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        populateViewModel(model, id);
        return "feed/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid Feed feed, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            populateViewModel(model, null);
            return "feed/edit";
        }
        feedService.save(feed);
        return "redirect:/feed/" + feed.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        feedService.delete(id);
    }

    @RequestMapping(value = "{id}/group", method = RequestMethod.GET)
    public String groups(@PathVariable Long id, ModelMap model) {
        Feed selectedGroupFeed = feedService.findWithGroups(id);
        Long[] selectedFeeds = new Long[selectedGroupFeed.getFeeds().size()];

        for (int i = 0; i < selectedGroupFeed.getFeeds().size(); i++) {
            selectedFeeds[i] = selectedGroupFeed.getFeeds().get(i).getId();
        }

        selectedGroupFeed.setSelectedFeeds(selectedFeeds);

        model.addAttribute("feed", selectedGroupFeed);
        model.addAttribute("availableSimpleFeeds", feedService.findAll(FeedType.SIMPLE));

        return "feed/group";
    }

    @RequestMapping(value = "/update/group", method = RequestMethod.POST)
    public String updateGroup(Feed feed) {
        Feed groupFeed = feedService.find(feed.getId());

        List<Feed> feedList = new ArrayList<Feed>();

        for (Long selectedId : feed.getSelectedFeeds()) {
            Feed childFeed = feedService.find(selectedId);
            feedList.add(childFeed);
        }

        feedService.updateGroup(groupFeed, feedList);

        return "redirect:/feed/" + feed.getId();
    }

    private void populateViewModel(ModelMap model, Long id) {
        if (id != null) {
            model.addAttribute("feed", feedService.find(id));
        }
        model.addAttribute("feedTypeList", FeedType.values());
    }
}
