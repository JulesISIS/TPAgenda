package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AgendaTest {

    Agenda agenda;
    List<Event> testList = new ArrayList<>();

    // On ajoute la date du November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // On ajoute la date du January 5, 2021
    LocalDate jan_5_2021 = LocalDate.of(2021, 1, 5);

    // On ajoute la date du November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);
    
    // On ajoute la date du May 5th, 2020, 18:30
    LocalDateTime may_5__2020_18_30 = LocalDateTime.of(2020, 5, 5, 18, 30);

    // On ajoute la durée de 120 minutes
    Duration min_120 = Duration.ofMinutes(120);

    // A simple event
    // November 1st, 2020, 22:30, 120 minutes
    Event simple = new Event("Simple event", nov_1__2020_22_30, min_120);
    
    // A second simple event
    // May 5th, 2020, 18:30, 120 minutes
    Event simpleBis = new Event("Simple event bis", may_5__2020_18_30, min_120);

    // A Weekly Repetitive event ending at a given date
    RepetitiveEvent fixedTermination = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, jan_5_2021);

    // A Weekly Repetitive event ending after a give number of occurrrences
    RepetitiveEvent fixedRepetitions = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, 10);

    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
        agenda.addEvent(simple);
        agenda.addEvent(simpleBis);
        agenda.addEvent(fixedTermination);
        agenda.addEvent(fixedRepetitions);
        agenda.addEvent(neverEnding);
    }

    @Test
    public void testMultipleEventsInDay() {
        assertEquals(4, agenda.eventsInDay(nov_1_2020).size(), "Il y a 4 événements ce jour là");
        assertTrue(agenda.eventsInDay(nov_1_2020).contains(neverEnding));
    }

    @Test
    public void testFindByTitle() {
        testList.add(simple);
        assertEquals(testList, agenda.findByTitle("Simple event"), "Les évènements ne sont pas correctement triés selon leur titre");
    }
    
    @Test
    public void testIsFreeFor(){
        assertTrue(agenda.isFreeFor(simpleBis),"L'évènement devrait être ajouté à l'agenda");
    }

}
