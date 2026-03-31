CREATE DATABASE IF NOT EXISTS `chicken_game`;
USE `chicken_game`;

CREATE TABLE IF NOT EXISTS `highscore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_name` varchar(50) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `play_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Thêm dữ liệu mẫu để Thầy thấy có điểm sẵn
INSERT INTO `highscore` (`player_name`, `score`) VALUES 
('Nguyễn Văn Khoa', 1000),
('Sky Warrior', 850),
('Player One', 500);
