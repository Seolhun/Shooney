INSERT INTO `TB_USER_PROFILE` (`USER_PROFILE_ID`, `USER_PROFILE_TYPE`)
VALUES
	(1,'GUEST'),
	(2,'UESR'),
	(3,'STAR'),
	(4,'SUPERADMIN');


INSERT INTO `TB_MENU` (`MENU_ID`, `MENU_NAME`, `MENU_TYPE`, `MENU_DEPTH`, `MENU_ORDER`, `MENU_PARENT_ID`, `MENU_URL`, `MENU_CREATED_DATE`, `MENU_CREATED_BY`, `MENU_DEL_FLAG`)
VALUES
	(1,'Home','admin',1,1,NULL,'','2017-04-16 16:59:45','shooney','N'),
	(2,'User','admin',1,2,NULL,'admin/user/list','2017-04-16 16:59:45','shooney','N'),
	(51,'Home','normal',1,1,NULL,'','2017-04-16 16:59:45','shooney','N'),
	(52,'Blog','normal',1,2,NULL,'blog/list','2017-04-16 16:59:45','shooney','N'),
	(53,'News','normal',1,3,NULL,'news/list','2017-04-16 16:59:45','shooney','N'),
	(54,'Music','normal',1,4,NULL,'music/list','2017-04-16 16:59:45','shooney','Y'),
	(59,'My Infomation','normal',1,9,NULL,NULL,'2017-04-16 16:59:45','shooney','N'),
	(63,'Profile','normal',2,1,59,'myinfo/profile','2017-04-16 20:57:03','shooney','N'),
	(64,'Attitude','normal',2,2,59,'myinfo/attitude','2017-04-16 20:57:03','shooney','N');