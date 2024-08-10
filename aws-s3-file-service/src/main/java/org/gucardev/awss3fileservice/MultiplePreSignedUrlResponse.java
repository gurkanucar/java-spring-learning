package org.gucardev.awss3fileservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultiplePreSignedUrlResponse {

    private String url;
    private String file;
    private String originalFileName;

}
