package core.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class OperationDto {
    private long userId;
    private double sum;
    private String type;
    private LocalDate date;

}
