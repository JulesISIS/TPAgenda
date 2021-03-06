package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class RepetitiveEvent extends Event {
   
     /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    
    ChronoUnit frequency;
    public ArrayList<LocalDate> myExceptions = new ArrayList<>();

    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        myExceptions.add(date);
    }

    public ChronoUnit getFrequency() {
        return this.frequency;
    }
        @Override
    public boolean isInDay(LocalDate aDay) {
        LocalDate d;
        d = this.getStart().toLocalDate();

        //Tant que la date en parametre est apres le debut de l'evenement, on ajoute une repeition a l'evenement
        while (aDay.isAfter(d) || aDay.equals(d)) {
            if (aDay.isEqual(d)) {
                this.isInDay = true;
            }
            d = d.plus(1, frequency);
        }
        
        //Tester si le jour ne porte pas d'exception
        myExceptions.forEach((e) -> {
            if (aDay.isEqual(e)) {
                isInDay = false;
            }
        });
        return isInDay;
    }
}
