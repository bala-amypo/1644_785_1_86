
package com.example.demo.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmRequest {
    private String name;
    private double lat;
    private double lon;
    private String soil;
}
