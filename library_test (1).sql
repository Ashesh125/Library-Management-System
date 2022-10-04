-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 04, 2022 at 04:28 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_Id` int(11) NOT NULL,
  `admin_Name` varchar(50) NOT NULL,
  `admin_Hash` varchar(256) NOT NULL,
  `admin_Hash2` varchar(256) NOT NULL,
  `admin_Roll` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_Id`, `admin_Name`, `admin_Hash`, `admin_Hash2`, `admin_Roll`) VALUES
(1, 'Ashesh Shakya', '93fa9d1b6220d34374d8b8d1ec1d1a1e6c383196c69c2a0a588c97adfbd61cd4', '93fa9d1b6220d34374d8b8d1ec1d1a1e6c383196c69c2a0a588c97adfbd61cd4', '180602');

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `author_Id` int(11) NOT NULL,
  `author_Name` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `authors`
--

INSERT INTO `authors` (`author_Id`, `author_Name`) VALUES
(10, 'William Shakespeare'),
(11, 'Barbara Cartland'),
(12, 'Masashi Kishimoto'),
(13, 'Saint Mustard'),
(14, 'Dipper Pines'),
(15, 'Parvinder S. Bali'),
(16, 'Tom Powers'),
(17, 'Clayton W. Barrows'),
(18, 'Author 231'),
(19, 'Andrew S. Tanenbaum'),
(20, 'Paul Deitel'),
(21, 'Harvey Deitel'),
(22, 'Ricardo Baeza Yates'),
(23, 'Curtis F. Gerald'),
(24, 'Mark Merkow'),
(25, 'Jim Breithaupt'),
(26, 'John P.Hayes'),
(27, 'Ranjan Parekh'),
(28, 'Ramesh Gaonkar'),
(29, 'Ivan Bratko'),
(30, 'Douglas C. Montgomery'),
(31, 'Thomas A. Powell'),
(32, 'Keith Strassberg'),
(33, 'Dhananjay M. Dhamdhere'),
(34, 'John C Martin'),
(35, 'Willian Stallings'),
(36, 'Donald D.Hearn'),
(37, 'M. Pauline Baker'),
(38, 'Abaraham Silberschatz');

-- --------------------------------------------------------

--
-- Table structure for table `author_group`
--

CREATE TABLE `author_group` (
  `author_group_Id` int(11) NOT NULL,
  `book_Id` int(11) NOT NULL,
  `author_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `author_group`
--

INSERT INTO `author_group` (`author_group_Id`, `book_Id`, `author_Id`) VALUES
(17, 17, 11),
(18, 17, 12),
(19, 18, 11),
(20, 18, 13),
(22, 22, 15),
(23, 22, 16),
(24, 22, 17),
(25, 22, 16),
(26, 22, 17),
(27, 23, 16),
(28, 23, 17),
(29, 24, 11),
(32, 26, 11),
(33, 26, 16),
(40, 32, 19),
(41, 33, 21),
(42, 33, 20),
(43, 34, 22),
(44, 35, 23),
(45, 36, 25),
(46, 36, 24),
(47, 37, 26),
(48, 38, 27),
(49, 39, 28),
(50, 40, 29),
(51, 41, 30),
(52, 42, 19),
(53, 43, 31),
(54, 44, 32),
(55, 45, 33),
(56, 46, 34),
(57, 47, 35),
(58, 48, 36),
(59, 48, 37),
(60, 48, 15),
(61, 49, 35),
(62, 50, 38),
(63, 51, 35),
(64, 52, 35),
(65, 53, 19),
(66, 53, 14),
(67, 53, 34);

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `books_Id` int(11) NOT NULL,
  `book_Name` varchar(100) NOT NULL,
  `book_ISBN` varchar(15) NOT NULL,
  `book_Hash` varchar(256) NOT NULL,
  `book_Publisher` int(11) NOT NULL,
  `book_Available` tinyint(4) DEFAULT 0,
  `book_Total` varchar(5) NOT NULL,
  `book_Quantity` int(10) NOT NULL,
  `book_Return` tinyint(4) NOT NULL DEFAULT 0,
  `book_Image` varchar(50) DEFAULT NULL,
  `book_PDF` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`books_Id`, `book_Name`, `book_ISBN`, `book_Hash`, `book_Publisher`, `book_Available`, `book_Total`, `book_Quantity`, `book_Return`, `book_Image`, `book_PDF`) VALUES
(17, 'Official book 2', '982137917', '5f14825df7fe079a7f724005bce1a471dbc8f108dea065fe6b9e2f8b49e26af0', 1, 1, '5', 0, 0, 'Asd', 'Asd'),
(18, 'Official book 3', '5468451236545', 'bf99f062e92229bf297db423fcd005734f8e461be3ac9a1f53b858c95ee6110', 1, 0, '5', 5, 0, 'Asd', 'Asd'),
(19, 'Official book 4', '9875213456124', '27518b966acceb369840fff1300685601055a5c91ba65adfa46599f5ef21101e', 1, 1, '01', 1, 0, 'Asd', 'Asd'),
(22, 'International Cuisine & Food Production Management', '9780198073895', '8a36fe784bbf700743752ad8dc8cd8c184d9c8fc9b0c1e5453c450344aed2d88', 36, 1, '6', 4, 0, 'Asd', 'Asd'),
(23, 'Introduction to Management in the Hospitality Industry', '9788126537259', '8a36fe784bbf700743752ad8dc8cd8c184d9c8fc9b0c1e5453c450344aed2d88', 36, 1, '7', 7, 0, 'Asd', 'Asd'),
(24, 'Official book 1', '123123123', '999ae948827d7244d37ecb1153c0134342ce507224fc9102d3d5f0c679498780', 1, 1, '3', 3, 0, 'Asd', 'Asd'),
(26, 'Test 2202', '4565321545455', 'be53a9bb3ecef9f168b92860d805b1e0a70f5bc832eb67fb4ecc7556e689196a', 1, 1, '4', 3, 0, 'Asd', 'Asd'),
(28, 'Test 2205', '1233212312312', 'c54f7b86539e0b759813341f8303fb1952a835b4d9a9432c0f9e290fc4888917', 1, 0, '3', 3, 0, 'Asd', 'Asd'),
(32, 'Operating Systems Design and Implementation', '9788120329553', '5b49c90ceffd4bfb68135ebfb3c2ca1059584531b31c062612a60ff91a7b65a7', 40, 1, '16', 16, 0, 'Asd', 'Asd'),
(33, 'C++ How To Program', '9788120349995', '53f9ee81041c233b169a015de683569bc12d9989b6d9702beda6e864ebd60e3e', 1, 1, '32', 32, 0, 'Asd', 'Asd'),
(34, 'Modern Information Retrival', '9788131709771', '61be330be6d35a748ea58b4481eda1576d31a6ec6ff550abaf5e1ea86362b60b', 41, 1, '14', 14, 0, 'Asd', 'Asd'),
(35, 'Applied Numerical Analysis', '9788131717400', '4e372ffd1b3202337bb374d1fd58bc3f9927ffe15dfec22f993d0eff42d291ad', 41, 1, '15', 15, 0, 'Asd', 'Asd'),
(36, 'Information Security-Principles and Practices', '9788131712887', 'd64088333da6e4a0b7735d4852c04fa4a36ba498df81903cc487af7f75e5c72f', 41, 1, '7', 7, 0, 'Asd', 'Asd'),
(37, 'Computer Architecture and Organization', '9781259028564', '5dc9508ab6ec3817ab7b83d615c4e2722a93f9e8163309faa0ccf877bd68ef6f', 42, 0, '1', 1, 0, 'Asd', 'Asd'),
(38, 'Principles of Multimedia', '9781259006500', 'f7ee9d60c04dbf5c0ca5f1ec27253d640f0a5eb08ce7258d8abb8f3ddf85d013', 42, 1, '4', 4, 0, 'Asd', 'Asd'),
(39, 'Microprocessor Architecture, Programming and Applications with the 8085', '9788187972884', '3ca0e5c48b4cfd714b5099001dc61cd9169312f1cff067887367ff73911786e9', 43, 1, '14', 13, 0, 'Asd', 'Asd'),
(40, 'PROLOG Programming for Artificial Intelligence', '9788131711347', '79096189cf84a129e574625f361ec3229a595bb1f2dde08a59c4779d66972064', 41, 1, '11', 11, 0, 'Asd', 'Asd'),
(41, 'Design and Analysis of Experiments', '9788126540501', '8b40974f46fd2dd090b6ac69b78d90764bfdc6b00235f8bf239fbfb136a238ce', 44, 1, '5', 5, 0, 'Asd', 'Asd'),
(42, 'Distributed Operating System', '9788177581799', '4ba53b71e98cf0b72c25a54ecc2ec283dd4bffe02a1a837bdea5f165147598f1', 41, 1, '3', 3, 0, 'Asd', 'Asd'),
(43, 'HTML & CSS : The Complete Reference, Fifth Edition', '9780070701946', '2f73bad03d6ca58673d0d5f770dd6f77f4712c62f45865a9847229f87be9fa25', 42, 0, '1', 0, 0, 'Asd', 'Asd'),
(44, 'Network Security', '9780070586710', '6b83059527d58205d0c90f33e6d4e38a644cc65d6c325b020d6c71cb88051bed', 42, 0, '1', 1, 0, 'Asd', 'Asd'),
(45, 'Operating Systems- A Concept-Based Approach', '9781259005589', 'd2d4ab0fc0eacbf579f2a26ee5d9c2504b018204cf1f7ac864c5da81c7f1b8e4', 42, 1, '2', 2, 0, 'Asd', 'Asd'),
(46, 'Introduction To Languages and The Theory of Computation', '9780070660489', 'ae2bf7e794cd032210b44e32664525eef15dfd4bf54ca707786731e13abfeed', 42, 1, '4', 4, 0, 'Asd', 'Asd'),
(47, 'Operating Systems', '8120321022', '92bb510565c598255930ff490ab7c1de59d03fd71e9656fc6fa02052cc41cb5e', 45, 0, '1', 1, 0, 'Asd', 'Asd'),
(48, 'Computer Graphics: C Version', '9788177587654', 'd5595dd89874e7157d6931e56546a5496e110cb55e0d0b395e842270c3fe3844', 46, 0, '1', 1, 0, 'Asd', 'Asd'),
(49, 'Operating Systems: Internals and Design Principles', '9788131725283', '2cf67fb96dcb167db279916c88cf39679a8a4a1637e4f1c520c70bfdd8666752', 41, 1, '4', 4, 0, 'Asd', 'Asd'),
(50, 'Operating System Concepts', '9788126520510', 'bc7dddf4d7c5c9d8cbd1822be67e10d71180fba0806fd103daf141fb040fbeb3', 44, 1, '3', 3, 0, 'Asd', 'Asd'),
(51, 'Operating Systems: Internals and Design Principles', '9789352866717', '62bd8a3a18a1547eef3e0501c567f73442ad6f0a5695c157e9ff753343c602ef', 41, 0, '1', 1, 0, 'Asd', 'Asd'),
(52, 'Crptyography and Network Security', '9789332585225', '1fe70136a5570056f2bfb1bb4421615027f0411f90983e05010eab362d7d6a2a', 41, 1, '14', 12, 0, 'Asd', 'Asd'),
(53, 'test 1000', '3213123123123', 'aeaacffbce1945df45e8cfdda0da289de81821b700764bd8df5a45141d1f22bd', 42, 1, '12', 11, 0, 'Asd', 'Asd');

-- --------------------------------------------------------

--
-- Table structure for table `borrower`
--

CREATE TABLE `borrower` (
  `borrower_Id` int(11) NOT NULL,
  `patron_Id` int(11) NOT NULL,
  `book_Id` int(11) NOT NULL,
  `admin_Id` int(11) NOT NULL,
  `borrowed_Date` date NOT NULL DEFAULT current_timestamp(),
  `due_Date` date NOT NULL,
  `returned` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `borrower`
--

INSERT INTO `borrower` (`borrower_Id`, `patron_Id`, `book_Id`, `admin_Id`, `borrowed_Date`, `due_Date`, `returned`) VALUES
(1, 3, 18, 1, '2022-04-01', '2022-04-16', '2022-06-07'),
(2, 3, 22, 1, '2022-04-02', '2022-05-04', '2022-06-07'),
(3, 3, 17, 1, '2022-04-02', '2022-05-05', '2022-06-07'),
(4, 3, 19, 1, '2022-04-02', '2022-05-05', '2022-08-16'),
(5, 2, 18, 1, '2022-04-05', '2022-05-05', '2022-04-08'),
(6, 2, 22, 1, '2022-04-20', '2022-05-05', '2022-05-29'),
(7, 3, 17, 1, '2022-04-22', '2022-05-07', '2022-06-07'),
(8, 3, 19, 1, '2022-04-22', '2022-05-07', '2022-08-16'),
(9, 3, 22, 1, '2022-04-22', '2022-05-07', '2022-06-07'),
(10, 3, 18, 1, '2022-04-22', '2022-05-07', '2022-06-07'),
(11, 3, 22, 1, '2022-04-22', '2022-05-07', '2022-06-07'),
(12, 3, 17, 1, '2022-04-22', '2022-05-07', '2022-06-07'),
(13, 3, 17, 1, '2022-04-22', '2022-05-07', '2022-06-07'),
(14, 2, 26, 1, '2022-05-29', '2022-06-13', '2022-05-29'),
(15, 2, 28, 1, '2022-05-29', '2022-06-13', '2022-05-29'),
(16, 2, 22, 1, '2022-05-29', '2022-06-13', '2022-05-29'),
(17, 6, 25, 1, '2022-05-29', '2022-06-13', NULL),
(18, 6, 26, 1, '2022-05-29', '2022-06-13', NULL),
(19, 2, 26, 1, '2022-05-29', '2022-06-13', '2022-05-29'),
(20, 2, 22, 1, '2022-05-29', '2022-06-13', NULL),
(21, 3, 18, 1, '2022-06-07', '2022-06-22', '2022-06-07'),
(22, 3, 19, 1, '2022-06-07', '2022-06-22', '2022-08-16'),
(23, 3, 22, 1, '2022-06-07', '2022-06-22', '2022-06-07'),
(24, 3, 18, 1, '2022-06-07', '2022-06-22', '2022-06-07'),
(25, 3, 19, 1, '2022-06-07', '2022-06-22', '2022-08-16'),
(26, 3, 22, 1, '2022-06-07', '2022-06-22', NULL),
(27, 7, 32, 1, '2022-08-14', '2022-08-29', '2022-08-17'),
(28, 7, 32, 1, '2022-08-17', '2022-09-01', '2022-08-17'),
(29, 7, 43, 1, '2022-08-17', '2022-09-01', NULL),
(30, 9, 52, 1, '2022-08-17', '2022-09-01', NULL),
(31, 7, 39, 1, '2022-08-17', '2022-09-01', NULL),
(32, 7, 52, 1, '2022-08-17', '2022-09-01', NULL),
(33, 8, 53, 1, '2022-08-17', '2022-09-01', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `facultys`
--

CREATE TABLE `facultys` (
  `faculty_Id` int(11) NOT NULL,
  `faculty_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facultys`
--

INSERT INTO `facultys` (`faculty_Id`, `faculty_Name`) VALUES
(2, 'BBA'),
(3, 'BCA'),
(4, 'BBM'),
(5, 'CSIT'),
(6, 'BIT'),
(7, 'MBA'),
(8, 'BHM'),
(9, 'BCS');

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `genre_Id` int(11) NOT NULL,
  `genre_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`genre_Id`, `genre_Name`) VALUES
(1, 'Sci-fi'),
(6, 'Romance'),
(7, 'Horror'),
(8, 'Action'),
(10, 'Comedy'),
(11, 'Spice of Life'),
(12, 'Adventure'),
(13, 'Fantasy'),
(14, 'AI'),
(15, 'Test Genre 9000'),
(16, 'IT'),
(17, 'dfads');

-- --------------------------------------------------------

--
-- Table structure for table `genre_group`
--

CREATE TABLE `genre_group` (
  `genre_group_Id` int(11) NOT NULL,
  `book_Id` int(11) NOT NULL,
  `genre_Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre_group`
--

INSERT INTO `genre_group` (`genre_group_Id`, `book_Id`, `genre_Id`) VALUES
(6, 17, 1),
(7, 17, 6),
(8, 18, 6),
(9, 18, 8),
(10, 19, 6),
(11, 19, 7),
(12, 19, 8),
(17, 24, 1),
(18, 24, 6),
(19, 24, 7),
(20, 24, 12),
(23, 26, 8),
(24, 26, 1),
(35, 34, 16),
(36, 35, 16),
(37, 36, 16),
(38, 37, 16),
(39, 38, 16),
(40, 39, 16),
(41, 40, 14),
(42, 40, 16),
(43, 41, 16),
(44, 42, 16),
(45, 43, 16),
(46, 44, 16),
(47, 45, 16),
(48, 46, 16),
(49, 47, 16),
(50, 48, 16),
(51, 49, 16),
(52, 50, 16),
(53, 51, 16),
(54, 52, 16),
(55, 53, 12),
(56, 53, 16),
(57, 53, 6);

-- --------------------------------------------------------

--
-- Table structure for table `patron`
--

CREATE TABLE `patron` (
  `patron_Id` int(11) NOT NULL,
  `patron_Name` varchar(50) NOT NULL,
  `patron_Roll` varchar(50) NOT NULL,
  `patron_Email` varchar(50) NOT NULL,
  `patron_Phone` varchar(11) NOT NULL,
  `patron_Hash` varchar(256) NOT NULL,
  `patron_Hash2` varchar(256) NOT NULL,
  `patron_Faculty` int(11) NOT NULL,
  `patron_Image` varchar(100) NOT NULL,
  `patron_Qty` int(11) NOT NULL,
  `fine` int(11) NOT NULL,
  `flagged` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patron`
--

INSERT INTO `patron` (`patron_Id`, `patron_Name`, `patron_Roll`, `patron_Email`, `patron_Phone`, `patron_Hash`, `patron_Hash2`, `patron_Faculty`, `patron_Image`, `patron_Qty`, `fine`, `flagged`) VALUES
(1, 'Ashesh Shakya8', '412311', 'ashesh112233@gmail.com', '1111111111', '4d1f434a5b185ae200607647010306db14670b65e0fd0f24fbb4a02465262b0b', '90d6cf4868a1fb6fe61a03b37b3b114211f23b415a11b2490664fb285c413cd9', 2, 'image', 0, 0, 0),
(2, 'User 1', '111111', 'user1@gmail.com', '1111111111', 'cefd34e47321ac3fef0504d81af2520bcd0581c425222614f71922fa5899180e', 'a0220470c1afd94dba0343fad39ab45f90cedfa2ff9c9e8093c5ba3c6e7427c', 2, 'image', 1, 0, 1),
(3, 'User 2', '222222', 'user2@gmail.com', '2222222222', 'd72d804b5bdda3a44988327a4871d61e9b2e84834b773c44dc338c78c801a2df', '902e35721f46e291c4613c0904421f812e0bdc637bf847afe1198be59a4586cf', 2, 'image', 1, 0, 0),
(4, 'User 3', '333333', 'user3@gmail.com', '3333333333', '558cd6bc4647f7250e1779bc47a5da4dc436e196d0653ad8b115fd62e8806460', '4584ca024769d94df61b02e65dd511f09b339b82efe05f0fe3df627015ee90f2', 2, 'image', 0, 0, 0),
(5, 'test12', '123546', 'test12@gmail.com', '8888888888', 'b2205be693da526b5c2f40e94d71f05873a755dc6f8e48307416ba353e270c9d', '90d6cf4868a1fb6fe61a03b37b3b114211f23b415a11b2490664fb285c413cd9', 2, 'image', 0, 100, 0),
(6, 'Depa hadka', '180603', 'depa@gmail.com', '9865329865', '13439db66288519718fde63e4e736b18d5ea772a93ef874772eea4e4c000bbcb', '13439db66288519718fde63e4e736b18d5ea772a93ef874772eea4e4c000bbcb', 2, 'image', 1, 0, 0),
(7, 'Nishma Mahrajan', '21010302', 'asdwe@gmail.com', '9867070099', 'd1c9456c5b12d3d633b21d45fb1c7fd71e52399f948253bc1a25cba98f290192', '923b4b8e2a0ac1afe014e0fecb2b4385166cd345095ff438c64296818a52e93a', 2, 'image', 3, 0, 0),
(8, 'Momik Shrestha', '180608', 'bca180608_momik@achsnepal.edu.np', '9860222338', 'ba23ba3285cbacaec4b92ef4ad7bec40ae0b6e14357299c283ae38e32b5e6e0e', 'e31e78be80153e16731049712f69f32a61ce08c49566873e47f98d06b2908a56', 3, 'image', 1, 0, 0),
(9, 'Shreeju Pradhan', '180622', 'bca180622_shreeju@achsnepal.edu.np', '9860560168', 'fa31360d0ce3457055cf0655d844be71f53f7d917473c6552a8b7f34380b6494', '7615f91d9025269f3ae9831201d4e187e3652c9a714d7ce48d05ab4fcdbec1c3', 3, 'image', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `publishers`
--

CREATE TABLE `publishers` (
  `publisher_Id` int(11) NOT NULL,
  `publisher_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `publishers`
--

INSERT INTO `publishers` (`publisher_Id`, `publisher_Name`) VALUES
(1, 'The Publishers'),
(36, 'Oxford University Press'),
(38, 'Austen, Jane'),
(40, ' PHI Learning'),
(41, 'Pearson India Education Services'),
(42, 'McGraw Hill Education '),
(43, 'Penram International Publishing'),
(44, 'John Wiley & Sons'),
(45, 'Prentice Hall of India'),
(46, 'Dorling Kindersley');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_Id`);

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`author_Id`);

--
-- Indexes for table `author_group`
--
ALTER TABLE `author_group`
  ADD PRIMARY KEY (`author_group_Id`),
  ADD KEY `book_Id` (`book_Id`),
  ADD KEY `author_Id` (`author_Id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`books_Id`),
  ADD KEY `book_Publisher` (`book_Publisher`);

--
-- Indexes for table `borrower`
--
ALTER TABLE `borrower`
  ADD PRIMARY KEY (`borrower_Id`);

--
-- Indexes for table `facultys`
--
ALTER TABLE `facultys`
  ADD PRIMARY KEY (`faculty_Id`);

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`genre_Id`);

--
-- Indexes for table `genre_group`
--
ALTER TABLE `genre_group`
  ADD PRIMARY KEY (`genre_group_Id`),
  ADD KEY `book_Id` (`book_Id`),
  ADD KEY `genre_Id` (`genre_Id`);

--
-- Indexes for table `patron`
--
ALTER TABLE `patron`
  ADD PRIMARY KEY (`patron_Id`),
  ADD KEY `patron_Faculty` (`patron_Faculty`);

--
-- Indexes for table `publishers`
--
ALTER TABLE `publishers`
  ADD PRIMARY KEY (`publisher_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `authors`
--
ALTER TABLE `authors`
  MODIFY `author_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `author_group`
--
ALTER TABLE `author_group`
  MODIFY `author_group_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `books_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `borrower`
--
ALTER TABLE `borrower`
  MODIFY `borrower_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `facultys`
--
ALTER TABLE `facultys`
  MODIFY `faculty_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `genre_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `genre_group`
--
ALTER TABLE `genre_group`
  MODIFY `genre_group_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `patron`
--
ALTER TABLE `patron`
  MODIFY `patron_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `publishers`
--
ALTER TABLE `publishers`
  MODIFY `publisher_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `author_group`
--
ALTER TABLE `author_group`
  ADD CONSTRAINT `author_group_ibfk_1` FOREIGN KEY (`book_Id`) REFERENCES `books` (`books_Id`),
  ADD CONSTRAINT `author_group_ibfk_2` FOREIGN KEY (`author_Id`) REFERENCES `authors` (`author_Id`);

--
-- Constraints for table `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_ibfk_1` FOREIGN KEY (`book_Publisher`) REFERENCES `publishers` (`publisher_Id`);

--
-- Constraints for table `genre_group`
--
ALTER TABLE `genre_group`
  ADD CONSTRAINT `genre_group_ibfk_1` FOREIGN KEY (`book_Id`) REFERENCES `books` (`books_Id`),
  ADD CONSTRAINT `genre_group_ibfk_2` FOREIGN KEY (`genre_Id`) REFERENCES `genres` (`genre_Id`);

--
-- Constraints for table `patron`
--
ALTER TABLE `patron`
  ADD CONSTRAINT `patron_ibfk_1` FOREIGN KEY (`patron_Faculty`) REFERENCES `facultys` (`faculty_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
