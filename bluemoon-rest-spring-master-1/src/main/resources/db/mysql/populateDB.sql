INSERT IGNORE INTO types VALUES (1, 'Refrigerator');
INSERT IGNORE INTO types VALUES (2, 'Television');
INSERT IGNORE INTO types VALUES (3, 'Air-conditioning');
INSERT IGNORE INTO types VALUES (4, 'Dryer');
INSERT IGNORE INTO types VALUES (5, 'Oven');
INSERT IGNORE INTO types VALUES (6, 'Fan');

INSERT IGNORE INTO customers VALUES (1, 'Adam', 'Jones', '541 Praire St.', 'Northridge', '6085551023');
INSERT IGNORE INTO customers VALUES (2, 'Betty', 'Washington', '443 Etiwanda Ave.', 'North Hills', '6085551749');
INSERT IGNORE INTO customers VALUES (3, 'Edward', 'Longbottom', '4321 Superior St.', 'Woodland Hills', '6085558763');
INSERT IGNORE INTO customers VALUES (4, 'Jimmy', 'Nesham', '412 Yolanda St.', 'Van Nuys', '6085553198');

INSERT IGNORE INTO appliances VALUES (1, 'Samsung LED TV 223U', '2019-09-07', 1, 2);
INSERT IGNORE INTO appliances VALUES (2, 'Whirlpool Refrigerator 9880', '2018-08-06', 2, 1);
INSERT IGNORE INTO appliances VALUES (3, 'BlueDart AC 1521L', '2010-04-17', 3, 3);
INSERT IGNORE INTO appliances VALUES (4, 'Hitachi Dryer G431', '2017-03-07', 4, 4);
INSERT IGNORE INTO appliances VALUES (5, 'LG Oven DD23', '2016-11-30', 1, 5);
INSERT IGNORE INTO appliances VALUES (6, 'GE Fan', '2015-01-20', 2, 6);
INSERT IGNORE INTO appliances VALUES (7, 'LG LED TV O9878', '2014-09-04', 3, 2);
INSERT IGNORE INTO appliances VALUES (8, 'GE Refrigerator 443L', '2019-09-04', 4, 1);

INSERT IGNORE INTO drops VALUES (1, 1, '2020-10-02', 'screen flickering');
INSERT IGNORE INTO drops VALUES (2, 2, '2020-11-03', 'no power');
INSERT IGNORE INTO drops VALUES (3, 3, '2020-09-04', 'not cooling');
INSERT IGNORE INTO drops VALUES (4, 4, '2020-11-05', 'loud noise');

