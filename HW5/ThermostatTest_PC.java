package thermostat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Predicate Coverage (PC) tests for Thermostat.turnHeaterOn.
 *
 * Predicates:
 *   P1 = (a || (b && c)) && d
 *        a = curTemp < dTemp - thresholdDiff
 *        b = override
 *        c = curTemp < overTemp - thresholdDiff
 *        d = timeSinceLastRun > minLag
 *   P2 = override          (single-clause inner predicate, reached only when P1==true)
 *
 * PC requires each predicate to evaluate true and false at least once.
 * Fixed inputs: period=DAY, day=WEEKDAY, pSet default dTemp=65,
 *               thresholdDiff=5, minLag=10.
 */
public class ThermostatTest_PC {

    private Thermostat t;
    private ProgrammedSettings pSet;

    @Before
    public void setUp() {
        t = new Thermostat();
        pSet = new ProgrammedSettings();
        t.setPeriod(Period.DAY);
        t.setDay(DayType.WEEKDAY);
        t.setThresholdDiff(5);
        t.setMinLag(10);
    }

    // P1 = TRUE — truth-table row 1 (a=T, b=T, c=T, d=T)
    // 50 < 65-5=60 (a=T); override=true (b=T);
    // 50 < 70-5=65 (c=T); 20 > 10 (d=T)
    @Test
    public void testP1_True_Row1() {
        t.setCurrentTemp(50);
        t.setOverride(true);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }

    // P1 = FALSE — truth-table row 16 (a=F, b=F, c=F, d=F)
    // 70 !< 60 (a=F); override=false (b=F);
    // 70 !< 65 (c=F); 5 !> 10 (d=F)
    @Test
    public void testP1_False_Row16() {
        t.setCurrentTemp(70);
        t.setOverride(false);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(5);
        assertFalse(t.turnHeaterOn(pSet));
    }

    // P2 = TRUE — row 1 reaches P2 with override=true
    @Test
    public void testP2_True_Row1() {
        t.setCurrentTemp(50);
        t.setOverride(true);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }

    // P2 = FALSE — row 7 (a=T, b=F, c=F, d=T) reaches P2 with override=false
    // 50 < 60 (a=T) forces P1=T so the inner if is reached.
    @Test
    public void testP2_False_Row7() {
        t.setCurrentTemp(50);
        t.setOverride(false);
        t.setOverTemp(50);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }
}
