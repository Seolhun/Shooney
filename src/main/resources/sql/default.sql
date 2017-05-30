INSERT INTO `TB_USER_PROFILE` (`USER_PROFILE_ID`, `USER_PROFILE_TYPE`)
VALUES
	(1,'GUEST'),
	(2,'UESR'),
	(3,'STAR'),
	(4,'SUPERADMIN');


INSERT INTO `TB_MENU` (`MENU_ID`, `MENU_CREATED_BY`, `MENU_CREATED_DATE`, `MENU_DEL_FLAG`, `MENU_DEPTH`, `MENU_NAME`, `MENU_ORDER`, `MENU_PARENT_ID`, `MENU_TYPE`, `MENU_URL`)
VALUES
	(1, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'Home', 1, NULL, 'admin', ''),
	(2, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'User', 2, NULL, 'admin', 'admin/user/list'),
	(3, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'Blog', 4, NULL, 'admin', 'admin/blog/list'),
	(4, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'Menu', 3, NULL, 'admin', 'admin/menu/list'),
	(51, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'Home', 1, NULL, 'normal', ''),
	(52, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'Blog', 2, NULL, 'normal', 'blog/list'),
	(53, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'News', 3, NULL, 'normal', 'news/list'),
	(54, 'shooney', '2017-04-16 16:59:45', 'Y', 1, 'Music', 4, NULL, 'normal', 'music/list'),
	(59, 'shooney', '2017-04-16 16:59:45', 'N', 1, 'My Infomation', 9, NULL, 'normal', NULL),
	(63, 'shooney', '2017-04-16 20:57:03', 'N', 2, 'Profile', 1, 59, 'normal', 'myinfo/profile'),
	(64, 'shooney', '2017-04-16 20:57:03', 'N', 2, 'Attitude', 2, 59, 'normal', 'myinfo/attitude'),
	(65, 'shooney', '2017-05-28 23:39:43', 'N', 1, 'Stack', 5, NULL, 'admin', 'admin/stack/list');


INSERT INTO `TB_USER` (`USER_ID`, `USER_CREATED_DATE`, `USER_DEL_FLAG`, `USER_EMAIL`, `USER_LOCKED_AUTH`, `USER_MODIFIED_BY`, `USER_MODIFIED_DATE`, `USER_NICKNAME`, `USER_PASSWORD`, `USER_RECEIVE_EMAIL`, `USER_STATE`)
VALUES
	(1,'2017-05-28 21:50:28','N','shun10114@gmail.com',NULL,NULL,'2017-05-28 21:50:28','shooney','$2a$10$fF/kaU9dwLLqgD8cix3d2.F/1dqEL1Gnd3OoePUMdIIgfnmfRIJOS',0,'ACTIVE');

INSERT INTO `TB_USER_PROFILE_REFER` (`USER_ID`, `USER_PROFILE_ID`)
VALUES
	(1,4);

delete from `TB_STACK_SIMILAR`;

delete from `TB_STACK_IMG`;

delete from `TB_STACK`;