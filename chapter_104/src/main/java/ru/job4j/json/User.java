package ru.job4j.json;

import java.util.Objects;

public class User {
    private String name;
    private String sname;
    private String email;
    private String pwd;
    private String sex;
    private String description;

    public User(String name, String sname, String email, String pwd, String sex, String description) {
        this.name = name;
        this.sname = sname;
        this.email = email;
        this.pwd = pwd;
        this.sex = sex;
        this.description = description;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(sname, user.sname)
                && Objects.equals(email, user.email)
                && Objects.equals(pwd, user.pwd)
                && Objects.equals(sex, user.sex)
                && Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sname, email, pwd, sex, description);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", sname='" + sname + '\''
                + ", email='" + email + '\''
                + ", pwd='" + pwd + '\''
                + ", sex='" + sex + '\''
                + ", description='" + description + '\''
                + '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
