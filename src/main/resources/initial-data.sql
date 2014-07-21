INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (1, 2, 'Geral', 'geral', 'Feed Geral', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (2, 1, 'Arquitetura', 'arquitetura', 'Feed Arquitetura GEARQ', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (3, 2, 'Framework', 'framework', 'Feed Frameworks', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (4, 1, 'Sistema A', 'sistema-a', 'Feed Sistema A', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (5, 1, 'Sistema B', 'sistema-b', 'Feed Sistema B', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (6, 1, 'Sistema C', 'sistema-c', 'Feed Sistema C', true);
INSERT INTO feed (id, feedType, name, identifier, description, active) VALUES (7, 1, 'Nova Versao Aplicativos', 'nova-versao', 'Feed de novas versoes dos aplicativos no Sicoob', true);

INSERT INTO feedGroup (groupFeedId, feedId) VALUES (1, 4);
INSERT INTO feedGroup (groupFeedId, feedId) VALUES (1, 5);
INSERT INTO feedGroup (groupFeedId, feedId) VALUES (1, 6);

INSERT INTO message (id, feed_id, title, body) VALUES (1, 4, 'Titulo Mensagem 1', 'Mensagem 1');
INSERT INTO message (id, feed_id, title, body) VALUES (2, 5, 'Titulo Mensagem 2', 'Mensagem 2');
INSERT INTO message (id, feed_id, title, body) VALUES (3, 6, 'Titulo Mensagem 3', 'Mensagem 3');

INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (4, 7, 'Nova versao', 'Versao nova do aplicativo', '2014-01-01 01:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (5, 7, 'Aplicativo A', 'Versao nova do aplicativo', '2014-02-10 19:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (6, 7, 'Aplicativo B', 'Versao nova do aplicativo', '2014-02-10 21:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (7, 7, 'Aplicativo C', 'Versao nova do aplicativo', '2014-02-11 11:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (8, 7, 'Aplicativo D', 'Versao nova do aplicativo', '2014-03-06 12:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (9, 7, 'Aplicativo E', 'Versao nova do aplicativo', '2014-03-12 09:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (10, 7, 'Aplicativo F', 'Versao nova do aplicativo', '2014-03-13 18:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (11, 7, 'Aplicativo G', 'Versao nova do aplicativo', '2014-04-09 21:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (12, 7, 'Aplicativo H', 'Versao nova do aplicativo', '2014-05-17 09:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (13, 7, 'Aplicativo I', 'Versao nova do aplicativo', '2014-06-22 20:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (14, 7, 'Aplicativo J', 'Versao nova do aplicativo', '2014-07-10 19:00:00');
INSERT INTO message (id, feed_id, title, body, createdAt) VALUES (15, 7, 'Aplicativo L', 'Versao nova do aplicativo', '2014-07-20 11:00:00');

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
