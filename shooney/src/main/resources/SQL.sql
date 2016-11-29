CREATE TABLE `USER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `state` varchar(30) NOT NULL,
  `point` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
CREATE TABLE `USER_PROFILE` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
CREATE TABLE `USER_PROFILE_REFER` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` int(5) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `USER_PROFILE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `PERSISTENT_LOGINS` (
  `email` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `USER_ATTEMPTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` bigint(20) NOT NULL,
  `attempts` int(5) NOT NULL,
  `logdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logip` varchar(30) NOT NULL DEFAULT '',
  `auth` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `MUSIC` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` longtext,
  `singer` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `lyrics` varchar(255) DEFAULT '',
  `url` varchar(255) DEFAULT '',
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `BOARD` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` longtext NOT NULL,
  `writer` varchar(30) NOT NULL,
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hits` bigint(20) NOT NULL DEFAULT '0',
  `likes` bigint(20) NOT NULL DEFAULT '0',
  `fileid` bigint(20) DEFAULT NULL,
  `delcheck` int(5) NOT NULL DEFAULT '0',
  `depth` int(50) NOT NULL DEFAULT '0',
  `entityname` varchar(30) NOT NULL,
  `pfname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_BOARD_FILE` (`fileid`),
  CONSTRAINT `FK_BOARD_FILE` FOREIGN KEY (`fileid`) REFERENCES `FILE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BOARD` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` longtext NOT NULL,
  `writer` varchar(30) NOT NULL,
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hits` bigint(20) NOT NULL DEFAULT '0',
  `likes` bigint(20) NOT NULL DEFAULT '0',
  `fileid` bigint(20) DEFAULT NULL,
  `delcheck` int(5) NOT NULL DEFAULT '0',
  `depth` int(50) NOT NULL DEFAULT '0',
  `entityname` varchar(30) NOT NULL,
  `pfname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_BOARD_FILE` (`fileid`),
  CONSTRAINT `FK_BOARD_FILE` FOREIGN KEY (`fileid`) REFERENCES `FILE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `COMMENT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `writer` varchar(30) NOT NULL,
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `likes` bigint(20) NOT NULL DEFAULT '0',
  `delcheck` int(5) NOT NULL DEFAULT '0',
  `board_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=431 DEFAULT CHARSET=utf8;

CREATE TABLE `FILE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(50) NOT NULL,
  `filepath` varchar(50) NOT NULL,
  `filesize` int(50) NOT NULL,
  `uploader` varchar(30) NOT NULL,
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `HISTORY` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) NOT NULL,
  `latestdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `MONSTER` (
  `id` int(10) unsigned NOT NULL,
  `Type` int(10) unsigned DEFAULT NULL,
  `Sortkey` int(10) unsigned DEFAULT NULL,
  `Name_kr` varchar(255) DEFAULT NULL,
  `Name_ja` varchar(255) DEFAULT NULL,
  `Name_en` varchar(255) DEFAULT NULL,
  `Name_fr` varchar(255) DEFAULT NULL,
  `Name_de` varchar(255) DEFAULT NULL,
  `Description_kr` text,
  `Description_ja` text,
  `Description_en` text,
  `Description_fr` text,
  `Description_de` text,
  `LevelMin` int(10) unsigned DEFAULT NULL,
  `LevelMax` int(10) unsigned DEFAULT NULL,
  `Time` int(10) unsigned DEFAULT NULL,
  `Halfway` tinyint(1) DEFAULT NULL,
  `RandomContentType` int(10) unsigned DEFAULT NULL,
  `Alliance` tinyint(1) DEFAULT NULL,
  `FinderPartyCondition` int(10) unsigned DEFAULT NULL,
  `PartyMemberCount` int(10) unsigned DEFAULT NULL,
  `TankCount` int(10) unsigned DEFAULT NULL,
  `HealerCount` int(10) unsigned DEFAULT NULL,
  `AttackerCount` int(10) unsigned DEFAULT NULL,
  `RangeCount` int(10) unsigned DEFAULT NULL,
  `DifferentiateDPS` tinyint(1) DEFAULT NULL,
  `PartyCount` int(10) unsigned DEFAULT NULL,
  `FreeRole` tinyint(1) DEFAULT NULL,
  `ItemLevel` int(10) unsigned DEFAULT NULL,
  `ItemLevelMax` int(10) unsigned DEFAULT NULL,
  `Colosseum` int(10) unsigned DEFAULT NULL,
  `Area` int(10) unsigned DEFAULT NULL,
  `ForceCount` int(10) unsigned DEFAULT NULL,
  `ExVersion` int(10) unsigned DEFAULT NULL,
  `data` blob,
  `path` text,
  `is_koeru_usually` tinyint(1) DEFAULT NULL,
  `is_koeru_annihilation` tinyint(1) DEFAULT NULL,
  `Index_kr` text,
  `Index_ja` text,
  `Index_en` text,
  `Index_fr` text,
  `Index_de` text,
  `index` longtext,
  PRIMARY KEY (`id`),
  KEY `InstanceContent_Type_index` (`Type`),
  FULLTEXT KEY `index` (`index`,`Name_kr`,`Name_en`,`Name_ja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ITEM` (
  `id` int(10) unsigned NOT NULL,
  `Category` int(10) unsigned DEFAULT NULL,
  `UICategory` int(10) unsigned DEFAULT NULL,
  `UIName_kr` varchar(255) DEFAULT NULL,
  `UIName_ja` varchar(255) DEFAULT NULL,
  `UIName_en` varchar(255) DEFAULT NULL,
  `UIName_fr` varchar(255) DEFAULT NULL,
  `UIName_de` varchar(255) DEFAULT NULL,
  `Help_kr` text,
  `Help_ja` text,
  `Help_en` text,
  `Help_fr` text,
  `Help_de` text,
  `Level` int(10) unsigned DEFAULT NULL,
  `EquipLevel` int(10) unsigned DEFAULT NULL,
  `Rarity` int(10) unsigned DEFAULT NULL,
  `HQ` tinyint(1) DEFAULT NULL,
  `SpecialBonus` int(10) unsigned DEFAULT NULL,
  `Series` int(10) unsigned DEFAULT NULL,
  `Slot` int(10) unsigned DEFAULT NULL,
  `Damage` int(10) unsigned DEFAULT NULL,
  `Damage_hq` int(10) unsigned DEFAULT NULL,
  `MagicDamage` int(10) unsigned DEFAULT NULL,
  `MagicDamage_hq` int(10) unsigned DEFAULT NULL,
  `Defense` int(10) unsigned DEFAULT NULL,
  `Defense_hq` int(10) unsigned DEFAULT NULL,
  `MagicDefense` int(10) unsigned DEFAULT NULL,
  `MagicDefense_hq` int(10) unsigned DEFAULT NULL,
  `ShieldRate` int(10) unsigned DEFAULT NULL,
  `ShieldRate_hq` int(10) unsigned DEFAULT NULL,
  `ShieldBlockRate` int(10) unsigned DEFAULT NULL,
  `ShieldBlockRate_hq` int(10) unsigned DEFAULT NULL,
  `AttackInterval` int(10) unsigned DEFAULT NULL,
  `AttackInterval_hq` int(10) unsigned DEFAULT NULL,
  `AutoAttack` int(10) unsigned DEFAULT NULL,
  `AutoAttack_hq` int(10) unsigned DEFAULT NULL,
  `Price` int(10) unsigned DEFAULT NULL,
  `PriceMin` int(10) unsigned DEFAULT NULL,
  `MirageItem` int(10) unsigned DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `icon_hq` varchar(255) DEFAULT NULL,
  `classjob` varchar(255) DEFAULT NULL,
  `Salvage` int(10) unsigned DEFAULT NULL,
  `Purify` int(10) unsigned DEFAULT NULL,
  `data` blob,
  `legacy` tinyint(1) DEFAULT NULL,
  `path` text,
  `Index_kr` text,
  `Index_ja` text,
  `Index_en` text,
  `Index_fr` text,
  `Index_de` text,
  `SortId` int(11) DEFAULT NULL,
  `index` text,
  PRIMARY KEY (`id`),
  KEY `Item_UICategory_index` (`UICategory`),
  KEY `Item_Level_index` (`Level`),
  KEY `Item_Legacy_index` (`legacy`),
  KEY `Item_Key_index` (`Key`),
  KEY `Item_EquipLevel_index` (`EquipLevel`),
  FULLTEXT KEY `index` (`index`,`UIName_kr`,`UIName_en`,`UIName_ja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('GUEST');

INSERT INTO USER_PROFILE(type)
VALUES ('PLAYER');

INSERT INTO USER_PROFILE(type)
VALUES ('CAPTAIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('SUPERADMIN');
 
commit;