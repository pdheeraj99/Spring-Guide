package io.mawa.spring.core.validation.conversion;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.NumberFormat;

public class MyBeanWithFormatting {

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal salary;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date joinDate;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "MyBeanWithFormatting{" +
                "salary=" + salary +
                ", joinDate=" + joinDate +
                '}';
    }
}
