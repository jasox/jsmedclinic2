package pl.jasox.medward.db;

/**
 * @author Janusz Swół
 */
public enum DatabaseType {
  DEFAULT, 
  SE,      // testowa do środowiska Javy SE
  AS,      // defaultowa dla danego serwera aplikacji
  HSQL,    // fake database - dla zawaansowanego testowania 
  MySQL;   // produkcyjna
}
