package org.gucardev.awss3fileservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultiplePreSignedUrlRequest {

    private String extension;
    private String originalFileName;

}
