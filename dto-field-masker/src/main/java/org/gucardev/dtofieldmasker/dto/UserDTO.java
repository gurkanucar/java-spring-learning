package org.gucardev.dtofieldmasker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gucardev.dtofieldmasker.annotation.MaskData;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("name")
    private String name;

    @MaskData(maskingOption = MaskData.MaskingOption.LAST_X_CHARS_PLAIN, value = 5)
    @JsonProperty("idNumber")
    private String idNumber;

    @JsonProperty("accounts")
    private List<AccountDTO> accountDTO;
}