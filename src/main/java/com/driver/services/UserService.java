package com.driver.services;


import com.driver.model.Subscription;
import com.driver.model.SubscriptionType;
import com.driver.model.User;
import com.driver.model.WebSeries;
import com.driver.repository.UserRepository;
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
public class UserService {


    UserRepository userRepository = new UserRepository() {
        @Override
        public List<User> findAll() {
            return null;
        }

        @Override
        public List<User> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<User> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public <S extends User> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends User> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<User> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public User getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<User> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends User> S save(S s) {
            return null;
        }

        @Override
        public Optional<User> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(User user) {

        }

        @Override
        public void deleteAll(Iterable<? extends User> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends User> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends User> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends User> boolean exists(Example<S> example) {
            return false;
        }
    };


    WebSeriesRepository webSeriesRepository = new WebSeriesRepository() {
        @Override
        public WebSeries findBySeriesName(String seriesName) {
            return null;
        }

        @Override
        public List<WebSeries> findAll() {
            return null;
        }

        @Override
        public List<WebSeries> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<WebSeries> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public <S extends WebSeries> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends WebSeries> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<WebSeries> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public WebSeries getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends WebSeries> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends WebSeries> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<WebSeries> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends WebSeries> S save(S s) {
            return null;
        }

        @Override
        public Optional<WebSeries> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(WebSeries webSeries) {

        }

        @Override
        public void deleteAll(Iterable<? extends WebSeries> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends WebSeries> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends WebSeries> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends WebSeries> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends WebSeries> boolean exists(Example<S> example) {
            return false;
        }
    };


    public Integer addUser(User user){

        //Jut simply add the user to the Db and return the userId returned by the repository
        User user1 = userRepository.save(user);
        return user1.getId();
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
