package by.epam.library.domain;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public class User extends Entity {
    private String surname;
    private String name;
    private String patronymic;
    private String subscription;
    private String address;
    private String phoneHome;
    private String phoneMobile;
    private String email;
    private Role role;
    private String login;
    private String password;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!surname.equals(user.surname)) return false;
        if (!name.equals(user.name)) return false;
        if (!patronymic.equals(user.patronymic)) return false;
        if (subscription != null ? !subscription.equals(user.subscription) : user.subscription != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (phoneHome != null ? !phoneHome.equals(user.phoneHome) : user.phoneHome != null) return false;
        if (phoneMobile != null ? !phoneMobile.equals(user.phoneMobile) : user.phoneMobile != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        if (!login.equals(user.login)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = surname.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + (subscription != null ? subscription.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneHome != null ? phoneHome.hashCode() : 0);
        result = 31 * result + (phoneMobile != null ? phoneMobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + role.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
