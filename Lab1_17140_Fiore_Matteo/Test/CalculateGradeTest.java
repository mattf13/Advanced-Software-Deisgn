import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateGradeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void fullGrade() {
        Grade g = CalculateGrade.calculateGrade(50, 10.0f);
        assertTrue(g.isApproved());
        assertEquals(30, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void just40LabPoints() {
        Grade g = CalculateGrade.calculateGrade(40, 10.0f);
        assertTrue(g.isApproved());
        assertEquals(27, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void just8inExam() {
        Grade g = CalculateGrade.calculateGrade(40, 8.0f);
        assertTrue(g.isApproved());
        assertEquals(24, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void negativeLabPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            Grade g = CalculateGrade.calculateGrade(-5, 8.0f);
        });
    }

    @Test
    void negativeExamGrade() {
        try {
            Grade g = CalculateGrade.calculateGrade(5, -8.0f);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void examMaximumGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            Grade g = CalculateGrade.calculateGrade(50, 18.0f);
        });
    }

    @Test
    void notApproved() {
        Grade g = CalculateGrade.calculateGrade(25, 5.0f);
        assertFalse(g.isApproved());
        assertNull(g.finalGrade());
    }

    @Test
    void extraLabPoint() {
        Grade g = CalculateGrade.calculateGrade(55, 7.0f);
        assertTrue(g.isApproved());
        assertEquals(27, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void extraLabPointWithLessThanFive() {
        Grade g = CalculateGrade.calculateGrade(59, 7.0f);
        assertTrue(g.isApproved());
        assertEquals(27, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void getting3PointsWithLabPoints() {
        Grade g = CalculateGrade.calculateGrade(72, 5.0f);
        assertTrue(g.isApproved());
        assertEquals(27, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void cumLaude() {
        Grade g = CalculateGrade.calculateGrade(62, 9.0f);
        assertTrue(g.isApproved());
        assertEquals(30, g.finalGrade());
        assertTrue(g.cumLaude());
    }

    @Test
    void lessThan25labPoints() {
        Grade g = CalculateGrade.calculateGrade(24, 10.0f);
        assertTrue(g.isApproved());
        assertEquals(22, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void between25and40labPoints() {
        Grade g = CalculateGrade.calculateGrade(39, 7.0f);
        assertTrue(g.isApproved());
        assertEquals(23, g.finalGrade());
        assertFalse(g.cumLaude());
    }

    @Test
    void over40labPoint() {
        Grade g = CalculateGrade.calculateGrade(63, 2.0f);
        assertTrue(g.isApproved());
        assertEquals(21, g.finalGrade());
        assertFalse(g.cumLaude());
    }
}