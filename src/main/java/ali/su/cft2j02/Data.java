package ali.su.cft2j02;

import java.util.Date;

public class Data {
    private String loginName;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date loginDate;
    private String appType;
    private String fileName;    //Для упрощения заведу здесь, как просто поле

    public Data(
            String loginName, String lastName, String firstName, String patronymic, Date loginDate, String appType,
            String fileName
               ) {
        this.loginName = loginName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.loginDate = loginDate;
        this.appType = appType;
        this.fileName = fileName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getFio() {
        return lastName + " " + firstName + " " + patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public String getAppType() {
        return appType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return "Data{" +
                "loginName='" + loginName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", loginDate=" + loginDate +
                ", appType='" + appType + '\'' +
                '}';
    }
}
