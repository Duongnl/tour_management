-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 03, 2024 lúc 03:44 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

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
  `phone_number` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `role_id` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`account_id`, `account_name`, `password`, `email`, `phone_number`, `time`, `role_id`, `status`) VALUES
('04c9375a-4cdd-4026-a35c-1c33b91aeb54', 'thaoh123', '$2a$10$iSikT1CN8QevmEoucm7lqetr5lYhhSF9BQ.Q1nMmDS/cV0ad/SVpK', 'dangthithaohien003@gmail.com', '0963717300', '2024-07-20 08:46:28', 1, 1),
('1eeeb10d-3d12-48af-a309-81636a2bfa33', 'duongngocle4231', '$2a$10$htwiewI15jUA.PPXgxMG0OcfIWOEnELNgwyvPpZcz.jOt9WMDXjl.', 'nguyenvanT2003@gmail.com', '0963717311', '2024-07-16 20:20:35', 2, 1),
('219bba7c-32c0-47b5-8161-b9a647605b3a', 'duongngocle', '$2a$10$ObgHb1k/M9fKCbLfuUsMFuPH99vlFuqV7w6iF4Sx/qqf70tsOYgsy', 'Lengocduong.2206@gmail.com', '0963717300', '2024-07-20 09:06:21', 2, 0),
('2d7eee88-aaa8-4e7f-a412-716bde3aa328', 'vanm123', '$2a$10$JTq/gooJjUisY7eOHFEXH.lJcMVj897.V5jATahGlnLaI6SBhLQmS', 'nguyenvanm2003@gmail.com', '0963717300', '2024-07-15 11:33:49', 1, 1),
('340114f6-1291-48ab-adae-ee41ec8672f4', 'anh123', '$2a$10$H173b9hL1SwU4TyVkIiFjOqi82XlZ63hHHdqrEldPHMc893aihJ3u', 'tranvant2008@gmail.com', '0963717300', '2023-07-08 03:15:30', 7, 1),
('47535719-7ac8-4c07-9e44-77b3e2246819', 'vang123', '$2a$10$lU5K7pjGtgTbjYWDXKzgkOnTG.s9Wnyo4S5vf.K2vh9kZioEVqDgG', 'nguyenvang2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('59f7911c-ca37-49d8-bb99-a8a557a4a3b2', 'anh1234', '$2a$10$ACSkIR0waxt4XPOPR6FZSuRkwrq/Sh351/0ffAnJ5UKkq5k929VA.', 'Lengocduong.2206@gmail.com', '0963717300', '2024-07-20 09:00:56', 2, 0),
('62343f4e-7727-402b-850d-80ced8a2f6c1', 'vanf123', '$2a$10$27JFgZxAQBEHyHrIMyyikOIZa9YcJU0/Bha5bzIQN7Xj1OkP4T1sa', 'nguyenvanf2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('64382246-9dea-4c57-854a-f8d52ad1ec2d', 'duonng', '$2a$10$TfsoMi09ggQNW6uma4RwM.3Y6Ju92eSrQCHzoIShjZMRX/n83jDsW', 'sontung@gmail.com', '0963666', '2024-07-20 17:41:18', 4, 0),
('66a0c1b6-6640-4424-b54a-715ccdd3162a', 'hai123', '$2a$10$uGQXR7OZNOvIho2xRpcUh.54SMVVLDNC9lZt/6phK/XVoHI614gUa', 'buithihaiduong003@gmail.com', '0963717300', '2024-07-17 08:00:41', 1, 0),
('6b7f4b92-c3ad-415e-8c69-b9b9950a53a1', 'vann123', '$2a$10$t50YnnfDzA/rym0M5aDJpOStpZvGhW7X4U6QA02AiCj9ANvBMRM1.', 'nguyenvann2003gmail.com', '0963717300', '2024-07-15 11:35:24', 1, 0),
('77ca06bf-0a64-4b56-9c25-2beed68ada39', 'vanh123', '$2a$10$vGoILgWeCkRPROk2Zu0SZeVc5IUEi9Jco9DhnAUBRqFnXc17QDGlC', 'nguyenvanh2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('7fc96088-d0f3-4a4c-bdf9-670349a3c1c2', 'vanb123', '$2a$10$H173b9hL1SwU4TyVkIiFjOqi82XlZ63hHHdqrEldPHMc893aihJ3u', 'nguyenvanb2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('9ad3f9e2-d973-4241-ab39-4f833e2c2964', 'vanv123', '$2a$10$FnHhLIIfCCjBLk9gtuVmse7bJpbLWpoZwpyGASJYR73Rk7IYNF1/m', 'nguyenvanv2003@gmail.com', '0963717300', '2024-07-15 09:31:28', 1, 0),
('a225e0a7-c775-4379-8e73-a65c3f78a364', 'vanc123', '$2a$10$H173b9hL1SwU4TyVkIiFjOqi82XlZ63hHHdqrEldPHMc893aihJ3u', 'nguyenvanc2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('bd482c16-9219-4d9e-9a7b-9c3b42b533d1', 'vand123', '$2a$10$H173b9hL1SwU4TyVkIiFjOqi82XlZ63hHHdqrEldPHMc893aihJ3u', 'nguyenvand2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('bd9db603-6e24-45bd-832d-cef8351a0eb7', 'vane123', '$2a$10$b.a/IFIvDA6eU.lLZWY3AOciBrEjoeFFZwbG1no52iIY13OanTu.O', 'nguyenvane2003@gmail.com', '0963717300', '2023-07-08 03:15:30', 1, 0),
('c6a6f411-c2e8-4674-b46a-775e8c78afb0', 'lengocduong', '$2a$10$bztK/bXL4Ed4mRAgX3PrXOvIRAtjiuQDGoOxqt4KbvEEE25wADRxa', 'Lengocduong.2206@gmail.com', '0963717300', '2024-07-20 16:26:40', 1, 1),
('da9c64a3-b680-4b84-9e33-80e0a0cbd005', 'anhhh', '$2a$10$QAnvBBDJRbxMQx3V78YN3u0msE5tnQDGyzQaoy0nF59solWOEgjZG', 'Lengocduong.2206@gmail.com', '0963717300', '2024-08-16 00:26:05', 2, 0),
('db9d2e24-c238-4f72-a9dd-ab7bee260ebc', 'vanty', '$2a$10$jc6mjQFlm6eJHcRjbQz.7u0S.SoWGglj9aypVOcn75hQo5LJzd9je', 'Lengocduong.2206@gmail.com', '0963717300', '2024-07-27 17:17:15', 1, 1),
('dded2d71-e701-4812-a8d9-920d6beaf2c9', 'vank123', '$2a$10$i5bb6dtsJBEzL0gMtfFvguZjx9Tr.B/kxXd8Tatr9tof4B.MyH2ai', 'nguyenvank2003@gmail.com', '0963717300', '2024-07-15 13:24:23', 1, 0),
('dfa40feb-f6aa-47ea-abc7-16cadaeb37ec', 'thanh', '$2a$10$fVUMnkfaQEpXFq22X8YtFerjW6I33k1Oe3ddkklFfYoHW4o7vTolC', 'Lengocduong.2206@gmail.com', '4242342342', '2024-08-16 00:21:25', 2, 1),
('e2e37ef1-386d-4b57-90cc-89243dcdc958', 'vans123', '$2a$10$ATArQOugukDvwEGnRWv5u.eYriTYyqc4DJNdAAgcFJewaiynCr/VO', 'nguyenvans2003@gmail.com', '0963717300', '2024-07-16 20:16:08', 1, 0),
('eaa88817-4edb-4cd5-bce1-5e26e9135761', 'nguyena123', '$2a$10$juhk.z9H34ZpiW9Ze0XWSef0g1ZnFmavCZjiNwb1ah.V/HSbumT6i', 'nguyena123@gmail.com', '4242343242', '2024-08-15 17:44:02', 7, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `airline`
--

CREATE TABLE `airline` (
  `airline_id` int(11) NOT NULL,
  `airline_name` varchar(255) NOT NULL,
  `airline_detail` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `airline`
--

INSERT INTO `airline` (`airline_id`, `airline_name`, `airline_detail`, `status`) VALUES
(1, 'VJ305', 'SGN-MY', 1),
(2, 'VJ306', 'MY-SGN', 1),
(3, 'VJ301', 'SGN_MY', 1),
(4, 'VJ302', 'MY-SGN', 1),
(5, 'VN301', 'HN-PHAP', 1),
(6, 'VN302', 'PHAP-HN', 1),
(7, 'VN304', 'HN-PHAP', 1),
(8, 'VN305', 'PHAP-HN', 1),
(9, 'QH201', 'DN-DL', 1),
(10, 'QH202', 'DL-DN', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_detail` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`category_id`, `category_name`, `category_detail`, `url`, `status`) VALUES
(1, 'Du lịch nhật bản đặc biệt', 'Mới nhất', 'Du-lich-nhat-ban-dac-biet', 0),
(2, 'Tour Mỹ', 'Đây rất là vui', 'Tour-My', 1),
(3, 'Tour Trung Quốc mới nhất', 'Đây rất là vui lứm', 'Tour-Trung-Quoc-moi-nhat', 0),
(4, 'Tour Đài Loan', 'Đây rất là vui', 'Tour-Dai-Loan', 1),
(5, 'Tour Châu Âu', 'Đây rất là vui', 'Tour-Chau-Au', 0),
(6, 'Tour Châu Âu mới', 'Đây rất là vui', 'Tour-Chau-Au-moi', 0),
(7, 'Tour Nga', 'Tour Nga đặc biệt', 'Tour-Nga', 1),
(8, 'Tour Pháp', 'Tour Pháp có visa', 'Tour-Phap', 1),
(9, 'Tour Brazil', 'Tour đá bóng Brazil', 'Tour-Brazil', 0),
(10, 'Tour Sale', 'Tour đang được sale', 'Tour-Sale', 1),
(11, 'Danh muc sale', 'Nhung tour dang sale', 'Danh-muc-sale', 1),
(12, 'Danh mục mới sale rẻ', 'Tour sale mới nhất', 'danh-muc-moi-sale-re', 1),
(13, 'Danh mục mới sale rẻ nhất', 'Tour sale mới nhất', 'Danh-muc-moi-sale-re', 1),
(14, 'Tour Hong Kong', '', 'Tour-Hong-Kong', 1),
(15, 'Danh mục phổ biến', '', 'Danh-muc-pho-bien', 1),
(16, 'Danh mục thịnh hành', 'đưaadâd', 'Danh-muc-thinh-hanh', 1),
(17, 'Tour sale', 'fefweew', 'Tour-sale', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `customer_rel_id` int(11) DEFAULT NULL,
  `relationship_name` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `visa_expire` date DEFAULT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`customer_id`, `customer_rel_id`, `relationship_name`, `customer_name`, `sex`, `phone_number`, `email`, `address`, `birthday`, `visa_expire`, `time`, `status`) VALUES
(1, NULL, 'Chủ hộ', 'Trần Quang Linh', 1, '0963717456', 'quanglinh1023m@gmail.com', 'Tan phu', '2023-06-26', '2028-02-19', '2024-09-19 14:27:33', 1),
(2, 1, 'Con', 'Dương', 1, '096371471', 'lengoduong.2206@gmail.com', 'Tân phú', '2024-09-04', '2024-09-24', '2024-09-19 14:30:45', 1),
(3, NULL, NULL, 'Dương mới', 1, '0963717300', 'duongngocle@gmail.com', 'Tân phú', '2003-08-21', '2003-08-21', '2024-10-01 14:30:00', 1),
(4, NULL, 'Chủ hộ', 'Linh mới', 1, '21313131', 'linhmoi@gmail.com', 'Tân phú', '2024-10-02', '2024-10-01', '2024-10-02 18:14:43', 1),
(5, 1, 'Con', 'Nguyễn A', 1, '3131313', 'nguyena@gmail.com', 'tan phu', '2024-10-10', '2024-10-01', '2024-10-02 18:31:59', 1),
(6, NULL, 'Chủ hộ', 'Nguyễn B', 0, '214234234', 'nguyenb@gmail.com', 'Tân phú', '2024-10-02', '2024-10-03', '2024-10-03 00:35:22', 1),
(7, NULL, 'Chủ hộ', 'Nguyễn C', 0, '313131', 'nguyenc@gmail.com', 'Tân phú', '2024-10-03', '2024-10-03', '2024-10-03 00:38:02', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `employee_name` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `total_commission` bigint(20) DEFAULT NULL,
  `total_sales` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`employee_id`, `account_id`, `employee_name`, `birthday`, `total_commission`, `total_sales`, `status`) VALUES
(1, '340114f6-1291-48ab-adae-ee41ec8672f4', 'anhle', '2008-09-11', 0, 0, 1),
(2, '7fc96088-d0f3-4a4c-bdf9-670349a3c1c2', 'Nguyễn Văn B', '2003-11-23', 0, 0, 1),
(3, 'bd482c16-9219-4d9e-9a7b-9c3b42b533d1', 'Nguyễn Văn C', '2003-08-23', 0, 0, 1),
(4, 'a225e0a7-c775-4379-8e73-a65c3f78a364', 'Nguyễn Văn D', '2003-08-23', 0, 0, 1),
(5, 'bd9db603-6e24-45bd-832d-cef8351a0eb7', 'Nguyễn Văn E', '2003-08-23', 0, 0, 1),
(6, '62343f4e-7727-402b-850d-80ced8a2f6c1', 'Nguyễn Văn F', '2003-08-23', 2, 3, 1),
(7, '47535719-7ac8-4c07-9e44-77b3e2246819', 'Nguyễn Văn G', '2003-08-23', 2, 3, 1),
(8, '77ca06bf-0a64-4b56-9c25-2beed68ada39', 'Nguyễn Văn H', '2003-08-23', 2, 3, 1),
(9, '9ad3f9e2-d973-4241-ab39-4f833e2c2964', 'Nguyễn Văn V', '2003-08-23', 2, 3, 1),
(10, '2d7eee88-aaa8-4e7f-a412-716bde3aa328', 'Nguyễn Văn M', '2003-08-23', 2, 3, 1),
(11, '6b7f4b92-c3ad-415e-8c69-b9b9950a53a1', 'Nguyễn Văn N', '2003-08-23', 2, 3, 1),
(12, 'dded2d71-e701-4812-a8d9-920d6beaf2c9', 'Nguyễn Văn K', '2003-08-22', 2, 3, 1),
(13, 'e2e37ef1-386d-4b57-90cc-89243dcdc958', 'Nguyễn Văn S', '2003-08-22', 2, 3, 1),
(14, '1eeeb10d-3d12-48af-a309-81636a2bfa33', 'Dương Ngọc Lê', '2003-06-22', 3000000, 40000000, 1),
(15, '66a0c1b6-6640-4424-b54a-715ccdd3162a', 'bui hai duong', '2003-07-16', 0, 0, 1),
(16, '04c9375a-4cdd-4026-a35c-1c33b91aeb54', 'Dang thi', '2024-07-09', 0, 0, 1),
(17, '59f7911c-ca37-49d8-bb99-a8a557a4a3b2', 'Anh', '2024-07-14', 0, 0, 1),
(18, '219bba7c-32c0-47b5-8161-b9a647605b3a', 'Dương Ngọc Lê', '2024-07-14', 0, 0, 1),
(19, 'c6a6f411-c2e8-4674-b46a-775e8c78afb0', 'Lê Ngọc Dương', '2024-07-17', 0, 0, 1),
(20, '64382246-9dea-4c57-854a-f8d52ad1ec2d', 'Sơn Tùngfff', '2003-05-04', 0, 0, 1),
(21, 'db9d2e24-c238-4f72-a9dd-ab7bee260ebc', 'Văn Tý', '2024-07-25', 0, 0, 1),
(22, 'eaa88817-4edb-4cd5-bce1-5e26e9135761', 'Nguyễn  A', '2024-08-21', 0, 0, 1),
(23, 'dfa40feb-f6aa-47ea-abc7-16cadaeb37ec', 'Anh', '2003-08-21', 0, 0, 1),
(24, 'da9c64a3-b680-4b84-9e33-80e0a0cbd005', 'anh ha', '2003-08-21', 0, 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `history`
--

CREATE TABLE `history` (
  `history_id` varchar(255) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `history_detail` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `history`
--

INSERT INTO `history` (`history_id`, `account_id`, `history_detail`, `time`, `status`) VALUES
('a4e4c6e2-6b92-4b6e-a0eb-15474e6840a9', '1eeeb10d-3d12-48af-a309-81636a2bfa33', 'change status customer: 6', '2024-10-03 00:36:20', 1),
('e35c5ff5-23c4-4cf8-b8a5-49e392b9643b', '1eeeb10d-3d12-48af-a309-81636a2bfa33', 'change status customer: 4', '2024-10-03 00:34:42', 1),
('eb368384-044f-4416-8232-7f6a2fa2df34', '1eeeb10d-3d12-48af-a309-81636a2bfa33', 'change status customer: 5', '2024-10-03 00:34:41', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `permission`
--

CREATE TABLE `permission` (
  `permission_id` varchar(255) NOT NULL,
  `permission_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `permission`
--

INSERT INTO `permission` (`permission_id`, `permission_name`, `status`) VALUES
('ACCESS_ACCOUNT', 'Truy cập trang tài khoản', 1),
('ACCESS_BOOKED', 'Truy cập thông tin đặt chổ', 1),
('ACCESS_CATEGORY', 'Truy cập danh mục', 1),
('ACCESS_CUSTOMER', 'Truy cập khách hàng', 1),
('ACCESS_DASHBOARD', 'Truy cập trang tổng quan', 1),
('ACCESS_HISTORY', 'Truy cập lịch sử', 1),
('ACCESS_RESERVE', 'Truy cập đặt chổ', 1),
('ACCESS_ROLE', 'Truy cập trang quyền', 1),
('ACCESS_TOUR', 'Truy cập tour', 1),
('CHANGE_ACCOUNT_STATUS', 'Đổi trạng thái tài khoản', 1),
('CHANGE_CATEGORY_STATUS', 'Thay đổi trạng thái danh mục', 1),
('CHANGE_CUSTOMER_STATUS', 'Thay đổi trạng thái khách hàng', 1),
('CHANGE_RESERVE_STATUS', 'Thay đổi trạng thái đặt chổ', 1),
('CHANGE_ROLE_STATUS', 'Thay đổi trạng thái quyền', 1),
('CHANGE_TOUR_STATUS', 'Thay đổi trạng thái tour', 1),
('CREATE_ACCOUNT', 'Tạo tài khoản', 1),
('CREATE_CATEGORY', 'Tạo danh mục', 1),
('CREATE_CUSTOMER', 'Tạo mới khách hàng', 1),
('CREATE_RESERVE', 'Tạo mới đặt chổ', 1),
('CREATE_ROLE', 'Tạo quyền', 1),
('CREATE_TOUR', 'Tạo mới tour', 1),
('UPDATE_ACCOUNT', 'Sửa tài khoản', 1),
('UPDATE_CATEGORY', 'Cập nhật danh mục', 1),
('UPDATE_CUSTOMER', 'Cập nhật thông tin khách hàng', 1),
('UPDATE_RESERVE', 'Cập nhật đặt chổ', 1),
('UPDATE_ROLE', 'Sửa quyền', 1),
('UPDATE_TOUR', 'Cập nhật tour', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reserve`
--

CREATE TABLE `reserve` (
  `reserve_id` varchar(255) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `tour_time_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `price_min` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `pay` int(11) DEFAULT NULL,
  `time` datetime NOT NULL,
  `commission` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reserve`
--

INSERT INTO `reserve` (`reserve_id`, `customer_id`, `tour_time_id`, `employee_id`, `note`, `price_min`, `price`, `pay`, `time`, `commission`, `status`) VALUES
('0b350f19-b58f-41f7-9375-f96eedf0b3c5', 2, 2, 14, '', 20000000, 20000000, 0, '2024-10-03 02:45:18', 1500000, 0),
('3a307269-e723-4108-87a6-d25fbdcd9a13', 7, 2, 14, '', 20000000, 20000000, 0, '2024-10-03 02:25:55', 1500000, 1),
('b61babfb-993d-42ae-8821-3515678b43f5', 1, 2, 14, 'Giá đặc biệt', 20000000, 55230000, 0, '2024-10-03 02:32:51', 1500000, 2),
('f10a12c9-44fd-4d10-abe4-2f150c0d1b5a', 6, 2, 14, '', 20000000, 20000000, 0, '2024-10-03 02:25:55', 1500000, 1),
('fc4ee1fb-5f26-4fab-870c-321316686040', 5, 1, 14, '', 10000000, 1, 0, '2024-10-03 02:57:06', 1000000, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `status`) VALUES
(1, 'Nhân viên mới', 1),
(2, 'Admin', 1),
(3, 'Sale', 1),
(4, 'Đối tác', 0),
(5, 'Người dùng', 0),
(6, 'Thực tập', 1),
(7, 'Người mới', 1),
(8, 'Khảo sát', 1),
(9, 'QL Tour', 1),
(10, 'Kế toán', 1),
(11, 'Sếp', 1),
(12, 'Tham quan', 1),
(13, 'Bộ trưởng', 1),
(14, 'Giám đốc', 1),
(15, 'Khách hàng', 1),
(16, 'Tạp hóa', 1),
(17, 'Hiệu sách', 1),
(18, 'Quản lý', 1),
(19, 'Nhà điều hành', 1),
(20, 'Quyền đam mê', 1),
(21, 'Quyền mới', 1),
(22, 'fsdfsdfsds', 1),
(23, '    hh    ', 1),
(24, 'ff', 1),
(25, 'dhaahda', 1),
(26, 'hht', 1),
(27, 'ffsefefe', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role_permission`
--

CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `role_permission`
--

INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES
(1, 'ACCESS_ACCOUNT'),
(1, 'ACCESS_RESERVE'),
(1, 'ACCESS_ROLE'),
(1, 'CREATE_CATEGORY'),
(1, 'UPDATE_CATEGORY'),
(2, 'ACCESS_ACCOUNT'),
(2, 'ACCESS_BOOKED'),
(2, 'ACCESS_CATEGORY'),
(2, 'ACCESS_CUSTOMER'),
(2, 'ACCESS_DASHBOARD'),
(2, 'ACCESS_HISTORY'),
(2, 'ACCESS_RESERVE'),
(2, 'ACCESS_ROLE'),
(2, 'ACCESS_TOUR'),
(2, 'CHANGE_ACCOUNT_STATUS'),
(2, 'CHANGE_CATEGORY_STATUS'),
(2, 'CHANGE_CUSTOMER_STATUS'),
(2, 'CHANGE_RESERVE_STATUS'),
(2, 'CHANGE_ROLE_STATUS'),
(2, 'CHANGE_TOUR_STATUS'),
(2, 'CREATE_ACCOUNT'),
(2, 'CREATE_CATEGORY'),
(2, 'CREATE_CUSTOMER'),
(2, 'CREATE_RESERVE'),
(2, 'CREATE_ROLE'),
(2, 'CREATE_TOUR'),
(2, 'UPDATE_ACCOUNT'),
(2, 'UPDATE_CATEGORY'),
(2, 'UPDATE_CUSTOMER'),
(2, 'UPDATE_RESERVE'),
(2, 'UPDATE_ROLE'),
(2, 'UPDATE_TOUR'),
(3, 'UPDATE_ACCOUNT'),
(3, 'UPDATE_CUSTOMER'),
(5, 'CREATE_ACCOUNT'),
(6, 'ACCESS_CATEGORY'),
(6, 'ACCESS_CUSTOMER'),
(6, 'ACCESS_RESERVE'),
(6, 'ACCESS_TOUR'),
(7, 'ACCESS_ACCOUNT'),
(7, 'ACCESS_CATEGORY'),
(7, 'ACCESS_ROLE'),
(7, 'CHANGE_ACCOUNT_STATUS'),
(7, 'CREATE_ACCOUNT'),
(19, 'ACCESS_ACCOUNT'),
(19, 'CREATE_ACCOUNT'),
(19, 'UPDATE_ACCOUNT'),
(20, 'ACCESS_ACCOUNT'),
(20, 'ACCESS_ROLE'),
(21, 'ACCESS_ACCOUNT'),
(21, 'ACCESS_CATEGORY'),
(21, 'ACCESS_CUSTOMER'),
(21, 'ACCESS_DASHBOARD'),
(21, 'ACCESS_HISTORY'),
(21, 'ACCESS_RESERVE'),
(21, 'ACCESS_ROLE'),
(21, 'ACCESS_TOUR'),
(22, 'ACCESS_CATEGORY'),
(22, 'CREATE_CATEGORY'),
(25, 'ACCESS_CATEGORY'),
(25, 'ACCESS_RESERVE'),
(25, 'ACCESS_TOUR'),
(26, 'ACCESS_CATEGORY');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `tour_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `tour_name` varchar(255) NOT NULL,
  `tour_detail` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`tour_id`, `category_id`, `tour_name`, `tour_detail`, `url`, `status`) VALUES
(1, 2, 'Du lịch tượng Nữ Thần Tự Do', 'Đi có visa các kiểu', 'Du-lich-tuong-Nu-Than-Tu-Do', 1),
(2, 8, 'Du lịch tháp Eiffel', 'Thích thì đi, không thích thì ở nhà', 'Du-lich-thap-Eiffel', 1),
(3, 7, 'Du lịch điện Kremlin', 'Ai sợ thì đi về', 'Du-lich-dien-Kremlin', 1),
(4, 4, 'Du lịch làng Cổ Thập Phần', 'Ai không sợ thì đi', 'Du-lich-lang-Co-Thap-Phan', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_time`
--

CREATE TABLE `tour_time` (
  `tour_time_id` int(11) NOT NULL,
  `tour_id` int(11) NOT NULL,
  `time_name` varchar(255) NOT NULL,
  `departure_date` date DEFAULT NULL,
  `departure_airline_id` int(11) DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `return_airline_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `quantity_reserve` int(11) NOT NULL,
  `quantity_sell` int(11) NOT NULL,
  `quantity_left` int(11) NOT NULL,
  `price_min` int(11) NOT NULL,
  `commission` int(11) NOT NULL,
  `visa_expire` date DEFAULT NULL,
  `status` int(11) NOT NULL,
  `departure_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_time`
--

INSERT INTO `tour_time` (`tour_time_id`, `tour_id`, `time_name`, `departure_date`, `departure_airline_id`, `return_date`, `return_airline_id`, `quantity`, `quantity_reserve`, `quantity_sell`, `quantity_left`, `price_min`, `commission`, `visa_expire`, `status`, `departure_time`, `return_time`) VALUES
(1, 1, 'Mùa hè cuối tháng 8', '2024-08-02', 1, '2024-08-31', 2, 50, 1, 0, 49, 10000000, 1000000, '2026-08-31', 1, '2024-08-22 15:52:15', '2024-08-31 15:52:24'),
(2, 1, 'Mùa thu tháng 9/2024', '2024-08-29', 3, '2024-09-30', 4, 60, 1, 2, 57, 20000000, 1500000, '2025-08-31', 1, '2024-09-01 15:52:34', '2024-08-30 15:52:41'),
(3, 2, 'Du lịch cuối tháng 8', '2024-08-22', 5, '2024-08-31', 6, 40, 0, 0, 40, 30000000, 1600000, '2026-08-31', 1, '2024-08-22 15:52:56', '2024-08-31 15:53:00'),
(4, 2, 'Du lịch thu tháng 9', '2024-09-01', 7, '2024-09-30', 8, 40, 0, 0, 40, 40000000, 1600000, '2026-08-31', 1, '2024-09-01 15:53:09', '2024-09-30 15:53:14'),
(5, 4, 'Du lịch mùa thu tháng 9', '2024-08-29', 9, '2024-09-30', 10, 20, 0, 0, 20, 50000000, 500000, '2026-08-31', 1, '2024-09-01 15:53:24', '2024-09-30 15:53:30');

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
  ADD KEY `account_fk1` (`role_id`);

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
  ADD UNIQUE KEY `account_id` (`account_id`);

--
-- Chỉ mục cho bảng `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`history_id`),
  ADD UNIQUE KEY `history_id` (`history_id`),
  ADD KEY `history_fk1` (`account_id`);

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
  ADD KEY `reserve_fk2` (`tour_time_id`),
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
  ADD KEY `tour_time_fk1` (`tour_id`),
  ADD KEY `tour_time_fk4` (`departure_airline_id`),
  ADD KEY `tour_time_fk6` (`return_airline_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `airline`
--
ALTER TABLE `airline`
  MODIFY `airline_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT cho bảng `tour`
--
ALTER TABLE `tour`
  MODIFY `tour_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tour_time`
--
ALTER TABLE `tour_time`
  MODIFY `tour_time_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

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
-- Các ràng buộc cho bảng `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_fk1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Các ràng buộc cho bảng `reserve`
--
ALTER TABLE `reserve`
  ADD CONSTRAINT `reserve_fk1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `reserve_fk2` FOREIGN KEY (`tour_time_id`) REFERENCES `tour_time` (`tour_time_id`),
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
  ADD CONSTRAINT `tour_time_fk1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`),
  ADD CONSTRAINT `tour_time_fk4` FOREIGN KEY (`departure_airline_id`) REFERENCES `airline` (`airline_id`),
  ADD CONSTRAINT `tour_time_fk6` FOREIGN KEY (`return_airline_id`) REFERENCES `airline` (`airline_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
