package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.repository.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import sun.misc.Resource;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.*;


@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitRepository dentistVisitRepository;


    public void addVisit(String dentistName, Date visitDate, Date visitTime) {
        Calendar cal = Calendar.getInstance(); //https://stackoverflow.com/questions/41753223/combine-two-type-dates-into-one?noredirect=1&lq=1
        cal.setTime(visitTime);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        cal.setTime(visitDate);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);

        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity(dentistName, cal);
        dentistVisitRepository.save(dentistVisitEntity);
    }

    public List<DentistVisitDTO> getAllVisits() {
        ArrayList<DentistVisitDTO> dentistVisits = new ArrayList<>();
        for (DentistVisitEntity dentist :
                dentistVisitRepository.findAll()) {
            DentistVisitDTO dentistVisitDTO = new DentistVisitDTO();
            dentistVisitDTO.setDentistName(dentist.getDentistName());
            dentistVisitDTO.setVisitTime(dentist.getVisitTime().getTime());
            dentistVisitDTO.setId(dentist.getId());
            dentistVisits.add(dentistVisitDTO);
        }
        return dentistVisits;
    }


    public DentistVisitDTO getVisitById(Long id) {
        DentistVisitEntity dentist = dentistVisitRepository.findOne(id);

        DentistVisitDTO dentistVisitDTO = new DentistVisitDTO();
        dentistVisitDTO.setDentistName(dentist.getDentistName());
        dentistVisitDTO.setVisitTime(dentist.getVisitTime().getTime());
        dentistVisitDTO.setId(dentist.getId());

        return dentistVisitDTO;
    }

    public void deleteVisitById(Long id) {
        if (dentistVisitRepository.exists(id)) {
            dentistVisitRepository.delete(id);
        }
    }

    public void updateVisit(Long id, String dentistName, Date visitDate, Date visitTime) {
        if (dentistVisitRepository.exists(id)) {
            DentistVisitEntity dentistVisitToUpdate = dentistVisitRepository.getOne(id);
            dentistVisitToUpdate.setDentistName(dentistName);
            Calendar cal = Calendar.getInstance(); //https://stackoverflow.com/questions/41753223/combine-two-type-dates-into-one?noredirect=1&lq=1
            cal.setTime(visitTime);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            cal.setTime(visitDate);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, min);
            dentistVisitToUpdate.setVisitTime(cal);
        }
    }
}
