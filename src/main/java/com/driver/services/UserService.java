package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WebSeriesRepository webSeriesRepository;


    public Integer addUser(User user){

        //Jut simply add the user to the Db and return the userId returned by the repository
        userRepository.save(user);
        return user.getId();
    }

    public Integer getAvailableCountOfWebSeriesViewable(Integer userId){

        //Return the count of all webSeries that a user can watch based on his ageLimit and subscriptionType
        //Hint: Take out all the Webseries from the WebRepository

        int count = 0;
        List<WebSeries> webSeriesList = webSeriesRepository.findAll();

        User user = userRepository.findById(userId).get();
        if(user != null) {
            String subscription = user.getSubscription().toString();

            if(subscription.equals("BASIC")) {
                for(int i=0; i<webSeriesList.size(); i++) {
                    if(webSeriesList.get(i).getAgeLimit()<=user.getAge() && webSeriesList.get(i).getSubscriptionType().toString().equals("BASIC")) {
                        count++;
                    }
                }
            }
            else if (subscription.equals("PRO")) {
                for(int i=0; i<webSeriesList.size(); i++) {
                    if(webSeriesList.get(i).getAgeLimit()<=user.getAge() && (webSeriesList.get(i).getSubscriptionType().toString().equals("BASIC") || webSeriesList.get(i).getSubscriptionType().toString().equals("PRO"))) {
                        count++;
                    }
                }
            }
            else if (subscription.equals("ELITE")) {
                for (int i=0; i<webSeriesList.size(); i++) {
                    if (webSeriesList.get(i).getAgeLimit()<=user.getAge()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }


}