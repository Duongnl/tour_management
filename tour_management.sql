-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 28, 2024 lúc 10:28 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tour_management`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `account_id` varchar(255) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `time` datetime NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `airline`
--

CREATE TABLE `airline` (
  `airline_id` varchar(255) NOT NULL,
  `airline_name` varchar(255) NOT NULL,
  `airline_detail` varchar(255) DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `category_id` varchar(255) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`category_id`, `category_name`, `status`) VALUES
('1', 'Du lịch Singapore', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customer_id` varchar(255) NOT NULL,
  `customer_rel_id` varchar(255) DEFAULT NULL,
  `relationship_name` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `phone_number` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `visa_expire` datetime(6) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employee_id` varchar(255) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `employee_name` varchar(255) NOT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `commission` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permission`
--

CREATE TABLE `permission` (
  `permission_id` varchar(255) NOT NULL,
  `permission_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reserve`
--

CREATE TABLE `reserve` (
  `reserve_id` varchar(255) NOT NULL,
  `customer_id` varchar(255) NOT NULL,
  `tour_id` varchar(255) NOT NULL,
  `employee_id` varchar(255) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `pay` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role_permission`
--

CREATE TABLE `role_permission` (
  `role_id` varchar(255) NOT NULL,
  `permission_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `tour_id` varchar(255) NOT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `tour_name` varchar(255) NOT NULL,
  `tour_detail` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `quantity_sell` int(11) NOT NULL,
  `quantity_reserve` int(11) NOT NULL,
  `quantity_left` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`tour_id`, `category_id`, `tour_name`, `tour_detail`, `quantity`, `quantity_sell`, `quantity_reserve`, `quantity_left`, `price`, `status`) VALUES
('1', '1', 'Du lịch Singapore', 'Vào mùa xuân', 50, 0, 0, 50, 15000000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_time`
--

CREATE TABLE `tour_time` (
  `tour_time_id` varchar(255) NOT NULL,
  `tour_id` varchar(255) NOT NULL,
  `time_name` varchar(255) NOT NULL,
  `departure_time` datetime NOT NULL,
  `departure_airline_id` varchar(255) DEFAULT NULL,
  `return_time` datetime NOT NULL,
  `return_airline_id` varchar(255) DEFAULT NULL,
  `visa_expire` datetime(6) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `account_id` (`account_id`),
  ADD UNIQUE KEY `account_name` (`account_name`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `account_fk6` (`role_id`);

--
-- Chỉ mục cho bảng `airline`
--
ALTER TABLE `airline`
  ADD PRIMARY KEY (`airline_id`),
  ADD UNIQUE KEY `airline_id` (`airline_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `customer_id` (`customer_id`),
  ADD UNIQUE KEY `phone_number` (`phone_number`),
  ADD KEY `customer_fk1` (`customer_rel_id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `employee_id` (`employee_id`),
  ADD KEY `employee_fk1` (`account_id`);

--
-- Chỉ mục cho bảng `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`permission_id`),
  ADD UNIQUE KEY `permission_id` (`permission_id`);

--
-- Chỉ mục cho bảng `reserve`
--
ALTER TABLE `reserve`
  ADD PRIMARY KEY (`reserve_id`),
  ADD UNIQUE KEY `reserve_id` (`reserve_id`),
  ADD KEY `reserve_fk1` (`customer_id`),
  ADD KEY `reserve_fk2` (`tour_id`),
  ADD KEY `reserve_fk3` (`employee_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `role_id` (`role_id`);

--
-- Chỉ mục cho bảng `role_permission`
--
ALTER TABLE `role_permission`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `role_permission_fk1` (`permission_id`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`tour_id`),
  ADD UNIQUE KEY `tour_id` (`tour_id`),
  ADD KEY `tour_fk1` (`category_id`);

--
-- Chỉ mục cho bảng `tour_time`
--
ALTER TABLE `tour_time`
  ADD PRIMARY KEY (`tour_time_id`),
  ADD UNIQUE KEY `tour_time_id` (`tour_time_id`),
  ADD KEY `tour_time_fk0` (`tour_id`),
  ADD KEY `tour_time_fk3` (`departure_airline_id`),
  ADD KEY `tour_time_fk5` (`return_airline_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_fk6` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

--
-- Các ràng buộc cho bảng `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_fk1` FOREIGN KEY (`customer_rel_id`) REFERENCES `customer` (`customer_id`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_fk1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Các ràng buộc cho bảng `reserve`
--
ALTER TABLE `reserve`
  ADD CONSTRAINT `reserve_fk1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `reserve_fk2` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  ADD CONSTRAINT `reserve_fk3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Các ràng buộc cho bảng `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `role_permission_fk0` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `role_permission_fk1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`);

--
-- Các ràng buộc cho bảng `tour`
--
ALTER TABLE `tour`
  ADD CONSTRAINT `tour_fk1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Các ràng buộc cho bảng `tour_time`
--
ALTER TABLE `tour_time`
  ADD CONSTRAINT `tour_time_fk0` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  ADD CONSTRAINT `tour_time_fk3` FOREIGN KEY (`departure_airline_id`) REFERENCES `airline` (`airline_id`),
  ADD CONSTRAINT `tour_time_fk5` FOREIGN KEY (`return_airline_id`) REFERENCES `airline` (`airline_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
