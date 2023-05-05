package com.driver.services;

import com.driver.EntryDto.WebSeriesEntryDto;
import com.driver.model.ProductionHouse;
import com.driver.model.WebSeries;
import com.driver.repository.ProductionHouseRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebSeriesService {
    @Autowired
    WebSeriesRepository webSeriesRepository;

    @Autowired
    ProductionHouseRepository productionHouseRepository;

    public Integer addWebSeries(WebSeriesEntryDto webSeriesEntryDto)throws  Exception{

        //Add a webSeries to the database and update the ratings of the productionHouse
        //Incase the seriesName is already present in the Db throw Exception("Series is already present")
        //use function written in Repository Layer for the same
        //Dont forget to save the production and webseries Repo

        WebSeries webSeries = new WebSeries();
        if (webSeriesRepository.findBySeriesName(webSeriesEntryDto.getSeriesName())!=null) throw new Exception("Series is already present");

        webSeries.setSeriesName(webSeriesEntryDto.getSeriesName());
        webSeries.setAgeLimit(webSeriesEntryDto.getAgeLimit());
        webSeries.setRating(webSeriesEntryDto.getRating());
        webSeries.setSubscriptionType(webSeriesEntryDto.getSubscriptionType());

        ProductionHouse productionHouse = productionHouseRepository.findById(webSeriesEntryDto.getProductionHouseId()).get();
        List<WebSeries> webSeriesList = productionHouse.getWebSeriesList();

        webSeries.setProductionHouse(productionHouse);
        webSeriesList.add(webSeries);
        productionHouse.setWebSeriesList(webSeriesList);

        double ratings = 0;
        for (WebSeries webSeries1 : webSeriesList){
            ratings += webSeries1.getRating();
        }

        ratings /= webSeriesList.size();
        productionHouse.setRatings(ratings);

        productionHouseRepository.save(productionHouse);
        webSeriesRepository.save(webSeries);

        return webSeries.getId();
    }

}
