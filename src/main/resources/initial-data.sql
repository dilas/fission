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

INSERT INTO message (id, feed_id, title, body) VALUES (4, 7, 'Nova versao', 'Versao nova do aplicativo');


ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
