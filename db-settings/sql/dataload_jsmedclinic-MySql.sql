-- -------------------------------------------------------------------
--     Nazwa pliku: dataload_jsmedclinic-MySql.sql
--       Utworzony: 13.09.2013
--           Autor: Janusz Swół © 2013
--           Firma: 
--       Działanie: Testowe dane systemu JSMedClinic
--        Podstawa:
-- Etap realizacji: Test
--           Uwagi: Kodowanie tego pliku UTF-8
-- -------------------------------------------------------------------
--
--
--
INSERT INTO kasach(description, symbol_kasa) VALUES('Praktyka prywatna', 'P/P');
INSERT INTO kasach(description, symbol_kasa) VALUES('Podkarpacka',       '09R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Mazowiecka',        '07R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Wojskowa',          '17B');
INSERT INTO kasach(description, symbol_kasa) VALUES('Małopolska',        '06R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Dolnośląska',       '01R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Śląska',            '12R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Pomorska',          '11R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Zachodniopomorska', '16R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Podlaska',          '10R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Opolska',           '08R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Warmińsko-Mazurska','14R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Świętokrzyska',     '13R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Kujawsko-Pomorska', '02R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Lubelska',          '03R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Lubuska',           '04R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Łódzka',            '05R');
INSERT INTO kasach(description, symbol_kasa) VALUES('Wielkopolska',      '15R');
--
--
--
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Michał',   'Zubek',       'profesor','0000001');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Zygmunt',  'Moszko',      '','0000002');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Adam',     'Zarzeczny',   '','0000003');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Piotr',    'Bilski',      '','0000004');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Roman',    'Góra',        '','0000005');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Grzegorz', 'Piwowski',    '','0000006');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Michał',   'Sobański',    '','0000007');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Stanisław','Dzierzkowski','','0000008');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Janina',   'Jagielska',   '','0000009');
INSERT INTO doctor(first_name, last_name, remarks, symbol_doctor) VALUES('Henryk',    'Koza',       '','0000010');
--
UPDATE doctor SET email_adress = 'm.zubek@wp.pl'        WHERE (symbol_doctor LIKE '0000001');
UPDATE doctor SET email_adress = 'z.moszko@wp.pl'       WHERE (symbol_doctor LIKE '0000002');
UPDATE doctor SET email_adress = 'a.zarzeczny@wp.pl'    WHERE (symbol_doctor LIKE '0000003');
UPDATE doctor SET email_adress = 'p.bilski@wp.pl'       WHERE (symbol_doctor LIKE '0000004');
UPDATE doctor SET email_adress = 'r.gora@wp.pl'         WHERE (symbol_doctor LIKE '0000005');
UPDATE doctor SET email_adress = 'g.piwowski@wp.pl'     WHERE (symbol_doctor LIKE '0000006');
UPDATE doctor SET email_adress = 'm.sobanski@wp.pl'     WHERE (symbol_doctor LIKE '0000007');
UPDATE doctor SET email_adress = 's.dzierzkowski@wp.pl' WHERE (symbol_doctor LIKE '0000008');
UPDATE doctor SET email_adress = 'j.jagielska@wp.pl'    WHERE (symbol_doctor LIKE '0000009');
UPDATE doctor SET email_adress = 'h.koza@wp.pl'         WHERE (symbol_doctor LIKE '0000010');
--
--
ALTER TABLE `med_procedure` ADD `symbol_doctor` VARCHAR(10) NOT NULL DEFAULT "0000001"; 
--
--
INSERT INTO clinic(description, remarks) VALUES('Neurologia w/m','');
INSERT INTO clinic(description, remarks) VALUES('Szpital Siedlce','');
INSERT INTO clinic(description, remarks) VALUES('PR Poznańska','');
INSERT INTO clinic(description, remarks) VALUES('Chirurgia w/m','');
INSERT INTO clinic(description, remarks) VALUES('ESCULAP Przychodnia',NULL);
INSERT INTO clinic(description, remarks) VALUES('EVITA Garwolin',NULL);
INSERT INTO clinic(description, remarks) VALUES('Klinika Neurologii MSWiA',NULL);
INSERT INTO clinic(description, remarks) VALUES('Miejski Ośrodek Zdrowia Zielonka',NULL);
INSERT INTO clinic(description, remarks) VALUES('Neurologia, Sokołów Podlaski','');
INSERT INTO clinic(description, remarks) VALUES('Neurologia Siedlce','');
INSERT INTO clinic(description, remarks) VALUES('Onkologia, Siedlce','');
INSERT INTO clinic(description, remarks) VALUES('Poradnia lek. ZPZZLO',NULL);
INSERT INTO clinic(description, remarks) VALUES('Poradnia Neurologiczna Legnica',NULL);
INSERT INTO clinic(description, remarks) VALUES('Poradnia Neurologiczna Łowicz',NULL);
INSERT INTO clinic(description, remarks) VALUES('Poradnia Neurologiczna Siedlce',NULL);
INSERT INTO clinic(description, remarks) VALUES('PR Legionowo',NULL);
INSERT INTO clinic(description, remarks) VALUES('prof. Friedmański','');
INSERT INTO clinic(description, remarks) VALUES('Przychodnia, Krypska, Warszawa',NULL);
INSERT INTO clinic(description, remarks) VALUES('Przychodnia - Łosice',NULL);
INSERT INTO clinic(description, remarks) VALUES('Przychodnia Mińsk Mazowiecki',NULL);
INSERT INTO clinic(description, remarks) VALUES('Przychodnia Warszawa-Mokotów',NULL);
INSERT INTO clinic(description, remarks) VALUES('Przyjęcie w trybie nagłym',NULL);
INSERT INTO clinic(description, remarks) VALUES('SOR',NULL);
INSERT INTO clinic(description, remarks) VALUES('STOCER',NULL);
INSERT INTO clinic(description, remarks) VALUES('Szpital Powiatowy Stalowa Wola',NULL);
INSERT INTO clinic(description, remarks) VALUES('Szpital Siedlce Neurologia',NULL);
INSERT INTO clinic(description, remarks) VALUES('VITAMED NZOZ',NULL);
INSERT INTO clinic(description, remarks) VALUES('ZOZ Busko - Zdrój',NULL);
INSERT INTO clinic(description, remarks) VALUES('ZOZ Serock',NULL);
INSERT INTO clinic(description, remarks) VALUES('ARS MEDICA',NULL);
INSERT INTO clinic(description, remarks) VALUES('Centrum Medyczne Sadyba',NULL);
INSERT INTO clinic(description, remarks) VALUES('Centrum Rehabilitacji STOCER',NULL);
INSERT INTO clinic(description, remarks) VALUES('Neurologia Siedlce',NULL);
INSERT INTO clinic(description, remarks) VALUES('Neurologia Szpital',NULL);
INSERT INTO clinic(description, remarks) VALUES('Neurologia Wołomin',NULL);
INSERT INTO clinic(description, remarks) VALUES('NZOZ',NULL);
INSERT INTO clinic(description, remarks) VALUES('Poradnia Ogólna',NULL);
INSERT INTO clinic(description, remarks) VALUES('Praktyka Lekarska',NULL);
INSERT INTO clinic(description, remarks) VALUES('Szpital Czerniakowski',NULL);
INSERT INTO clinic(description, remarks) VALUES('Centrum Medyczne',NULL);
INSERT INTO clinic(description, remarks) VALUES('Gabinet Neurologii',NULL);
INSERT INTO clinic(description, remarks) VALUES('PR Wołomin',NULL);
--
--
--
UPDATE `doctor` SET `password` ='5f4dcc3b5aa765d61d8327deb882cf99' WHERE `password` IS NULL;
--
--
UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000002"
WHERE ( (`med_procedure`.`pesel` >= "33000000000" ) AND (`med_procedure`.`pesel` < "40000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000003"
WHERE ( (`med_procedure`.`pesel` >= "40000000000" ) AND (`med_procedure`.`pesel` < "50000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000004"
WHERE ( (`med_procedure`.`pesel` >= "50000000000" ) AND (`med_procedure`.`pesel` < "60000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000005"
WHERE ( (`med_procedure`.`pesel` >= "60000000000" ) AND (`med_procedure`.`pesel` < "70000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000006"
WHERE ( (`med_procedure`.`pesel` >= "70000000000" ) AND (`med_procedure`.`pesel` < "80000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000007"
WHERE ( (`med_procedure`.`pesel` >= "80000000000" ) AND (`med_procedure`.`pesel` < "85000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000008"
WHERE ( (`med_procedure`.`pesel` >= "85000000000" ) AND (`med_procedure`.`pesel` < "90000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000009"
WHERE ( (`med_procedure`.`pesel` >= "90000000000" ) );

UPDATE `med_procedure`
SET `med_procedure`.`symbol_doctor` = "0000010"
WHERE ( (`med_procedure`.`pesel` <= "29000000000" ) );
--
--
commit;
--
--
ALTER TABLE `med_procedure` ADD `id_proc_type` INT(10) unsigned DEFAULT 0; 
--
--  
SELECT `med_procedure_type`.`id_med_proc_type`, `med_procedure.symbol_proc_type` 
  FROM  `med_procedure_type`, `med_procedure` 
    WHERE `med_procedure`.`symbol_proc_type` = `med_procedure_type`.`symbol_proc_type`; 
--
--
UPDATE `med_procedure` 
  SET `med_procedure`.`id_proc_type` = 
  ( SELECT `med_procedure_type`.`id_med_proc_type` FROM `med_procedure_type` 
      WHERE `med_procedure`.`symbol_proc_type` = `med_procedure_type`.`symbol_proc_type` );
--  
--
ALTER TABLE `med_procedure` ADD  
  CONSTRAINT `med_procedure_proc_type_fk` FOREIGN KEY( `id_proc_type` )
                                          REFERENCES `med_procedure_type`( `id_med_proc_type` );
--
--
-- 




