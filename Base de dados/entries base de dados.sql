insert into medals (name,icon,amount_likes)
values
('','',0),
('Begginer', '', 5),
('Intermediate','', 50),
('Semi-pro','', 500),
('Pro Builder','',2500),
('Build God All Mighty','',5000);

insert into titles (name,rep_amount, color)
values
('Member',0,''),
('Researcher',150,'#00ff00'),
('Scavenger',500,'#0000ff'),
('Moderator',2500,'#cc0099'),
('Partner',5000,'#dcd045'),
('Administrator',9999,'#09f4e1');

insert into user_type (name)
values
('common'),
('partner'),
('admin');


insert into build_type (name)
values
('Budget'),
('Gaming'),
('Gaming EXPERT'),
('Designer/Editer'),
('Workstation'),
('Farming');

insert into component_type (name)
values
('Processador'),
('Placa gráfica'),
('Motherboard'),
('Ventoinha'),
('Cooler CPU'),
('Fonte de alimentação'),
('Disco SSD'),
('Disco HDD'),
('Disco SSHD'),
('Memória RAM'),
('Caixa');

insert into users (title_id,usertype_id,medal_id,email,username,password,description,reputation, active, validation_code)
values
(6,3,1,'hardwarehamlet.mail@gmail.com', 'hardwarehamlet','hardwarehamlet.arm123','Administrators of Hardware Hamlet', 9999, true, 'HamletAdmi');

insert into components(component_type_id, user_id, brand, name, description, price, flg_available, icon_url)
values  
(1,1,'Intel', 'Core i7-9700K octa-core 3.7GHz', 'Processador de alto desempenho, com 6 cores e 12 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.7GHz. Socket: LGA 1151. Cache: 12 MB. TDP: 95 W',430.0,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I79700K_3.jpg'),
(1,1,'Intel', 'Core i7-8700K hexa-core 3.6GHz', 'Processador de alto desempenho, com 8 cores e 8 threads, perfeito para gaming e streaming e edição de vídeo/fotografia. Frequências até 4.9GHz. Socket: LGA 1151. Cache: 12 MB. TDP: 95 W',470.0,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I78700K.jpg'),
(1,1,'Intel', 'Core i5-9600K hexa-core 3.7GHz', 'Processador de alto desempenho, com 6 cores e 6 threads, perfeito para gaming e streaming. Frequências até 4.6GHz. Socket: LGA 1151. Cache: 9 MB. TDP: 95 W',299.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I59600K_3.jpg'),
(1,1,'Intel', 'Core i5-8600K hexa-core 3.6GHz', 'Processador de alto desempenho, com 6 cores e 6 threads, perfeito para gaming e streaming. Frequências até 4.3GHz. Socket: LGA 1151. Cache: 9 MB. TDP: 95 W',324.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/X/BX80684I58600K.jpg'),
(1,1,'AMD', 'Ryzen 7 2700X octa-core 3.4GHz', 'Processador de alto desempenho, com 8 cores e 16 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.3GHz. Socket: AM4. Cache: 20MB. TDP: 105W',329.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD270XBGAFBOX.jpg'),
(1,1,'AMD', 'Ryzen 7 1700X octa-core 3.7GHz', 'Processador de alto desempenho, com 8 cores e 16 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.3GHz. Socket: AM4. Cache: 16MB. TDP: 95W',229.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD170XBCAEWOF.jpg'),
(1,1,'AMD', 'Ryzen Threadripper 2950X 16-core 3.5GHz', 'Processador de alto desempenho, com 16 cores e 32 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4.4GHz. Socket: TR4. Cache: 32MB. TDP: 180W',949.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD295XA8AFWOF_3.jpg'),
(1,1,'AMD', 'Ryzen Threadripper 1950X 16-core 3.4GHz', 'Processador de alto desempenho, com 16 cores e 32 threads, perfeito para gaming, streaming e edição de vídeo/fotografia. Frequências até 4GHz. Socket: TR4. Cache: 32MB. TDP: 180W',779.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/Y/D/YD195XA8AEWOF.jpg'),

(2,1,'NVidia', 'RTX 2080 Ti Asus ROG Strix 11GB', 'Placa gráfica com tecnologia Ray Tracing de ultima geração. A melhor placa gráfica para jogos extremos na resolução 4K.Memória: 11GB GDDR5X
Núcleos CUDA: 4352
Memória: 11GB GDDR6
Clock GPU:
Modo OC - GPU Boost Clock: 1665 MHz, GPU Base Clock: 1350 MHz
Modo Gaming (Default) - GPU Boost Clock: 1650 MHz, GPU Base Clock: 1350 MHz',1424.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90YV0CC0-M0NM00.jpg'),
(2,1,'NVidia', 'GTX 1080 Ti Asus ROG Strix 11GB', 'Placa gráfica da geração 10 da NVidia. Ideal para gaming e edição de video/fotografia. Memória: 11GB GDDR5X
Engine Clock: 
Modo OC - Clock Boost GPU: 1708 MHz | Clock Base GPU: 1594 MHz
Modo Gaming (Default) - Clock Boost GPU: 1683 MHz | Clock Base GPU: 1569 MHz
Núcleos CUDA: 3584',899.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90yv0am0-m0nm00_3.jpg'),
(2,1,'NVidia', 'PNY Quadro P5000 16GB', 'Placa gráfica de gama superior, especificamente desenhada para design 3D e edição/render de videos e fotografias. Núcleos CUDA: 2560
Memória: 16GB GDDR5X',1890.90,true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/V/C/VCQP5000-PB.jpg'),
(2,1,'NVidia', 'PNY Quadro P4000 8GB', 'Placa gráfica de gama superior, especificamente desenhada para design 3D e edição/render de videos e fotografias. Núcleos CUDA: 1792
Memória: 8GB GDDR5',949.90,true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/V/C/VCQP4000-PB.jpg'),
(2,1,'AMD', 'Radeon RX590 Asus ROG Strix', 'Placa gráfica de ultima geração, topo de gama da série 500 da AMD Radeon. Ideal para um computador all around. Memória: 8GB GDDR5
Engine Clock: 1565 MHz (Modo OC) / 1545 MHz (Modo Gaming)
Stream Processors: 2304
Clock de Memória: 8000 MHz',429.90,true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90YV0AK2-M0NA00.jpg'),
(2,1,'AMD', 'Radeon RX580 Asus ROG Strix', 'Placa gráfica de ultima geração da AMD Radeon. Ideal para um computador all around. Memória: 8GB GDDR5
Engine Clock: 1360 MHz (Modo OC) / 1340 MHz (Modo Gaming)
Stream Processors: 2304
Clock de Memória: 8000 MHz',379.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/R/X/RX580-OC8GB.jpg'),

(3,1, 'MSI', 'MPG Z390 GAMING EDGE AC','Socket: LGA1151.
Portas USB:
3 x USB 3.1 (Gen2) Type A+C 
6 x USB 3.1 (Gen1) 
6 x USB 2.0
Multi-GPU:
Suporta AMD CrossFire
NVIDIA SLI
Armazenamento:
2 x Turbo M.2 slots (Intel Optane) 
6 x SATA 6Gb/s
Portas Traseiras:
1 x PS/2 Combo Port 
1 x LAN port 
2 x Wireless / Bluetooth 
5 x HD Audio Connectors 
2 x USB 2.0 
1 x DisplayPort 
1 x HDMI 
2 x USB 3.1 Gen2 Type-A + C 
2 x USB 3.1 Gen1 
1 x Optical S/PDIF out
Form Factor:Atx',179.90, true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/1/911-7B17-002_7.jpg'),
(3,1,'Asus', 'ROG STRIX Z390-F GAMING','Socket: LGA1151.
Portas USB:
1 x Conector USB 3.1 Gen 1 painel frontal 
4 x USB 3.1 Gen 2 (3 Type-A e 1 Type-C no painel traseiro) 
4 x USB 3.1 Gen 1 (2 no painel traseiro, 2 através dos conectores USB internos) 
6 x USB 2.0 ( 2 no painel traseiro, 4 através dos conectores USB internos)
Multi-GPU:
AMD CrossFire
NVIDIA SLI
Portas Traseiras:
1 x PS/2 teclado/rato 
1 x HDMI 
1 x DisplayPort 
2 x USB2.0 
2 x USB 3.1 Gen1 
4 x USB3.1 Gen2 (3 x Type-A e 1 x Type-C) 
1 x Anti-surge LAN (RJ45) 
5 x Jacks de áudio 
1 x Saída Óptica S/PDIF
Form Factor:ATX',218.90,true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/9/0/90MB0YG0-M0EAY0_4.jpg'),
(3,1,'MSI','X399 SLI Plus','Socket:TR4.
Portas USB:
Chipset ASMedia ASM3142: 1 x Porta USB 3.1 Gen 2 (SuperSpeed USB 10Gbps) Tipo C no painel traseiro 
1 x Porta USB 3.1 Gen 2 (SuperSpeed USB 10Gbps) Tipo A no painel traseiro 
Chipset AMD X399: 1 x Porta USB 3.1 Gen 2 Tipo C (SuperSpeed USB 10Gbps) através do conector USB interno 
4 x Portas USB 3.1 Gen 1 (SuperSpeed USB) disponíveis através dos conectores USB internos 
6 x Portas USB 2.0 (High-Speed USB) (2 x Portas Tipo A no painel traseiro, 4 x Portas disponíveis através dos conectores USB internos) 
Processador AMD: 8 x Portas USB 3.1 Gen 1 (SuperSpeed USB 10Gbps) Tipo no painel traseiro
Multi-GPU:
Suporta a Tecnologia 4-Way NVIDIA SLI 
Suporta a Tecnologia 4-Way AMD CrossFire
Form Factor:ATX',319.90,true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/9/1/911-7B09-009.png'),
(3,1,'MSI', 'X470 GAMING M7 AC', 'Socket: AM4.
Portas USB:
3 x Portas USB 3.1 Gen 2 Tipo A e Tipo C 
8 x Slots USB 3.1 Gen 1 
6 x Slots USB 2.0
Multi-GPU:
Suporta 2-Way SLI / 3-Way CrossFire
Portas Traseiras:
1 x Botão de limpeza CMOS 
1 x Botão BIOS FLASHBACK+ 
1 x Porta PS/2 para dispositivos gaming 
2 x Portas USB 2.0 
4 x Portas USB 3.1 Gen 1 
1 x Módulo Wi-Fi/Bluetooth 
1 x Porta LAN RJ45 
1 x Porta USB 3.1 Gen 2 Tipo A 
1 x Porta USB 3.1 Gen 2 Tipo C 
5 x Jacks de áudio HD 
1 x Saída Óptica S/PDIF
Form Factor:ATX',284.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/4/7/4719072564247_1.png'),

(4,1,'Raijintek', 'IRIS 12 Rainbow RGB Orcus PWM 120mm','Ventoinha Raijintek IRIS 12 Rainbow RGB Orcus de 120 mm
Velocidade de rotação até 1800 RPM
Volume de ruído de apenas 23 dBA
Iluminação RGB',9.90,true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/0/R/0R40A00046.jpg'),

(5,1,'Noctua', 'NH-U9B SE2', 'Compatibilidade de socket: Intel LGA1366, LGA1156, LGA1155, LGA1150, LGA775, LGA2011 
Asus X-socket™ 
AMD AM2, AM2+, AM3, AM3+, FM1, FM2, FM2+
Dimensão	125 x 95 x 71 mm 
125 x 95 x 95/120 mm (com ventoinhas) 
Velocidade de ventoinha: 1600 RPM',52.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/N/C/NCTNHU9B.jpg'),
(5,1,'Noctua', 'NH-U12S TR4-SP3 Threadripper Edition', '› Dissipador de CPU para socket AMD TR4 / SP3
› Ventoinha de 120 mm incluída
› Baixo nível de ruído
Compatibilidade: AMD TR4 e SP3', 69.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/N/H/NH-U12S-TR4-SP3.jpg'),
(5,1, 'Cooler Master', 'MasterAir MA410M RGB', '› Dissipador de CPU para socket Intel e AMD
› Com duas ventoinhas de 120 mm
› Iluminação RG
Compatibilidade de socket:
Intel LGA 2066 / 2011-v3 / 2011 / 1151 / 1150 / 1155 / 1156 / 1366 
AMD AM4 / AM3 + / AM3 / AM2 + / AM2 / FM2 + / FM2 / FM1',65.90, true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/M/A/MAM-T4PN-218PC-R1.jpg'),

(6,1,'be quiet!', 'Straight Power 11 550W 80+ Gold', '› Fonte modular be quiet! Straight Power 11
› Fonte de 550W
› Certificação 80 Plus Gold
› Operação inaudível sem comprometer em desempenho de energia', 105.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/B/N/BN281_1_1.jpg'),
(6,1,'be quiet!','Straight Power 11 750W 80+ Gold','› Fonte modular be quiet! Straight Power 11
› Fonte de 750W
› Certificação 80 Plus Gold
› Operação inaudível sem comprometer em desempenho de energia',129.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/B/N/BN283.jpg'),
(6,1,'be quiet!','Straight Power 11 1000W 80+ Gold','› Fonte modular be quiet! Straight Power 11
› Fonte de 1000W
› Certificação 80 Plus Gold
› Operação inaudível sem comprometer em desempenho de energia',195.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/B/N/BN285.jpg'),

(7,1,'Samsung', '860 EVO 500GB SATA III', '› Disco SSD com tecnologia inovadora V-NAND
› melhor desempenho de leitura e gravação
› Capacidade de 500GB
› Interface SATA III', 99.00, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/M/Z/MZ-76E1T0B_2FEU.jpg'),
(7,1,'Samsung', '860 EVO 1TB SATA III', '› Disco SSD com tecnologia inovadora V-NAND
› melhor desempenho de leitura e gravação
› Capacidade de 1TB
› Interface SATA III', 194.90, true, 'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/M/Z/MZ-76E1T0B_2FEU.jpg'),
(8, 1, 'Seagate', 'Barracuda 1TB 7200rpm SATA III 64MB', '› Disco rígido com capacidade de 1TB › Velocidade de 7200 rpm› Formato de 3.5', 41.80, true,'https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST1000DM010.jpg'),
(8, 1, "Seagate", "Barracuda 2TB 7200rpm SATA III 64MB", "Disco SATA III 3.5' com 2TB, velocidade de 7200rpm e uma qualidade excelente SEAGATE.", 68.90, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST2000DM006_1_1.jpg"),
(8, 1, "Seagate", "3TB IronWolf 5900rpm SATA III 64MB", "Disco Seagate IronWolf com capacidade de 1TB e 5900RPM. Ideal para sistemas NAS.", 109.90, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST3000VN007.jpg"),
(8, 1, "Seagate", "Barracuda 4TB 5400rpm SATA III 256MB","Barracuda 4TB 5400rpm SATA III 256MB", 108.90, true,"https://www.chip7.pt/245716-thickbox_default/seagate-barracuda-st4000dm004-4000gb-serial-ata-iii-unidade-de-disco-rigido.jpg"),

(8, 1, "Western Digital", "Western Digital Blue 1TB (7200rpm) SATA III 64MB", "› Disco rígido com capacidade de 1TB› Velocidade de 7200 rpm› Formato de 3.5", 43.40, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD10EZEX.jpg"),
(8, 1, "Western Digital", "Western Digital Blue 2TB SATA III 64MB", "› Aumente a capacidade de armazenamento› Disco rígido com capacidade de 2TB› Velocidade de 5400 RPM› Formato de 3.5", 71.50, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD20EZRZ.jpg"),
(8, 1, "Western Digital", "Western Digital Purple 3TB SATA III 64MB", "› Optimizado para sistemas de segurança de alta definição e funcionamento contínuo› Suporte a uma carga de trabalho nominal até 180 TB/ano› Suporta até 64 câmeras de vigilância› Baixo consumo e alta eficiência", 98.90, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD30PURZ_1_1.jpg"),
(8, 1, "Western Digital", "Western Digital Blue 4TB SATA III 64MB", "Aumente a capacidade de armazenamento do seu PC com o disco rígido WD Blue de 4TB, a marca projetada para PCs desktop e all-in-one.", 108.90, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/W/D/WD40EZRZ.jpg"),

(9, 1, "Seagate", "SSHD Seagate 2TB + 8GB SSD FireCuda SATA III 64MB", "O Disco Seagate FireCuda de 2TB SATA III é a solução de armazenamento de dados que ajuda a garantir uma gestão de dados acessível de longo prazo. Um disco híbrido com 8GB SSD e uma velocidade de rotação de 7200RPM, para os melhores acessos possíveis.", 109.90, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST2000DX002.jpg"),
(9, 1, "Seagate", "SSHD Seagate 1TB + 8GB SSD FireCuda SATA III 64MB", "› O Disco Seagate FireCuda de 1TB SATA III é a solução de armazenamento de dados› Ajuda a garantir uma gestão de dados acessível de longo prazo› Um disco híbrido com 8GB SSD e uma velocidade de rotação de 7200RPM", 82.90, true,"https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/c687aa7517cf01e65c009f6943c2b1e9/S/T/ST1000DX002.jpg"),

(10, 1,"HyperX", "Fury 4GB (1x4GB) DDR4-2400MHz CL15", "Capacidade: 4GB (1x4GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 13335 mm x 3424 mm", 39.50, true,"https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/H/X/HX424C15FB_2F4.jpg"),
(10, 1, "HyperX", "Fury 8GB (1x8GB) DDR4-2400MHz CL15", "Capacidade: 8GB (1x8GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 13335 mm x 3424 mm", 74.70, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/H/X/HX424C15FB_2F4.jpg"),
(10, 1, "HyperX", "Fury 8GB (2x4GB) DDR4-2400MHz CL15", "Capacidade: 8GB (2x4GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Dimensões: 13335 mm x 3424 mm", 84.90, true, "https://d36djlzg0xu24k.cloudfront.net/media/catalog/product/cache/926507dc7f93631a094422215b778fe0/H/X/HX421C14FBK2_2F8.jpg"),
(10, 1, "HyperX", "Fury 16GB (1x16GB) DDR4-2400MHz CL15", "Capacidade: 16GB (1x16GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 133.35 mm x 34.24 mm", 144.90, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/2/0/20774_1.jpg"),
(10, 1, "HyperX", "Fury 16GB (2x8GB) DDR4-2400MHz CL15", "Capacidade: 16GB (2x8GB), Velocidade de Frequência: 2400MHz, Latência CAS: CL15, Tensão: 1.2V, Dimensões: 133.35 mm x 34.24 mm", 159.30, true, "https://d3fa68hw0m2vcc.cloudfront.net/5ba/181576111.jpeg"),
(10, 1, "Corsair", "Vengeance LPX 8GB (1x8GB) DDR4-2400MHz CL14", "Série: Vengeance® LPX, Capacidade: Single Channel 4GB (4GBx1), Tipo: DDR4, Velocidade: 2400MHz, Latência: 16-16-16-39, Voltagem: 1.2v, Recursos: Intel XMP 2.0 (Extreme Memory Profile) Ready", 56.50, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/s/e/sem-t_tulo-3_16.jpg"),
(10, 1, "Corsair", "Vengeance LPX 8GB (1x8GB) DDR4-2400MHz CL16", "Série: Vengeance® LPX, Capacidade: Single Channel 8GB (8GBx1), Tipo: DDR4, Velocidade: 2400MHz, Latência: 16-16-16-39,Voltagem: 1.2v, Recursos: Intel XMP 2.0 (Extreme Memory Profile) Ready", 103.50, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/s/e/sem-t_tulo-3_16.jpg"),
(10, 1, "Corsair", "Vengeance LPX 8GB (2x4GB) DDR4-2400MHz CL14", "Série: Vengeance® LPX, Capacidade: Kit Dual-Channel 8GB (4GBx2), Tipo: DDR4, Velocidade: 2400MHz, Latência: 14-16-16-31, Voltagem: 1.2V, Recursos: Intel XMP 2.0 (Extreme Memory Profile) Ready", 112.90, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_31_7_7.jpg"),

(11, 1, "Nox", "Caixa ATX Nox Modus Blue LED Edition Preta", "Motherboards Compatíveis: ATX; micro ATX, Slots de expansão: 7, Sistema de refrigeração: Frontal: 
Suporta 1 x ventoinha 120mm (1 x incluída com LED azul), Traseira: Suporta 1 x ventoinha 80mm (opcional/não incluída)",27.90 , true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_2_93.jpg"),
(11, 1, "Corsair", "Caixa ATX Corsair Crystal 460X RGB Preta", "Motherboards compatíveis: Mini-ITX, MicroATX, ATX, Slots de expansão: 7, Ventoinhas incluídas: Frontal: 3 x 120mm SP120 RGB LED", 169.90, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_2_62.jpg"),
(11, 1, "Corsair", "Caixa Micro-ATX Corsair Carbide 88R com Janela Preta", "Motherboards compatíveis: Mini-ITX, MicroATX, Slots de expansão: 4, Ventoinhas incluídas: Traseira: 1 x 120mm", 48.90, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_8_57.jpg"),
(11, 1, "Cooler Master", "Caixa ATX Cooler Master MasterBox 5t com Janela Preta", "Motherboards suportadas: ATX, Micro-ATX, Mini-ITX, Slots de Expansão: 7, Sistema de Refrigeração: Superior: Suporta 1 x ventoinha 120mm ou 1 x 140mm (opcionais/não incluídas); Frontal: Suporta 3 x ventoinhas 120mm ou 2 x 140mm (1 x 120mm incluída); Traseira: Suporta 1 x ventoinha 120mm (incluída)", 90.90, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/1/_/1_491.jpg"),
(11, 1, "NZXT", "Caixa ATX NZXT S340 Special Edition com Janela Preta", "Motherboards Compatíveis: Mini-ITX, MicroATX, ATX ; Slots de Expansão: 7; Sistema de Refrigeração:
Frontal: Suporta 2 x ventoinhas 140mm ou 2 x ventoinhas 120mm (opcionais/não incluídas), Topo: Suporta 1 x ventoinha 140/120mm (inclui 1 x ventoinha 120mm FN V2), Traseira: Suporta 1 x ventoinha 120mm (inclui 1 x ventoinha 120mm FN V2)", 99.90, true, "https://www.pcdiga.com/media/catalog/product/cache/1/image/2718f121925249d501c6086d4b8f9401/2/1/21891_1.jpg");

	
insert into builds(build_id, user_id, build_type_id, name, description, cpu_description, gpu_description, ram_description, price, likes)
values
(1,1,1,'Build teste', 'Apenas uma descrição da build de teste', 'Descrição do cpu teste', 'Descrição da gpu de teste', 'Descrição da ram de teste', ,0);