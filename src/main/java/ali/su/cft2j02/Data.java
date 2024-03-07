package ali.su.cft2j02;

import java.util.Date;
import java.util.Objects;

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

    public Data() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (!loginName.equals(data.loginName)) return false;
        if (!lastName.equals(data.lastName)) return false;
        if (!firstName.equals(data.firstName)) return false;
        if (!patronymic.equals(data.patronymic)) return false;
        if (!Objects.equals(loginDate, data.loginDate)) return false;
        if (!appType.equals(data.appType)) return false;
        return fileName.equals(data.fileName);
    }

    @Override
    public int hashCode() {
        int result = loginName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        result = 31 * result + appType.hashCode();
        result = 31 * result + fileName.hashCode();
        return result;
    }
}
