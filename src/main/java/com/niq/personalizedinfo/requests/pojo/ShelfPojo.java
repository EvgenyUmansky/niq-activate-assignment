package com.niq.personalizedinfo.requests.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShelfPojo implements Serializable {
    String productId;
    Double relevancyScore;
}
