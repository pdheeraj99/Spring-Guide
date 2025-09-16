package io.mawa.spring.core.bfpp;

public class DataSourceBean {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DataSourceBean{" +
                "password='" + password + '\'' +
                '}';
    }
}
