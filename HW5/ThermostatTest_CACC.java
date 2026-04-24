package thermostat;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Correlated Active Clause Coverage (CACC) tests for Thermostat.turnHeaterOn.
 *
 *   P1 = (a || (b && c)) && d
 *
 * For each clause, pick a pair of rows where (1) the other clauses make that
 * clause active (determine P1), and (2) the clause itself flips.  The outcome
 * of P1 flips between the two rows, proving the clause was the determiner.
 *
 *   a active when (b && c)==F and d==T : rows 7 (a=T,P1=T) / 15 (a=F,P1=F)
 *   b active when a==F, c==T, d==T     : rows 9 (b=T,P1=T) / 13 (b=F,P1=F)
 *   c active when a==F, b==T, d==T     : rows 9 (c=T,P1=T) / 11 (c=F,P1=F)
 *   d active when (a || (b && c))==T   : rows 1 (d=T,P1=T) / 2  (d=F,P1=F)
 *
 * P2 = override is a single-clause predicate, so CACC == PC for P2.
 * Row 1 (b=T, P1=T) and row 7 (b=F, P1=T) reach P2 with both values.
 */
public class ThermostatTest_CACC {

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

    // d active, d=T — Row 1 (T,T,T,T) — P1=T ; also P2 reached with b=T
    @Test
    public void test_dActive_T_Row1() {
        t.setCurrentTemp(50);
        t.setOverride(true);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }

    // d active, d=F — Row 2 (T,T,T,F) — P1=F
    @Test
    public void test_dActive_F_Row2() {
        t.setCurrentTemp(50);
        t.setOverride(true);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(5);
        assertFalse(t.turnHeaterOn(pSet));
    }

    // a active, a=T — Row 7 (T,F,F,T) — P1=T ; also P2 reached with b=F
    @Test
    public void test_aActive_T_Row7() {
        t.setCurrentTemp(50);
        t.setOverride(false);
        t.setOverTemp(50);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }

    // a active, a=F — Row 15 (F,F,F,T) — P1=F
    @Test
    public void test_aActive_F_Row15() {
        t.setCurrentTemp(70);
        t.setOverride(false);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(20);
        assertFalse(t.turnHeaterOn(pSet));
    }

    // b and c both active & true — Row 9 (F,T,T,T) — P1=T
    @Test
    public void test_bActive_T_cActive_T_Row9() {
        t.setCurrentTemp(70);
        t.setOverride(true);
        t.setOverTemp(80);
        t.setTimeSinceLastRun(20);
        assertTrue(t.turnHeaterOn(pSet));
    }

    // b active, b=F — Row 13 (F,F,T,T) — P1=F
    @Test
    public void test_bActive_F_Row13() {
        t.setCurrentTemp(70);
        t.setOverride(false);
        t.setOverTemp(80);
        t.setTimeSinceLastRun(20);
        assertFalse(t.turnHeaterOn(pSet));
    }

    // c active, c=F — Row 11 (F,T,F,T) — P1=F
    @Test
    public void test_cActive_F_Row11() {
        t.setCurrentTemp(70);
        t.setOverride(true);
        t.setOverTemp(70);
        t.setTimeSinceLastRun(20);
        assertFalse(t.turnHeaterOn(pSet));
    }
}
