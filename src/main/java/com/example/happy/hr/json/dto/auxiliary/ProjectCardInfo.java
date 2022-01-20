package com.example.happy.hr.json.dto.auxiliary;

import com.example.happy.hr.domain.wrappers.ProjectCardWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*  DTO для передачи записи из реестра  */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectCardInfo {
    private ProjectCardWrapper info;
    private String getCardUrl;
    private String putArchiveCard;
    private String deleteUnarchiveCard;
}
