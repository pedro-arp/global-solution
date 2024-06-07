package devdojo.academy.globalfishing.consumes;

import devdojo.academy.globalfishing.domain.png.UrlPng;
import devdojo.academy.globalfishing.domain.report.Report;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@Log4j2
@RequiredArgsConstructor
public class GlobalFishingApiConsumes {

    private static final String URL = "https://gateway.api.globalfishingwatch.org/";
    private static final String GENERATE_PNG_BY_COUNTRY = "v3/4wings/generate-png?interval=YEAR&datasets[0]=public-global-fishing-effort:latest&filters[0]=flag in ('%s')&date-range=2024-01-01,2024-01-31";
    private static final String GENERATE_PNG_ALL_COUNTRIES = "v3/4wings/generate-png?interval=YEAR&datasets[0]=public-global-fishing-effort:latest&date-range=2024-01-01,2024-01-31";
    private static final String GENERATE_REPORT = "v2/4wings/report?spatial-resolution=low&temporal-resolution=entire&spatial-aggregation=false&datasets[0]=public-global-fishing-effort:v20231026&date-range=2024-05-01,2024-12-01&format=json";
    private static final String BODY = """
            {
                "region": {
                    "dataset": "public-mpa-all",
                    "id": 555745302
                }
            }""";


    @Value("${configuration.bearer.token}")
    private String bearerToken;

    private final AuthHeader accessToken;

    public Report generateReportByGearType() {

        var restTemplate = new RestTemplate();

        var entity = accessToken.setAuthInTheHeader(BODY);

        var exchange = restTemplate.exchange(URL + GENERATE_REPORT, HttpMethod.POST, entity, Report.class);

        return exchange.getBody();

    }


    public String generateUrlFromAPngByCountry(String country) {

        var restTemplate = new RestTemplate();

        var entity = accessToken.setAuthInTheHeader(null);

        var urlRequest = String.format(GENERATE_PNG_BY_COUNTRY, country);

        var exchange = restTemplate.exchange(URL + urlRequest, HttpMethod.POST, entity, UrlPng.class);

        var originalString = Objects.requireNonNull(exchange.getBody()).getUrl();

        var urlResponse = formatUrl(originalString);

        return String.format("curl --location --request GET  \"%s\" \\\n" + "-H \"Authorization: Bearer %s\" ", urlResponse, bearerToken);

    }

    public String generatePngAllCountries() {

        var restTemplate = new RestTemplate();

        var entity = accessToken.setAuthInTheHeader(null);

        var exchange = restTemplate.exchange(URL + GENERATE_PNG_ALL_COUNTRIES, HttpMethod.POST, entity, UrlPng.class);

        var urlRequest = Objects.requireNonNull(exchange.getBody()).getUrl();

        return String.format("curl --location --request GET  \"%s\" \\\n" + "-H \"Authorization: Bearer %s\" ", urlRequest, bearerToken);

    }

    public String formatUrl(String url) {
        return url.replace(" ", "%20").replace("'", "%27");
    }
}