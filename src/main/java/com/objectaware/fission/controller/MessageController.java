package com.objectaware.fission.controller;

import com.objectaware.fission.model.FeedType;
import com.objectaware.fission.model.Message;
import com.objectaware.fission.service.FeedService;
import com.objectaware.fission.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private FeedService feedService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("messageList", messageService.findAll());
        return "message/index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Message newMessage(ModelMap model) {
        populateViewModel(model, null);
        return new Message();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid Message message, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            populateViewModel(model, message.getId());
            return "message/new";
        }
        messageService.save(message);
        return "redirect:/message/" + message.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, ModelMap model) {
        model.addAttribute("message", messageService.find(id));
        return "message/show";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap model) {
        populateViewModel(model, id);
        return "message/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid Message message, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            populateViewModel(model, message.getId());
            return "message/edit";
        }
        messageService.save(message);
        return "redirect:/message/" + message.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        messageService.delete(id);
    }


    private void populateViewModel(ModelMap model, Long id) {
        if (id != null) {
            model.addAttribute("message", messageService.find(id));
        }
        model.addAttribute("feedList", feedService.findAll(FeedType.SIMPLE));
    }
}
