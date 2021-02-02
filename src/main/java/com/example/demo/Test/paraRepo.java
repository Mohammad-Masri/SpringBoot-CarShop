package com.example.demo.Test;//package com.example.demo.Test;
//
//import com.example.demo.Models.Parameter;
//import com.example.demo.Repositories.ParameterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Component
//public class paraRepo implements ParameterRepository
//{
//
//    @Autowired
//    ParameterRepository parameterRepository;
//
//
//    private void simulateSlowService() {
//        try {
//            long time = 3000L;
//            Thread.sleep(time);
//        } catch (InterruptedException e) {
//            throw new IllegalStateException(e);
//        }
//    }
//
//    @CachePut("parameter")
//    @Override
//    public Parameter findByMykey(String myKey) {
//        simulateSlowService();
//        return parameterRepository.findByMykey(myKey);
//    }
//
//    @Override
//    public <S extends Parameter> S save(S entity) {
//        return parameterRepository.save(entity);
//    }
//
//    @Override
//    public <S extends Parameter> Iterable<S> saveAll(Iterable<S> entities) {
//        return parameterRepository.saveAll(entities);
//    }
//
//    @Override
//    public Optional<Parameter> findById(Integer integer) {
//        return parameterRepository.findById(integer);
//    }
//
//    @Override
//    public boolean existsById(Integer integer) {
//        return parameterRepository.existsById(integer);
//    }
//
//    @Cacheable("parameter")
//    @CachePut("parameter")
//    @Override
//    public Iterable<Parameter> findAll() {
//        simulateSlowService();
//        return parameterRepository.findAll();
//    }
//
//    @Override
//    public Iterable<Parameter> findAllById(Iterable<Integer> integers) {
//        return parameterRepository.findAllById(integers);
//    }
//
//    @Override
//    public long count() {
//        return parameterRepository.count();
//    }
//
//    @Override
//    public void deleteById(Integer integer) {
//        parameterRepository.deleteById(integer);
//    }
//
//    @Override
//    public void delete(Parameter entity) {
//        parameterRepository.delete(entity);
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Parameter> entities) {
//        parameterRepository.deleteAll(entities);
//    }
//
//    @Override
//    public void deleteAll() {
//        parameterRepository.deleteAll();
//    }
//}
