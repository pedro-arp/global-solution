package devdojo.academy.globalfishing.controller;

import devdojo.academy.globalfishing.mapper.GlobalFishingMapper;
import devdojo.academy.globalfishing.response.ReportPostResponse;
import devdojo.academy.globalfishing.service.GlobalFishingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"global-fishing/v1/"})
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Global Fishing Application", description = "Uses external data to provide information about fishing vessels")
public class GlobalFishingController {

    private final GlobalFishingMapper mapper;

    private final GlobalFishingService service;

    @PostMapping("report")
    @Operation(summary = "Generate fishing effort report", description = "Generate fishing effort report grouped by gear type",
            responses = {
                    @ApiResponse(description = "Generate fishing effort report",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReportPostResponse.class))))
            })
    public ResponseEntity<ReportPostResponse> generateReportByGearType() {

        var report = service.generateReportByGearType();

        var response = mapper.reportResponse(report);

        return ResponseEntity.ok(response);

    }

    @PostMapping("genarate-png/heatmap/{country}")
    @Operation(summary = "Generate Curl", description = "Generates a curl using the fishing vessels traffic index filtered by country",
            responses = {
                    @ApiResponse(description = "Generate PNG about heat map fishing vessels filtered by country",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReportPostResponse.class))))
            })
    public ResponseEntity<String> generateCurlFromAPngByCountry(@PathVariable String country) {

        log.info("Request successful, the acronym for the country found is: '{}'", country);

        var url = service.generateUrlFromAPngByCountry(country);

        return ResponseEntity.ok(url);

    }

    @PostMapping("genarate-png/heatmap/ALL")
    @Operation(summary = "Generate Curl", description = "Generates a curl using the fishing vessels traffic index about all regions",
            responses = {
                    @ApiResponse(description = "Generate PNG about heat map fishing vessels about all regions",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReportPostResponse.class))))
            })
    public ResponseEntity<String> generateUrlFromAPngAllCountries() {

        var url = service.generateUrlFromAPngByAllCountries();

        return ResponseEntity.ok(url);
    }


}
