public class Grade {
    private boolean approved = false;
    private Integer grade = null;
    private boolean cumLaude = false;

    public Grade(int grade) {
        if (grade>=18) {
            approved = true;
            if (grade > 30){
                this.grade = 30;
                cumLaude = true;
            } else {
                this.grade = grade;
                cumLaude = false;
            }
        }else {
            approved = false;
        }

    }

    public boolean isApproved() {
        return approved;
    }

    public Integer finalGrade() {
        return grade;
    }

    public boolean cumLaude() {
        return cumLaude;
    }
}
