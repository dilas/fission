package com.objectaware.fission.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Feed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Integer feedType = 1;

    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String identifier;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable=false, insertable=false, columnDefinition = "timestamp default now()")
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private Boolean active;

    @ManyToMany
    @JoinTable(name = "FeedGroup", joinColumns = { @JoinColumn(name = "groupFeedId", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "feedId", referencedColumnName = "id", nullable = false)})
    private List<Feed> feeds;

    @ManyToMany(mappedBy = "feeds")
    private List<Feed> groupFeeds;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<Message> messages;

    @Transient
    private Long[] selectedFeeds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        if (identifier != null) {
            identifier = identifier.toLowerCase();
        }

        this.identifier = identifier;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return (this.updatedAt != null ? this.updatedAt : new Date());
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public FeedType getFeedType() {
        return FeedType.getType(this.feedType);
    }

    public void setFeedType(FeedType feedType) {
        if (feedType == null) {
            this.feedType = null;
        } else {
            this.feedType = feedType.getId();
        }
    }

    public List<Feed> getGroupFeeds() {
        return groupFeeds;
    }

    public void setGroupFeeds(List<Feed> groupFeeds) {
        this.groupFeeds = groupFeeds;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public Long[] getSelectedFeeds() {
        return selectedFeeds;
    }

    public void setSelectedFeeds(Long[] selectedFeeds) {
        this.selectedFeeds = selectedFeeds;
    }
}
