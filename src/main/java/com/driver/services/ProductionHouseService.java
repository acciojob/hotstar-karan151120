package com.driver.services;


import com.driver.EntryDto.ProductionHouseEntryDto;
import com.driver.model.ProductionHouse;
import com.driver.repository.ProductionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionHouseService {


    ProductionHouseRepository productionHouseRepository = new ProductionHouseRepository() {
        @Override
        public List<ProductionHouse> findAll() {
            return null;
        }

        @Override
        public List<ProductionHouse> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<ProductionHouse> findAllById(Iterable<Integer> iterable) {
            return null;
        }

        @Override
        public <S extends ProductionHouse> List<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends ProductionHouse> S saveAndFlush(S s) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<ProductionHouse> iterable) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public ProductionHouse getOne(Integer integer) {
            return null;
        }

        @Override
        public <S extends ProductionHouse> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends ProductionHouse> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<ProductionHouse> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends ProductionHouse> S save(S s) {
            return null;
        }

        @Override
        public Optional<ProductionHouse> findById(Integer integer) {
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
        public void delete(ProductionHouse productionHouse) {

        }

        @Override
        public void deleteAll(Iterable<? extends ProductionHouse> iterable) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends ProductionHouse> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends ProductionHouse> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends ProductionHouse> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends ProductionHouse> boolean exists(Example<S> example) {
            return false;
        }
    };

    public Integer addProductionHouseToDb(ProductionHouseEntryDto productionHouseEntryDto){

        ProductionHouse productionHouse = new ProductionHouse();
        productionHouse.setName(productionHouseEntryDto.getName());
        double ratings = 0;
        productionHouse.setRatings(ratings);
        productionHouse.setWebSeriesList(new ArrayList<>());

        productionHouseRepository.save(productionHouse);

        return productionHouse.getId();
    }



}
