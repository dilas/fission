package com.objectaware.fission.web.ext;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.Message;
import com.objectaware.fission.service.FeedService;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedGeneratorView extends AbstractRssFeedView {
    @Autowired
    private FeedService feedService;

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        Feed requestedFeed = feedService.findByIdentifier((String) model.get("identifier"));

        feed.setTitle(requestedFeed.getName());
        feed.setFeedType("rss_2.0");
        feed.setDescription(requestedFeed.getDescription());
        feed.setLink("http://localhost:8080/fission-1.0.0-SNAPSHOT/rss/" + requestedFeed.getIdentifier() + ".xml");

        super.buildFeedMetadata(model, feed, request);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<Item> buildFeedItems(Map<String, Object> stringObjectMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        List<Message> messageList = (List<Message>) stringObjectMap.get("messageList");
        List<Item> items = new ArrayList<Item>(messageList.size());

        for(Message message: messageList) {
            Item item = new Item();

            Content content = new Content();
            content.setType("text/plain");
            content.setValue(message.getBody());

            item.setContent(content);

            item.setTitle(message.getTitle());

            item.setLink("http://localhost:8080/fission-1.0.0-SNAPSHOT/message/" + message.getId());
            item.setPubDate(message.getCreatedAt());

            items.add(item);
        }

        return items;
    }
}
