package no.vipps.twitchecom.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class GetAccessTokenDTO {
    private String token_type;
    private String expires_in;
    private String ext_expires_in;
    private String expires_on;
    private String not_before;
    private String resource;
    private String access_token;

    public GetAccessTokenDTO(String token_type, String expires_in, String ext_expires_in, String expires_on, String not_before, String resource, String access_token) {
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.ext_expires_in = ext_expires_in;
        this.expires_on = expires_on;
        this.not_before = not_before;
        this.resource = resource;
        this.access_token = access_token;
    }

    public GetAccessTokenDTO() {
    }

    public Boolean hasExired() {
        LocalDateTime dateNow = Instant.ofEpochSecond(System.currentTimeMillis() / 1000).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime dateExpire = Instant.ofEpochSecond(Long.valueOf(expires_on)).atZone(ZoneId.systemDefault()).toLocalDateTime();

        return dateNow.isAfter(dateExpire);
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getExt_expires_in() {
        return ext_expires_in;
    }

    public void setExt_expires_in(String ext_expires_in) {
        this.ext_expires_in = ext_expires_in;
    }

    public String getExpires_on() {
        return expires_on;
    }

    public void setExpires_on(String expires_on) {
        this.expires_on = expires_on;
    }

    public String getNot_before() {
        return not_before;
    }

    public void setNot_before(String not_before) {
        this.not_before = not_before;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}