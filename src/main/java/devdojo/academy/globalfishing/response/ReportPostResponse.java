package devdojo.academy.globalfishing.response;

import devdojo.academy.globalfishing.domain.report.Resources;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReportPostResponse {

    private Integer total;
    private List<Resources> entries;


}
