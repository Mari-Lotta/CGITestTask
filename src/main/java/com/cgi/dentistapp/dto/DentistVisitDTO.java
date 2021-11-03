package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.util.Date;


public class DentistVisitDTO {

    @Size(min = 1, max = 50)
    String dentistName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date visitDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    Date visitTime;

    Long id;

    public DentistVisitDTO(String dentistName, Date visitTime, Date visitDate) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
        this.visitDate = visitDate;
    }
    public DentistVisitDTO(String dentistName, Date visitTime, Date visitDate, Long id) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
        this.visitDate = visitDate;
    }

    public DentistVisitDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
