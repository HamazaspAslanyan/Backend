package com.example.backend.db;

import com.example.backend.dtos.DanceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DB {

    public List<DanceDto> getDanceList(){
        List<DanceDto> danceDtos = new ArrayList<>();
        danceDtos.add(new DanceDto(10L, "Ալաշկերտի քոչարի", "Նկարագրություն", null, null, null, null, null));
        return danceDtos;
    }
}
