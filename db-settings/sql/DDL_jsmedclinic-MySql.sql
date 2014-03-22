-- -------------------------------------------------------------------
--     Nazwa pliku: DDL_jsmedclinic-MySql.sql
--       Utworzony: 04.09.2013
--           Autor: Janusz Swół © 2013
--           Firma: 
--       Działanie: utworzenie tabel systemu JSMedClinic, baza MySQL
--        Podstawa:
-- Etap realizacji: Test
--           Uwagi: Kodowanie tego pliku UTF-8
-- -------------------------------------------------------------------
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla KAS CHORYCH pacjenta
--  informacje o kasach chorych
-- -------------------------------------------------------------------
--
CREATE TABLE `kasach` (          
  `symbol_kasa` VARCHAR(10) NOT NULL,  -- symbol kasy chorych 
  --                   
  `description` VARCHAR(45) NOT NULL,  -- opis kasy chorych  
  `remarks`     VARCHAR(20),           -- uwagi do kasy chorych
  --
  CONSTRAINT `kasach_pk` PRIMARY KEY( `symbol_kasa` )  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla  PATIENT
--  ogólne informacje o pacjencie
-- -------------------------------------------------------------------
--
CREATE TABLE `patient` ( 
  -- 
  `pesel`         DECIMAL(11) NOT NULL,  -- pesel pacjenta
  --
  `last_name`     VARCHAR(35) NOT NULL,  -- nazwisko pacjenta
  `first_name`    VARCHAR(25),           -- imię pacjenta  
  `sex`           VARCHAR(6),            -- płeć pacjenta
  `zip_code`      VARCHAR(6),            -- kod zamieszkania pacjenta 
  `city`          VARCHAR(20),           -- miejscowość zamieszkania pacjenta 
  `street`        VARCHAR(55),           -- adres zamieszkania pacjenta   
  `phone`         VARCHAR(15),           -- telefon kontaktowy 
  `workplace`     VARCHAR(35),           -- miejsce pracy
  `occupation`    VARCHAR(35),           -- zawód wykonywany     
  `insurance`     BOOLEAN,               -- czy pacjent jest ubezpieczony w NFZ
  `symbol_kasa`   VARCHAR(10) NOT NULL,  -- symbol kasy chorych  
  --  
  `contact`       VARCHAR(35),           -- osoba kontaktowa
  `remarks`       VARCHAR(20),           -- uwagi
  --
  CONSTRAINT `patient_pk` PRIMARY KEY(`pesel`),
  CONSTRAINT `patient_kasach_fk` FOREIGN KEY( `symbol_kasa` )
                                 REFERENCES `kasach`( `symbol_kasa` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
-- 
-- -------------------------------------------------------------------
-- Struktura tabeli dla KATEGORIi PROCEDURY na oddziale
--  informacje o kategoriach procedur medycznych
-- -------------------------------------------------------------------
--
CREATE TABLE `med_procedure_cat` (    
  --
  `id_med_proc_cat` INT(10) unsigned NOT NULL auto_increment,   -- id kategorii
  --
  `symbol_kasa`     VARCHAR(10) NOT NULL, -- symbol kasy chorych dla tej kategorii procedury
  `symbol_proc_cat` VARCHAR(10) NOT NULL, -- symbol tej kategorii procedury
  `description`     VARCHAR(55),          -- opis  
  --
  `remarks`         VARCHAR(20),
  --
  CONSTRAINT `id_med_proc_cat_pk` PRIMARY KEY( `id_med_proc_cat` ),   
  CONSTRAINT `med_procedure_cat_kasach_fk` FOREIGN KEY( `symbol_kasa` )
                                           REFERENCES `kasach`( `symbol_kasa` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
--
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla TYPU CZYNNOŚCI MEDYCZNEJ na oddziale
--  informacje o typie procedury
-- -------------------------------------------------------------------
--
CREATE TABLE `med_procedure_type` (   
  --
  `id_med_proc_type` INT(10) unsigned NOT NULL auto_increment,   -- id typu procedury 
  -- 
  `symbol_proc_type` VARCHAR(15) NOT NULL, -- symbol typu czynności   
  `symbol_kasa`      VARCHAR(10) NOT NULL, -- symbol kasy chorych dla tego typu procedury
  `serial_no`        DECIMAL(10,2),        -- numer kolejny 
  `description`      VARCHAR(120),         -- opis typu czynności 
  `point_value`      DECIMAL(6),           -- wartość punktowa
  `symbol_proc_cat`  VARCHAR(10),          -- symbol kategorii procedury ( działu )
  `begin_date`       DATE,                 -- data wprowadzenia
  `expire_date`      DATE,                 -- data wycofania
  `medical_code`     VARCHAR(25),          -- kod medyczny procedury   
  --
  `remarks`          VARCHAR(30),
  --
  CONSTRAINT `id_med_proc_type_pk` PRIMARY KEY( `id_med_proc_type` ),  
  CONSTRAINT `med_procedure_type_kasach_fk` FOREIGN KEY( `symbol_kasa` )
                                            REFERENCES `kasach`( `symbol_kasa` )
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla LEKARZY na oddziale
--  informacje o lekarzach
-- -------------------------------------------------------------------
--
CREATE TABLE `doctor` (  
  --
  `id_doctor`     INT(10) unsigned NOT NULL auto_increment, -- id lekarza  
  `symbol_doctor` VARCHAR(10) NOT NULL ,  -- symbol uprawnień lekarza  
  `symbol_spec`   VARCHAR(15),            -- symbol specializacji 
  `first_name`    VARCHAR(15),            -- imię lekarza 
  `last_name`     VARCHAR(25)  NOT NULL,  -- nazwisko lekarza   
  `email_adress`  VARCHAR(75),            -- email
  `password`      VARCHAR(32),            -- hasło do systemu   
  --
  `remarks`       VARCHAR(40),
  --
  CONSTRAINT `doctor_pk` PRIMARY KEY( `id_doctor` ),
  CONSTRAINT `doctor_ux` UNIQUE KEY ( `symbol_doctor` )  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla MIEJSC SKĄD SKIEROWANO pacjenta
--  informacje 
-- -------------------------------------------------------------------
--
CREATE TABLE `clinic` (    
  --
  `id_clinic`   INT(10) unsigned NOT NULL auto_increment, -- id miejsca skierowania 
  `description` VARCHAR(35) NOT NULL,                     -- opis miejsca skierowania 
  --
  `remarks`     VARCHAR(20),
  --
  CONSTRAINT `clinic_pk` PRIMARY KEY( `id_clinic` )   
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
--
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla przyjęć na oddział
--  informacje o przyjęciu
-- -------------------------------------------------------------------
--
CREATE TABLE `admission` (
  --
  `id_admiss`  INT(10) unsigned NOT NULL auto_increment, -- id przyjęcia  
  `primary_no` INT(10) unsigned NOT NULL, -- numer główny księgi kliniki
  `ward_no`    INT(10) unsigned NOT NULL, -- numer odziałowy
  `ksg`        VARCHAR(4),                -- sygnatura księgi    
  `pesel`      DECIMAL(11) NOT NULL,      -- identyfikator pacjenta
  --
  `admission_date`  TIMESTAMP NOT NULL,   -- data i czas przyjęcia do szpitala
  `admission_from`  VARCHAR(35),          -- skąd skierowany
  `from_remarks`    VARCHAR(25),          -- uwagi o skierowaniu
  `symbol_doctor`   VARCHAR(10) NOT NULL, -- identyfikator lekarza prowadzcego
  `init_diagnosis`  VARCHAR(45),          -- rozpoznanie wstępne
  `final_diagnosis` VARCHAR(45),          -- rozpoznanie ostateczne   
  `discharge_date`  TIMESTAMP,            -- data i czas wypisania ze szpitala
  `discharge_to`    VARCHAR(35),          -- gdzie wypisany  
  --
  `remarks`   VARCHAR(20),
  --
  CONSTRAINT `admission_pk` PRIMARY KEY( `id_admiss` ),
  CONSTRAINT `admission_patient_fk` FOREIGN KEY( `pesel` )
                                    REFERENCES `patient`( `pesel` ),
  CONSTRAINT `admission_doctor_fk`  FOREIGN KEY( `symbol_doctor` )
                                    REFERENCES `doctor`( `symbol_doctor` ) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
--
-- -------------------------------------------------------------------
-- Struktura tabeli dla CZYNNOŚCI na oddziale
--  informacje o wykonanych procedurach medycznych
-- -------------------------------------------------------------------
--
CREATE TABLE `med_procedure` (  
  --
  `id_med_procedure` INT(10) unsigned NOT NULL auto_increment,  -- id procedury
  --
  `symbol_doctor`    VARCHAR(10) NOT NULL,  -- symbol uprawnień lekarza wykonującego/odpowiedzialnego
  `id_admiss`        INT(10) unsigned,      -- identyfikator przyjęcia
  `data`             DATE,                  -- data wykonania procedury
  `pesel`            DECIMAL(11) NOT NULL,  -- pesel pacjenta poddanego tej procedurze
  `symbol_kasa`      VARCHAR(10) NOT NULL,  -- symbol kasy chorych dla tej procedury
  `primary_data`     VARCHAR(15),           -- dane dotyczące procedury ( np. ząb zabiegu )
  `additional_data`  VARCHAR(15),           -- dodatkowe dane
  `symbol_proc_type` VARCHAR(15) NOT NULL,  -- symbol typu procedury
  `no_adm`           DECIMAL(3)  DEFAULT 1, -- numer kolejny w ramach przyjęcia 
  `surcharge`        BOOLEAN,               -- czy była dopłata w ramach NFZ
  `amount`           DECIMAL(10,2),         -- kwota dopłaty    
  `description`      VARCHAR(75),           -- opis leczenia 
  --
  `remarks`          VARCHAR(50),           -- uwagi
  --
  CONSTRAINT `med_procedure_pk` PRIMARY KEY( `id_med_procedure` ),
  CONSTRAINT `med_procedure_doctor_fk`  FOREIGN KEY( `symbol_doctor` )
                                        REFERENCES `doctor`( `symbol_doctor` ),
  CONSTRAINT `med_procedure_patient_fk` FOREIGN KEY( `pesel` )
                                        REFERENCES `patient`( `pesel` ), 
  CONSTRAINT `med_procedure_kasach_fk`  FOREIGN KEY( `symbol_kasa` )
                                        REFERENCES `kasach`( `symbol_kasa` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;
--
-- -------------------------------------------------------------------
--
COMMIT;
