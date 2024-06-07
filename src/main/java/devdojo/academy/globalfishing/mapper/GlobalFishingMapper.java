package devdojo.academy.globalfishing.mapper;

import devdojo.academy.globalfishing.domain.report.Report;
import devdojo.academy.globalfishing.response.ReportPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface GlobalFishingMapper {
    ReportPostResponse reportResponse(Report report);

}
