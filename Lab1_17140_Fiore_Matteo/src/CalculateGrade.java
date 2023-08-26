public class CalculateGrade {
    public static Grade calculateGrade(int labPoints, float examGrade) {
        validateArguments(labPoints, examGrade);
        if (labPoints > 50) {
            double extraLabPoints = (labPoints - 50) / 5;
            examGrade += (extraLabPoints > 3) ? 3 : extraLabPoints;
            labPoints = 50;
        }

        int labFinalExam = labPointsRoundedFinalExam(labPoints, 0);
        int examFinalGrade = Math.round((examGrade * 15) / 10);
        Grade grade = new Grade(labFinalExam + examFinalGrade);

        return grade;
    }

    private static int labPointsRoundedFinalExam(int labPoints, int res) {
        double conversionLabPoints = (labPoints * 15) / 50.0;
        if (labPoints < 25) {
            res = (int) Math.floor(conversionLabPoints);
        }
        if (labPoints >= 25 && labPoints <= 40) {
            res = (int) Math.round(conversionLabPoints);
        }
        if (labPoints > 40) {
            res = (int) Math.ceil(conversionLabPoints);
        }
        return res;
    }

    private static void validateArguments(int labPoints, float examGrade) {
        if (labPoints < 0) {
            throw new IllegalArgumentException("Lab points cannot be negative");
        }
        if (examGrade < 0) {
            throw new IllegalArgumentException("Exam grade cannot be negative");
        }
        if (examGrade > 10) {
            throw new IllegalArgumentException("Exam grade cannot be more than 10");
        }
    }

    public static void main(String[] args) {
        System.out.println(calculateGrade(63, 0).finalGrade());
    }
}
