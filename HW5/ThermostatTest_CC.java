package thermostat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Clause Coverage (CC) tests for Thermostat.turnHeaterOn.
 *
 * CC requires every clause to take both true and false.
 *   P1 clauses: a, b, c, d
 *   P2 clause : b (same boolean variable as P1's b)
 *
 * Row 1  (T,T,T,T) covers a=T, b=T, c=T, d=T (and P2 with b=T).
 * Row 16 (F,F,F,F) covers a=F, b=F, c=F, d=F.
 * Row 7  (T,F,F,T) lets P2 be executed with b=F (needs P1=T to reach).
 */
public class ThermostatTest_CC {

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

    // Row 1 — a=T, b=T, c=T, d=T ; P2 reached with b=T
    @Test
    public void testAllClausesTrue_Row1() {
        t.setCurrentTemp(50);
        t.setOverride(true);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }

    // Row 16 — a=F, b=F, c=F, d=F
    @Test
    public void testAllClausesFalse_Row16() {
        t.setCurrentTemp(70);
        t.setOverride(false);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(5);
        assertFalse(t.turnHeaterOn(pSet));
    }

    // Row 7 — a=T, b=F, c=F, d=T ; reaches P2 with b=F
    @Test
    public void testP2_bFalseReachable_Row7() {
        t.setCurrentTemp(50);
        t.setOverride(false);
        t.setOverTemp(50);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }
}
