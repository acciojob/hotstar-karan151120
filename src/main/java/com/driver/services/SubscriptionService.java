package com.driver.services;


import com.driver.EntryDto.SubscriptionEntryDto;
import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.repository.SubscriptionRepository;
import com.driver.repository.UserRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    public Integer buySubscription(SubscriptionEntryDto subscriptionEntryDto){

        //Save The subscription Object into the Db and return the total Amount that user has to pay
        Subscription subscription = new Subscription();
        subscription.setSubscriptionType(subscriptionEntryDto.getSubscriptionType());
        subscription.setNoOfScreensSubscribed(subscriptionEntryDto.getNoOfScreensRequired());
        Date date = new Date();
        subscription.setStartSubscriptionDate(date);
        int total = 0;
        String s = subscriptionEntryDto.getSubscriptionType().toString();
        if(s.equals("BASIC")) {
            total = 500 + 200*(subscriptionEntryDto.getNoOfScreensRequired());
        }
        else if (s.equals("PRO")) {
            total = 800 + 250*(subscriptionEntryDto.getNoOfScreensRequired());
        }
        else if (s.equals("ELITE")) {
            total = 1000 + 350*(subscriptionEntryDto.getNoOfScreensRequired());
        }
        subscription.setTotalAmountPaid(total);

        User user = userRepository.findById(subscriptionEntryDto.getUserId()).get();
        user.setSubscription(subscription);
        subscription.setUser(user);

        userRepository.save(user);
        subscriptionRepository.save(subscription);
        return total;
    }

    public Integer upgradeSubscription(Integer userId)throws Exception{

        //If you are already at an ElITE subscription : then throw Exception ("Already the best Subscription")
        //In all other cases just try to upgrade the subscription and tell the difference of price that user has to pay
        //update the subscription in the repository
        User user = userRepository.findById(userId).get();
        int cost = 0;
        if (user != null) {
            Subscription subscription = user.getSubscription();
            String s = subscription.getSubscriptionType().toString();

            if (s.equals("ELITE")) throw new Exception("Already the best Subscription");


            if (s.equals("BASIC")) {
                int screens = subscription.getNoOfScreensSubscribed();

                int tc = 800 + 250*screens;
                cost = tc - subscription.getTotalAmountPaid();
                subscription.setSubscriptionType(SubscriptionType.PRO);
                subscription.setTotalAmountPaid(tc);

                user.setSubscription(subscription);
                subscriptionRepository.save(subscription);
                userRepository.save(user);
            }
            else if (s.equals("PRO")) {
                int screens = subscription.getNoOfScreensSubscribed();

                int tc = 1000 + 350*screens;
                cost = tc - subscription.getTotalAmountPaid();
                subscription.setSubscriptionType(SubscriptionType.ELITE);
                subscription.setTotalAmountPaid(tc);

                user.setSubscription(subscription);
                subscriptionRepository.save(subscription);
                userRepository.save(user);
            }
        }
        return cost;
    }

    public Integer calculateTotalRevenueOfHotstar(){

        //We need to find out total Revenue of hotstar : from all the subscriptions combined
        //Hint is to use findAll function from the SubscriptionDb
        List<Subscription> subscriptionList = subscriptionRepository.findAll();
        int revenue = 0;

        for (Subscription subscription : subscriptionList) {
            revenue += subscription.getTotalAmountPaid();
        }
        return revenue;
    }

}
