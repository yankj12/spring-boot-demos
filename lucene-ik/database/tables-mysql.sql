CREATE TABLE
	food
	(
		foodid INT NOT NULL AUTO_INCREMENT,
		foodname VARCHAR(120) DEFAULT '',
		price DOUBLE,
		imagepath VARCHAR(100) DEFAULT '',
		PRIMARY KEY (foodid)
	)
	ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO food (foodid, foodname, price, imagepath) VALUES (1, '青椒肉丝', 13.0, 'F://test/12.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (2, '青椒炒鸡蛋', 20.0, 'F://test/13.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (3, '西红柿炒鸡蛋', 20.0, 'F://test/14.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (4, '红烧茄子', 20.0, 'F://test/15.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (5, '红烧肉', 20.0, 'F://test/16.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (6, '凉拌西红柿', 13.0, 'F://test/17.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (7, '凉拌黄瓜', 13.0, 'F://test/18.jpg');
INSERT INTO food (foodid, foodname, price, imagepath) VALUES (8, '小鸡炖蘑菇', 30.0, 'F://test/19.jpg');
