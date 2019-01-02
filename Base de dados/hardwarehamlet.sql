-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 02-Jan-2019 às 23:46
-- Versão do servidor: 10.1.37-MariaDB
-- versão do PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hardwarehamlet`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `builds`
--

CREATE TABLE `builds` (
  `build_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `build_type_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cpu_description` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gpu_description` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ram_description` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `likes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `builds`
--

INSERT INTO `builds` (`build_id`, `user_id`, `build_type_id`, `name`, `description`, `cpu_description`, `gpu_description`, `ram_description`, `price`, `likes`) VALUES
(1, 1, 1, 'Build teste', 'Apenas uma descrição da build de teste', 'Descrição do cpu teste', 'Descrição da gpu de teste', 'Descrição da ram de teste', 0, 0),
(2, 1, 2, 'Build teste2', 'Apenas uma descrição da build de teste', 'Descrição do cpu teste', 'Descrição da gpu de teste', 'Descrição da ram de teste', 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `build_components`
--

CREATE TABLE `build_components` (
  `build_id` int(11) NOT NULL,
  `component_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `build_components`
--

INSERT INTO `build_components` (`build_id`, `component_id`, `quantity`) VALUES
(1, 1, 1),
(1, 9, 1),
(1, 15, 1),
(1, 19, 3),
(1, 20, 1),
(1, 25, 1),
(1, 27, 3),
(1, 42, 2),
(1, 47, 1),
(2, 5, 1),
(2, 13, 1),
(2, 17, 1),
(2, 19, 3),
(2, 21, 1),
(2, 25, 1),
(2, 27, 3),
(2, 42, 2),
(2, 46, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `build_type`
--

CREATE TABLE `build_type` (
  `build_type_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `build_type`
--

INSERT INTO `build_type` (`build_type_id`, `name`) VALUES
(1, 'Budget'),
(2, 'Gaming'),
(3, 'Gaming EXPERT'),
(4, 'Designer/Editer'),
(5, 'Workstation'),
(6, 'Farming');

-- --------------------------------------------------------

--
-- Estrutura da tabela `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `build_id` int(11) NOT NULL,
  `content` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `components`
--

CREATE TABLE `components` (
  `component_id` int(11) NOT NULL,
  `component_type_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `brand` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(2000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `flg_available` tinyint(1) NOT NULL,
  `icon_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `components`
--

INSERT INTO `components` (`component_id`, `component_type_id`, `user_id`, `brand`, `name`, `description`, `price`, `flg_available`, `icon_url`) VALUES
(1, 1, 1, 'Intel', 'Core i7-9700K octa-core 3.7GHz', 'Processador de alto desempenho, com 6 cores e 12 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.7GHz. Socket: LGA 1151. Cache: 12 MB. TDP: 95 W', 430, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I79700K_3.jpg'),
(2, 1, 1, 'Intel', 'Core i7-8700K hexa-core 3.6GHz', 'Processador de alto desempenho, com 8 cores e 8 threads, perfeito para gaming e streaming e edição de vídeo/fotografia. Frequências até 4.9GHz. Socket: LGA 1151. Cache: 12 MB. TDP: 95 W', 470, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I78700K.jpg'),
(3, 1, 1, 'Intel', 'Core i5-9600K hexa-core 3.7GHz', 'Processador de alto desempenho, com 6 cores e 6 threads, perfeito para gaming e streaming. Frequências até 4.6GHz. Socket: LGA 1151. Cache: 9 MB. TDP: 95 W', 299.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I59600K_3.jpg'),
(4, 1, 1, 'Intel', 'Core i5-8600K hexa-core 3.6GHz', 'Processador de alto desempenho, com 6 cores e 6 threads, perfeito para gaming e streaming. Frequências até 4.3GHz. Socket: LGA 1151. Cache: 9 MB. TDP: 95 W', 324.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I58600K.jpg'),
(5, 1, 1, 'AMD', 'Ryzen 7 2700X octa-core 3.4GHz', 'Processador de alto desempenho, com 8 cores e 16 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.3GHz. Socket: AM4. Cache: 20MB. TDP: 105W', 329.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD270XBGAFBOX.jpg'),
(6, 1, 1, 'AMD', 'Ryzen 7 1700X octa-core 3.7GHz', 'Processador de alto desempenho, com 8 cores e 16 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.3GHz. Socket: AM4. Cache: 16MB. TDP: 95W', 229.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD170XBCAEWOF.jpg'),
(7, 1, 1, 'AMD', 'Ryzen Threadripper 2950X 16-core 3.5GHz', 'Processador de alto desempenho, com 16 cores e 32 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.4GHz. Socket: TR4. Cache: 32MB. TDP: 180W', 949.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD295XA8AFWOF_3.jpg'),
(8, 1, 1, 'AMD', 'Ryzen Threadripper 1950X 16-core 3.4GHz', 'Processador de alto desempenho, com 16 cores e 32 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4GHz. Socket: TR4. Cache: 32MB. TDP: 180W', 779.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD195XA8AEWOF.jpg'),
(9, 2, 1, 'NVidia', 'RTX 2080 Ti Asus ROG Strix 11GB', 'Placa gráfica com tecnologia Ray Tracing de ultima geração. A melhor placa gráfica para jogos extremos na resolução 4K.Memória: 11GB GDDR5X\r\nNúcleos CUDA: 4352\r\nMemória: 11GB GDDR6\r\nClock GPU:\r\nModo OC - GPU Boost Clock: 1665 MHz, GPU Base Clock: 1350 MHz\r\nModo Gaming (Default) - GPU Boost Clock: 1650 MHz, GPU Base Clock: 1350 MHz', 1424.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90YV0CC0-M0NM00.jpg'),
(10, 2, 1, 'NVidia', 'GTX 1080 Ti Asus ROG Strix 11GB', 'Placa gráfica da geração 10 da NVidia. Ideal para gaming e edição de video/fotografia. Memória: 11GB GDDR5X\r\nEngine Clock: \r\nModo OC - Clock Boost GPU: 1708 MHz | Clock Base GPU: 1594 MHz\r\nModo Gaming (Default) - Clock Boost GPU: 1683 MHz | Clock Base GPU: 1569 MHz\r\nNúcleos CUDA: 3584', 899.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90yv0am0-m0nm00_3.jpg'),
(11, 2, 1, 'NVidia', 'PNY Quadro P5000 16GB', 'Placa gráfica de gama superior, especificamente desenhada para design 3D e edição/render de videos e fotografias. Núcleos CUDA: 2560\r\nMemória: 16GB GDDR5X', 1890.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/V/C/VCQP5000-PB.jpg'),
(12, 2, 1, 'NVidia', 'PNY Quadro P4000 8GB', 'Placa gráfica de gama superior, especificamente desenhada para design 3D e edição/render de videos e fotografias. Núcleos CUDA: 1792\r\nMemória: 8GB GDDR5', 949.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/V/C/VCQP4000-PB.jpg'),
(13, 2, 1, 'AMD', 'Radeon RX590 Asus ROG Strix', 'Placa gráfica de ultima geração, topo de gama da série 500 da AMD Radeon. Ideal para um computador all around. Memória: 8GB GDDR5\r\nEngine Clock: 1565 MHz (Modo OC) / 1545 MHz (Modo Gaming)\r\nStream Processors: 2304\r\nClock de Memória: 8000 MHz', 429.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90YV0AK2-M0NA00.jpg'),
(14, 2, 1, 'AMD', 'Radeon RX580 Asus ROG Strix', 'Placa gráfica de ultima geração da AMD Radeon. Ideal para um computador all around. Memória: 8GB GDDR5\r\nEngine Clock: 1360 MHz (Modo OC) / 1340 MHz (Modo Gaming)\r\nStream Processors: 2304\r\nClock de Memória: 8000 MHz', 379.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/R/X/RX580-OC8GB.jpg'),
(15, 3, 1, 'MSI', 'MPG Z390 GAMING EDGE AC', 'Socket: LGA1151.\r\nPortas USB:\r\n3 x USB 3.1 (Gen2) Type A+C \r\n6 x USB 3.1 (Gen1) \r\n6 x USB 2.0\r\nMulti-GPU:\r\nSuporta AMD CrossFire\r\nNVIDIA SLI\r\nArmazenamento:\r\n2 x Turbo M.2 slots (Intel Optane) \r\n6 x SATA 6Gb/s\r\nPortas Traseiras:\r\n1 x PS/2 Combo Port \r\n1 x LAN port \r\n2 x Wireless / Bluetooth \r\n5 x HD Audio Connectors \r\n2 x USB 2.0 \r\n1 x DisplayPort \r\n1 x HDMI \r\n2 x USB 3.1 Gen2 Type-A + C \r\n2 x USB 3.1 Gen1 \r\n1 x Optical S/PDIF out\r\nForm Factor:Atx', 179.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/1/911-7B17-002_7.jpg'),
(16, 3, 1, 'Asus', 'ROG STRIX Z390-F GAMING', 'Socket: LGA1151.\r\nPortas USB:\r\n1 x Conector USB 3.1 Gen 1 painel frontal \r\n4 x USB 3.1 Gen 2 (3 Type-A e 1 Type-C no painel traseiro) \r\n4 x USB 3.1 Gen 1 (2 no painel traseiro, 2 através dos conectores USB internos) \r\n6 x USB 2.0 ( 2 no painel traseiro, 4 através dos conectores USB internos)\r\nMulti-GPU:\r\nAMD CrossFire\r\nNVIDIA SLI\r\nPortas Traseiras:\r\n1 x PS/2 teclado/rato \r\n1 x HDMI \r\n1 x DisplayPort \r\n2 x USB2.0 \r\n2 x USB 3.1 Gen1 \r\n4 x USB3.1 Gen2 (3 x Type-A e 1 x Type-C) \r\n1 x Anti-surge LAN (RJ45) \r\n5 x Jacks de áudio \r\n1 x Saída Óptica S/PDIF\r\nForm Factor:ATX', 218.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90MB0YG0-M0EAY0_4.jpg'),
(17, 3, 1, 'MSI', 'X399 SLI Plus', 'Socket:TR4.\r\nPortas USB:\r\nChipset ASMedia ASM3142: 1 x Porta USB 3.1 Gen 2 (SuperSpeed USB 10Gbps) Tipo C no painel traseiro \r\n1 x Porta USB 3.1 Gen 2 (SuperSpeed USB 10Gbps) Tipo A no painel traseiro \r\nChipset AMD X399: 1 x Porta USB 3.1 Gen 2 Tipo C (SuperSpeed USB 10Gbps) através do conector USB interno \r\n4 x Portas USB 3.1 Gen 1 (SuperSpeed USB) disponíveis através dos conectores USB internos \r\n6 x Portas USB 2.0 (High-Speed USB) (2 x Portas Tipo A no painel traseiro, 4 x Portas disponíveis através dos conectores USB internos) \r\nProcessador AMD: 8 x Portas USB 3.1 Gen 1 (SuperSpeed USB 10Gbps) Tipo no painel traseiro\r\nMulti-GPU:\r\nSuporta a Tecnologia 4-Way NVIDIA SLI \r\nSuporta a Tecnologia 4-Way AMD CrossFire\r\nForm Factor:ATX', 319.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/9/1/911-7B09-009.png'),
(18, 3, 1, 'MSI', 'X470 GAMING M7 AC', 'Socket: AM4.\r\nPortas USB:\r\n3 x Portas USB 3.1 Gen 2 Tipo A e Tipo C \r\n8 x Slots USB 3.1 Gen 1 \r\n6 x Slots USB 2.0\r\nMulti-GPU:\r\nSuporta 2-Way SLI / 3-Way CrossFire\r\nPortas Traseiras:\r\n1 x Botão de limpeza CMOS \r\n1 x Botão BIOS FLASHBACK+ \r\n1 x Porta PS/2 para dispositivos gaming \r\n2 x Portas USB 2.0 \r\n4 x Portas USB 3.1 Gen 1 \r\n1 x Módulo Wi-Fi/Bluetooth \r\n1 x Porta LAN RJ45 \r\n1 x Porta USB 3.1 Gen 2 Tipo A \r\n1 x Porta USB 3.1 Gen 2 Tipo C \r\n5 x Jacks de áudio HD \r\n1 x Saída Óptica S/PDIF\r\nForm Factor:ATX', 284.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/4/7/4719072564247_1.png'),
(19, 4, 1, 'Raijintek', 'IRIS 12 Rainbow RGB Orcus PWM 120mm', 'Ventoinha Raijintek IRIS 12 Rainbow RGB Orcus de 120 mm\r\nVelocidade de rotação até 1800 RPM\r\nVolume de ruído de apenas 23 dBA\r\nIluminação RGB', 9.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/0/R/0R40A00046.jpg'),
(20, 5, 1, 'Noctua', 'NH-U9B SE2', 'Compatibilidade de socket: Intel LGA1366, LGA1156, LGA1155, LGA1150, LGA775, LGA2011 \r\nAsus X-socket™ \r\nAMD AM2, AM2+, AM3, AM3+, FM1, FM2, FM2+\r\nDimensão	125 x 95 x 71 mm \r\n125 x 95 x 95/120 mm (com ventoinhas) \r\nVelocidade de ventoinha: 1600 RPM', 52.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/N/C/NCTNHU9B.jpg'),
(21, 5, 1, 'Noctua', 'NH-U12S TR4-SP3 Threadripper Edition', '› Dissipador de CPU para socket AMD TR4 / SP3\r\n› Ventoinha de 120 mm incluída\r\n› Baixo nível de ruído\r\nCompatibilidade: AMD TR4 e SP3', 69.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/N/H/NH-U12S-TR4-SP3.jpg'),
(22, 5, 1, 'Cooler Master', 'MasterAir MA410M RGB', '› Dissipador de CPU para socket Intel e AMD\r\n› Com duas ventoinhas de 120 mm\r\n› Iluminação RG\r\nCompatibilidade de socket:\r\nIntel LGA 2066 / 2011-v3 / 2011 / 1151 / 1150 / 1155 / 1156 / 1366 \r\nAMD AM4 / AM3 + / AM3 / AM2 + / AM2 / FM2 + / FM2 / FM1', 65.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/M/A/MAM-T4PN-218PC-R1.jpg'),
(23, 6, 1, 'be quiet!', 'Straight Power 11 550W 80+ Gold', '› Fonte modular be quiet! Straight Power 11\r\n› Fonte de 550W\r\n› Certificação 80 Plus Gold\r\n› Operação inaudível sem comprometer em desempenho de energia', 105.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/N/BN281_1_1.jpg'),
(24, 6, 1, 'be quiet!', 'Straight Power 11 750W 80+ Gold', '› Fonte modular be quiet! Straight Power 11\r\n› Fonte de 750W\r\n› Certificação 80 Plus Gold\r\n› Operação inaudível sem comprometer em desempenho de energia', 129.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/B/N/BN283.jpg'),
(25, 6, 1, 'be quiet!', 'Straight Power 11 1000W 80+ Gold', '› Fonte modular be quiet! Straight Power 11\r\n› Fonte de 1000W\r\n› Certificação 80 Plus Gold\r\n› Operação inaudível sem comprometer em desempenho de energia', 195.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/B/N/BN285.jpg'),
(26, 7, 1, 'Samsung', '860 EVO 500GB SATA III', '› Disco SSD com tecnologia inovadora V-NAND\r\n› melhor desempenho de leitura e gravação\r\n› Capacidade de 500GB\r\n› Interface SATA III', 99, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/M/Z/MZ-76E1T0B_2FEU.jpg'),
(27, 7, 1, 'Samsung', '860 EVO 1TB SATA III', '› Disco SSD com tecnologia inovadora V-NAND\r\n› melhor desempenho de leitura e gravação\r\n› Capacidade de 1TB\r\n› Interface SATA III', 194.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/M/Z/MZ-76E1T0B_2FEU.jpg'),
(28, 8, 1, 'Seagate', 'Barracuda 1TB 7200rpm SATA III 64MB', '› Disco rígido com capacidade de 1TB › Velocidade de 7200 rpm› Formato de 3.5', 41.8, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST1000DM010.jpg'),
(29, 8, 1, 'Seagate', 'Barracuda 2TB 7200rpm SATA III 64MB', 'Disco SATA III 3.5\' com 2TB, velocidade de 7200rpm e uma qualidade excelente SEAGATE.', 68.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST2000DM006_1_1.jpg'),
(30, 8, 1, 'Seagate', '3TB IronWolf 5900rpm SATA III 64MB', 'Disco Seagate IronWolf com capacidade de 1TB e 5900RPM. Ideal para sistemas NAS.', 109.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST3000VN007.jpg'),
(31, 8, 1, 'Seagate', 'Barracuda 4TB 5400rpm SATA III 256MB', 'Barracuda 4TB 5400rpm SATA III 256MB', 108.9, 1, 'https://www.chip7.pt/245716-thickbox_default/seagate-barracuda-st4000dm004-4000gb-serial-ata-iii-unidade-de-disco-rigido.jpg'),
(32, 8, 1, 'Western Digital', 'Western Digital Blue 1TB (7200rpm) SATA III 64MB', '› Disco rígido com capacidade de 1TB› Velocidade de 7200 rpm› Formato de 3.5', 43.4, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD10EZEX.jpg'),
(33, 8, 1, 'Western Digital', 'Western Digital Blue 2TB SATA III 64MB', '› Aumente a capacidade de armazenamento› Disco rígido com capacidade de 2TB› Velocidade de 5400 RPM› Formato de 3.5', 71.5, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD20EZRZ.jpg'),
(34, 8, 1, 'Western Digital', 'Western Digital Purple 3TB SATA III 64MB', '› Optimizado para sistemas de segurança de alta definição e funcionamento contínuo› Suporte a uma carga de trabalho nominal até 180 TB/ano› Suporta até 64 câmeras de vigilância› Baixo consumo e alta eficiência', 98.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD30PURZ_1_1.jpg'),
(35, 8, 1, 'Western Digital', 'Western Digital Blue 4TB SATA III 64MB', 'Aumente a capacidade de armazenamento do seu PC com o disco rígido WD Blue de 4TB, a marca projetada para PCs desktop e all-in-one.', 108.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD40EZRZ.jpg'),
(36, 9, 1, 'Seagate', 'SSHD Seagate 2TB + 8GB SSD FireCuda SATA III 64MB', 'O Disco Seagate FireCuda de 2TB SATA III é a solução de armazenamento de dados que ajuda a garantir uma gestão de dados acessível de longo prazo. Um disco híbrido com 8GB SSD e uma velocidade de rotação de 7200RPM, para os melhores acessos possíveis.', 109.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST2000DX002.jpg'),
(37, 9, 1, 'Seagate', 'SSHD Seagate 1TB + 8GB SSD FireCuda SATA III 64MB', '› O Disco Seagate FireCuda de 1TB SATA III é a solução de armazenamento de dados› Ajuda a garantir uma gestão de dados acessível de longo prazo› Um disco híbrido com 8GB SSD e uma velocidade de rotação de 7200RPM', 82.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST1000DX002.jpg'),
(38, 10, 1, 'HyperX', 'Fury 4GB (1x4GB) DDR4-2400MHz CL15', 'Capacidade: 4GB (1x4GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 13335 mm x 3424 mm', 39.5, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/H/X/HX424C15FB_2F4.jpg'),
(39, 10, 1, 'HyperX', 'Fury 8GB (1x8GB) DDR4-2400MHz CL15', 'Capacidade: 8GB (1x8GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 13335 mm x 3424 mm', 74.7, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/H/X/HX424C15FB_2F4.jpg'),
(40, 10, 1, 'HyperX', 'Fury 8GB (2x4GB) DDR4-2400MHz CL15', 'Capacidade: 8GB (2x4GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Dimensões: 13335 mm x 3424 mm', 84.9, 1, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/H/X/HX421C14FBK2_2F8.jpg'),
(41, 10, 1, 'HyperX', 'Fury 16GB (1x16GB) DDR4-2400MHz CL15', 'Capacidade: 16GB (1x16GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 133.35 mm x 34.24 mm', 144.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/2/0/20774_1.jpg'),
(42, 10, 1, 'HyperX', 'Fury 16GB (2x8GB) DDR4-2400MHz CL15', 'Capacidade: 16GB (2x8GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 133.35 mm x 34.24 mm', 159.3, 1, 'https://d3fa68hw0m2vcc.cloudfront.net/5ba/181576111.jpeg'),
(43, 10, 1, 'Corsair', 'Vengeance LPX 8GB (1x8GB) DDR4-2400MHz CL14', 'Série: Vengeance® LPX, Capacidade: Single Channel 4GB (4GBx1), Tipo: DDR4, Velocidade: 2400MHz, Latência: 16-16-16-39, Voltagem: 1.2v, Recursos: Intel XMP 2.0 (Extreme Memory Profile) Ready', 56.5, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/s/e/sem-t_tulo-3_16.jpg'),
(44, 10, 1, 'Corsair', 'Vengeance LPX 8GB (1x8GB) DDR4-2400MHz CL16', 'Série: Vengeance® LPX, Capacidade: Single Channel 8GB (8GBx1), Tipo: DDR4, Velocidade: 2400MHz, Latência: 16-16-16-39,Voltagem: 1.2v, Recursos: Intel XMP 2.0 (Extreme Memory Profile) Ready', 103.5, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/s/e/sem-t_tulo-3_16.jpg'),
(45, 10, 1, 'Corsair', 'Vengeance LPX 8GB (2x4GB) DDR4-2400MHz CL14', 'Série: Vengeance® LPX, Capacidade: Kit Dual-Channel 8GB (4GBx2), Tipo: DDR4, Velocidade: 2400MHz, Latência: 14-16-16-31, Voltagem: 1.2V, Recursos: Intel XMP 2.0 (Extreme Memory Profile) Ready', 112.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_31_7_7.jpg'),
(46, 11, 1, 'Nox', 'Caixa ATX Nox Modus Blue LED Edition Preta', 'Motherboards Compatíveis: ATX; micro ATX, Slots de expansão: 7, Sistema de refrigeração: Frontal: \r\nSuporta 1 x ventoinha 120mm (1 x incluída com LED azul), Traseira: Suporta 1 x ventoinha 80mm (opcional/não incluída)', 27.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_2_93.jpg'),
(47, 11, 1, 'Corsair', 'Caixa ATX Corsair Crystal 460X RGB Preta', 'Motherboards compatíveis: Mini-ITX, MicroATX, ATX, Slots de expansão: 7, Ventoinhas incluídas: Frontal: 3 x 120mm SP120 RGB LED', 169.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_2_62.jpg'),
(48, 11, 1, 'Corsair', 'Caixa Micro-ATX Corsair Carbide 88R com Janela Preta', 'Motherboards compatíveis: Mini-ITX, MicroATX, Slots de expansão: 4, Ventoinhas incluídas: Traseira: 1 x 120mm', 48.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_8_57.jpg'),
(49, 11, 1, 'Cooler Master', 'Caixa ATX Cooler Master MasterBox 5t com Janela Preta', 'Motherboards suportadas: ATX, Micro-ATX, Mini-ITX, Slots de Expansão: 7, Sistema de Refrigeração: Superior: Suporta 1 x ventoinha 120mm ou 1 x 140mm (opcionais/não incluídas); Frontal: Suporta 3 x ventoinhas 120mm ou 2 x 140mm (1 x 120mm incluída); Traseira: Suporta 1 x ventoinha 120mm (incluída)', 90.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_491.jpg'),
(50, 11, 1, 'NZXT', 'Caixa ATX NZXT S340 Special Edition com Janela Preta', 'Motherboards Compatíveis: Mini-ITX, MicroATX, ATX ; Slots de Expansão: 7; Sistema de Refrigeração:\r\nFrontal: Suporta 2 x ventoinhas 140mm ou 2 x ventoinhas 120mm (opcionais/não incluídas), Topo: Suporta 1 x ventoinha 140/120mm (inclui 1 x ventoinha 120mm FN V2), Traseira: Suporta 1 x ventoinha 120mm (inclui 1 x ventoinha 120mm FN V2)', 99.9, 1, 'https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/2/1/21891_1.jpg');

-- --------------------------------------------------------

--
-- Estrutura da tabela `component_type`
--

CREATE TABLE `component_type` (
  `component_type_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `component_type`
--

INSERT INTO `component_type` (`component_type_id`, `name`) VALUES
(1, 'Processador'),
(2, 'Placa gráfica'),
(3, 'Motherboard'),
(4, 'Ventoinha'),
(5, 'Cooler CPU'),
(6, 'Fonte de alimentação'),
(7, 'Disco SSD'),
(8, 'Disco HDD'),
(9, 'Disco SSHD'),
(10, 'Memória RAM'),
(11, 'Caixa');

-- --------------------------------------------------------

--
-- Estrutura da tabela `medals`
--

CREATE TABLE `medals` (
  `medal_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `icon` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount_likes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `medals`
--

INSERT INTO `medals` (`medal_id`, `name`, `icon`, `amount_likes`) VALUES
(1, '', '', 0),
(2, 'Begginer', '', 5),
(3, 'Intermediate', '', 50),
(4, 'Semi-pro', '', 500),
(5, 'Pro Builder', '', 2500),
(6, 'Build God All Mighty', '', 5000);

-- --------------------------------------------------------

--
-- Estrutura da tabela `titles`
--

CREATE TABLE `titles` (
  `title_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rep_amount` int(11) NOT NULL,
  `color` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `titles`
--

INSERT INTO `titles` (`title_id`, `name`, `rep_amount`, `color`) VALUES
(1, 'Member', 0, ''),
(2, 'Researcher', 150, '#00ff00'),
(3, 'Scavenger', 500, '#0000ff'),
(4, 'Moderator', 2500, '#cc0099'),
(5, 'Partner', 5000, '#dcd045'),
(6, 'Administrator', 9999, '#09f4e1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `title_id` int(11) NOT NULL,
  `usertype_id` int(11) NOT NULL,
  `medal_id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(61) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reputation` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `validation_code` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`user_id`, `title_id`, `usertype_id`, `medal_id`, `email`, `username`, `password`, `description`, `reputation`, `active`, `validation_code`) VALUES
(1, 6, 3, 1, 'hardwarehamlet.mail@gmail.com', 'hardwarehamlet', 'hardwarehamlet.arm123', 'Administrators of Hardware Hamlet', 9999, 1, 'Hamlet');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_type`
--

CREATE TABLE `user_type` (
  `usertype_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `user_type`
--

INSERT INTO `user_type` (`usertype_id`, `name`) VALUES
(1, 'common'),
(2, 'partner'),
(3, 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `builds`
--
ALTER TABLE `builds`
  ADD PRIMARY KEY (`build_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `build_type_id` (`build_type_id`);

--
-- Indexes for table `build_components`
--
ALTER TABLE `build_components`
  ADD PRIMARY KEY (`build_id`,`component_id`),
  ADD KEY `component_id` (`component_id`);

--
-- Indexes for table `build_type`
--
ALTER TABLE `build_type`
  ADD PRIMARY KEY (`build_type_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `build_id` (`build_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `components`
--
ALTER TABLE `components`
  ADD PRIMARY KEY (`component_id`),
  ADD KEY `component_type_id` (`component_type_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `component_type`
--
ALTER TABLE `component_type`
  ADD PRIMARY KEY (`component_type_id`);

--
-- Indexes for table `medals`
--
ALTER TABLE `medals`
  ADD PRIMARY KEY (`medal_id`);

--
-- Indexes for table `titles`
--
ALTER TABLE `titles`
  ADD PRIMARY KEY (`title_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `title_id` (`title_id`),
  ADD KEY `usertype_id` (`usertype_id`),
  ADD KEY `medal_id` (`medal_id`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`usertype_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `builds`
--
ALTER TABLE `builds`
  MODIFY `build_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `build_type`
--
ALTER TABLE `build_type`
  MODIFY `build_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `components`
--
ALTER TABLE `components`
  MODIFY `component_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `component_type`
--
ALTER TABLE `component_type`
  MODIFY `component_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `medals`
--
ALTER TABLE `medals`
  MODIFY `medal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `titles`
--
ALTER TABLE `titles`
  MODIFY `title_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `usertype_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `builds`
--
ALTER TABLE `builds`
  ADD CONSTRAINT `builds_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `builds_ibfk_2` FOREIGN KEY (`build_type_id`) REFERENCES `build_type` (`build_type_id`);

--
-- Limitadores para a tabela `build_components`
--
ALTER TABLE `build_components`
  ADD CONSTRAINT `build_components_ibfk_1` FOREIGN KEY (`build_id`) REFERENCES `builds` (`build_id`),
  ADD CONSTRAINT `build_components_ibfk_2` FOREIGN KEY (`component_id`) REFERENCES `components` (`component_id`);

--
-- Limitadores para a tabela `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`build_id`) REFERENCES `builds` (`build_id`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Limitadores para a tabela `components`
--
ALTER TABLE `components`
  ADD CONSTRAINT `components_ibfk_1` FOREIGN KEY (`component_type_id`) REFERENCES `component_type` (`component_type_id`),
  ADD CONSTRAINT `components_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Limitadores para a tabela `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`title_id`) REFERENCES `titles` (`title_id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`usertype_id`) REFERENCES `user_type` (`usertype_id`),
  ADD CONSTRAINT `users_ibfk_3` FOREIGN KEY (`medal_id`) REFERENCES `medals` (`medal_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
