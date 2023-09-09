package com.niq.personalizedinfo.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.niq.personalizedinfo.requests.pojo.ShelfPojo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopperPersonalizedProductRequest implements Serializable {
    String shopperId;

    @JsonAlias({"shelfList", "shelf"})
    List<ShelfPojo> shelfList;
}
