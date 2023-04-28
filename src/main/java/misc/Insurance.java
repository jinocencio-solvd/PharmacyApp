package misc;

import java.util.Objects;

public class Insurance {

    private static int count = 0;

    private String insuranceName;
    private String insuranceId;
    private double percentInsuranceCovered;

    public Insurance(String insuranceName, double percentInsuranceCovered) {
        count++;
        this.insuranceName = insuranceName;
        this.insuranceId = "InsuranceID-" + count;
        this.percentInsuranceCovered = percentInsuranceCovered;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public double getPercentInsuranceCovered() {
        return percentInsuranceCovered;
    }

    public void setPercentInsuranceCovered(double percentInsuranceCovered) {
        this.percentInsuranceCovered = percentInsuranceCovered;
    }

    @Override
    public String toString() {
        return "Misc.Insurance{" +
            "insuranceName='" + insuranceName + '\'' +
            ", insuranceId='" + insuranceId + '\'' +
            ", percentInsuranceCovered=" + percentInsuranceCovered +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Insurance)) {
            return false;
        }
        Insurance insurance = (Insurance) o;
        return Double.compare(insurance.getPercentInsuranceCovered(),
            getPercentInsuranceCovered()) == 0 && Objects.equals(getInsuranceName(),
            insurance.getInsuranceName()) && Objects.equals(getInsuranceId(),
            insurance.getInsuranceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInsuranceName(), getInsuranceId(), getPercentInsuranceCovered());
    }
}
