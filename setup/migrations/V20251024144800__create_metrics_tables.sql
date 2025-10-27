-- Create table for entity AnalyticsEvent
DROP TABLE IF EXISTS `analytics_event`;
CREATE TABLE `analytics_event` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`version` int NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`user` bigint NOT NULL,
	`duration_seconds` int DEFAULT NULL,
	`end_inner_height` int DEFAULT NULL,
	`end_inner_width` int DEFAULT NULL,
	`end_name` varchar(255) DEFAULT NULL,
	`end_outer_height` int DEFAULT NULL,
	`end_outer_width` int DEFAULT NULL,
	`language` varchar(255) DEFAULT NULL,
	`load_time` datetime DEFAULT NULL,
	`page_location` varchar(255) DEFAULT NULL,
	`platform` varchar(255) DEFAULT NULL,
	`port` varchar(255) DEFAULT NULL,
	`start_inner_height` int DEFAULT NULL,
	`start_inner_width` int DEFAULT NULL,
	`start_outer_height` int DEFAULT NULL,
	`start_outer_width` int DEFAULT NULL,
	`unload_time` datetime DEFAULT NULL,
	`user_agent` varchar(255) DEFAULT NULL,
	primary key(`id`),
	CONSTRAINT analytics_event_user_fk FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create table for entity Interaction
DROP TABLE IF EXISTS `interaction`;
CREATE TABLE `interaction` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`version` int NOT NULL,
	`created_at` datetime NOT NULL,
	`updated_at` datetime NOT NULL,
	`analytics_event` bigint NOT NULL,
	`client_timestamp` datetime DEFAULT NULL,
	`client_x` int DEFAULT NULL,
	`client_y` int DEFAULT NULL,
	`content` varchar(255) DEFAULT NULL,
	`event` varchar(255) DEFAULT NULL,
	`screen_x` int DEFAULT NULL,
	`screen_y` int DEFAULT NULL,
	`target_classes` varchar(255) DEFAULT NULL,
	`target_tag` varchar(255) DEFAULT NULL,
	`type` varchar(255) NOT NULL,
	primary key(`id`),
	CONSTRAINT interaction_analytics_event_fk FOREIGN KEY (`analytics_event`) REFERENCES `analytics_event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;