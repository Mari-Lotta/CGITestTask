package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistService {
    @Autowired
    private DentistRepository dentistRepository;

    public List<DentistDTO> getAll(){
        ArrayList<DentistDTO> dentists = new ArrayList<>();
        for (DentistEntity dentist:
                dentistRepository.findAll()) {
            DentistDTO dentistDTO = new DentistDTO();
            dentistDTO.setFirstName(dentist.getFirstName());
            dentistDTO.setLastName(dentist.getLastName());
            dentists.add(dentistDTO);
        }
        return dentists;
    }
}
