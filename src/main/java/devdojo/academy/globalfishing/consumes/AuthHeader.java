package devdojo.academy.globalfishing.consumes;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AuthHeader {

    @Value("${configuration.bearer.token}")
    private String bearerToken;

    public HttpEntity<String> setAuthInTheHeader(String body) {

        var headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + bearerToken);

        return new HttpEntity<>(body, headers);

    }

}
