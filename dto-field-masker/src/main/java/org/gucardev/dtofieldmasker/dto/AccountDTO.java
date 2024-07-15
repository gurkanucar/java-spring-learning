package org.gucardev.dtofieldmasker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gucardev.dtofieldmasker.annotation.MaskData;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @JsonProperty("accountName")
    private String accountName;

    @JsonProperty("accountNumber")
    @MaskData(replaceChar = "*", maskingOption = MaskData.MaskingOption.LAST_X_CHARS_MASKED, value = 10)
    private String accountNumber;
}