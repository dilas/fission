INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (1, 1, 'Feed 1', 'feed1', 'Feed 1', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (2, 1, 'Feed 2', 'feed2', 'Feed 2', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (3, 1, 'Feed 3', 'feed3', 'Feed 3', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (4, 2, 'Group Feed', 'group-feed', 'Group Feed', true);

INSERT INTO feedGroup (groupFeedId, feedId) VALUES (4, 1);
INSERT INTO feedGroup (groupFeedId, feedId) VALUES (4, 2);

INSERT INTO message (id, feed_id, title, body) VALUES (1, 1, 'Message Title 1', 'Message 1');
INSERT INTO message (id, feed_id, title, body) VALUES (2, 2, 'Message Title 2', 'Message 2');
INSERT INTO message (id, feed_id, title, body) VALUES (3, 3, 'Message Title 3', 'Message 3');

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
