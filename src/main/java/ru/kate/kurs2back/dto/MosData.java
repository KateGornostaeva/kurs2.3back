package ru.kate.kurs2back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MosData {
    private Long global_id;
    
    @JsonProperty("Number")
    private int number;
    
    @JsonProperty("Cells")
    private Cells cells;
    
    
}
