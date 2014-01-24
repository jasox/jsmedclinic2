package pl.jasox.medward.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Klasa pomocnicza dla daty.
 * 
 * @author anil & janusz.swol@gmail.com
 */
public class DateUtil {
  
  /** 
   * Zwraca datę różną od zadanej o określoną liczbę dni
   */
  public static Date getAddDaysDate(Date date, Integer d) {
    // ..
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(date);
    gc.add(GregorianCalendar.DATE, d);    
    return gc.getTime();
  }
  
  /**
   * Zwraca datę soboty z bieżącego tygodnia.
   */
  public static Date getCurrentPeriodEndingDate() {
    // powtarzaj aż znajdziesz SUNDAY
    GregorianCalendar gc = new GregorianCalendar();
    while ( gc.get( GregorianCalendar.DAY_OF_WEEK ) != 
            GregorianCalendar.SUNDAY ) {
      gc.add(GregorianCalendar.DATE, 1);
    }
    return gc.getTime();
  }

  /**
   * Data poniedziałku bieżącego tygodnia
   */
  public static Date getCurrentPeriodStartingDate() {
    // powtrzaj aż znajdziesz MONDAY
    GregorianCalendar gc = new GregorianCalendar();
    while ( gc.get(GregorianCalendar.DAY_OF_WEEK) != 
            GregorianCalendar.MONDAY ) {
      gc.add(GregorianCalendar.DATE, -1);
    }
    return gc.getTime();
  }

  /**
   * Zwraca datę z wyzerowanymi paramterami czasu
   */
  public static Date getDateWithZeroTime(Date date) {
    Calendar modifiedDate = new GregorianCalendar();    
    modifiedDate.setTime(date);
    modifiedDate.set(Calendar.HOUR_OF_DAY, 0);
    modifiedDate.set(Calendar.MINUTE,      0);
    modifiedDate.set(Calendar.SECOND,      0);
    modifiedDate.set(Calendar.MILLISECOND, 0);

    return modifiedDate.getTime();
  }
  
  /**
   * Zwraca ustawioną datę z wyzerowanymi paramterami czasu
   *   ustawienie zgodne z urzędowym zapisem daty : y, M, d
   */
  public static Date getDateWithZeroTime(int year, int month, int day) {
    Calendar modifiedDate = new GregorianCalendar();
    modifiedDate.set(year, month - 1, day);    
    modifiedDate.set(Calendar.HOUR_OF_DAY, 0);
    modifiedDate.set(Calendar.MINUTE,      0);
    modifiedDate.set(Calendar.SECOND,      0);
    modifiedDate.set(Calendar.MILLISECOND, 0);

    return modifiedDate.getTime();
  }

  /**
   * Zwraca datę z parametrami ustawionymi na max dla dnia 
   * ( to jest, 23:59:59.999 )
   */
  public static Date getDateWithMaxTime(Date date) {
    Calendar modifiedDate = new GregorianCalendar();
    modifiedDate.setTime(date);
    modifiedDate.set(Calendar.HOUR_OF_DAY, 23 );
    modifiedDate.set(Calendar.MINUTE,      59 );
    modifiedDate.set(Calendar.SECOND,      59 );
    modifiedDate.set(Calendar.MILLISECOND, 999);
    
    return modifiedDate.getTime();
  }

  /**
   * Sprawdza czy checkDate wypada w bieżącym tygodniu
   */
  public static boolean isInCurrentPayPeriod(Date checkDate) {
    Date weekStartDate = 
      getDateWithZeroTime(getCurrentPeriodStartingDate());
    Date weekEndDate = 
      getDateWithMaxTime(getCurrentPeriodEndingDate());

    return ( !checkDate.before(weekStartDate) && 
             !checkDate.after(weekEndDate) );
  }
  
  
  /** */
  public static void main(String[] args) {
    System.out.println("...");
    
    System.out.println("  " + new Date());
    System.out.println("  " + DateUtil.getDateWithZeroTime(1, 1, 1) );
    System.out.println("  " + DateUtil.getDateWithZeroTime(1962, 1, 13) );
    System.out.println("...");
    
  }  
  
}
