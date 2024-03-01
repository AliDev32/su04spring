package ali.su.cft2j02;

import java.util.Date;

public class Data {
    private String loginName;
    private String lastName;
    private String firstName;
    private String patronymic;
    private Date loginDate;
    private String appType;

    public Data(
            String loginName, String lastName, String firstName, String patronymic, Date loginDate, String appType
               ) {
        this.loginName = loginName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.loginDate = loginDate;
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
