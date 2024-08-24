-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 12, 2024 lúc 08:33 PM
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
-- Đang đổ dữ liệu cho bảng `permission`
--

INSERT INTO `permission` (`permission_id`, `permission_name`, `status`) VALUES
('ACCESS_ACCOUNT', 'Truy cập trang tài khoản', 1),
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
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
