package register;

import pharmacy.Pharmacy;

public class Receipt {

    private String content;

    public Receipt(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
