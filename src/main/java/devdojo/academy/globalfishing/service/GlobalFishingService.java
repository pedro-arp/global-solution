package devdojo.academy.globalfishing.service;

import devdojo.academy.globalfishing.consumes.GlobalFishingApiConsumes;
import devdojo.academy.globalfishing.domain.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlobalFishingService {

    private final GlobalFishingApiConsumes consumes;

    public Report generateReportByGearType() {

        return consumes.generateReportByGearType();

    }

    public String generateUrlFromAPngByCountry(String country) {

        return consumes.generateUrlFromAPngByCountry(country);

    }

    public String generateUrlFromAPngByAllCountries() {

        return consumes.generatePngAllCountries();
    }
}
