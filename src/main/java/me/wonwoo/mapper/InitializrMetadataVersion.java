package me.wonwoo.mapper;

import org.springframework.http.MediaType;

/**
 * Created by hellowd on 2016-11-16.
 */
public enum InitializrMetadataVersion {

    /**
     * HAL-compliant metadata.
     */
    V2("application/vnd.initializr.v2+json"),

    /**
     * Add 'versionRange' attribute to any dependency to specify which
     * Spring Boot versions are compatible with it. Also provide a
     * separate 'dependencies' endpoint to query dependencies meta-data.
     */
    V2_1("application/vnd.initializr.v2.1+json");

    private final MediaType mediaType;

    InitializrMetadataVersion(String mediaType) {
        this.mediaType = MediaType.parseMediaType(mediaType);
    }

    public MediaType getMediaType() {
        return mediaType;
    }
}