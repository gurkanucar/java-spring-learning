package org.gucardev.awss3fileservice;

import java.io.InputStream;

public record S3ObjectInputStreamWrapper(InputStream inputStream, String eTag) {
}
