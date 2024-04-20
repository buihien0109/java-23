package com.example.demo.dao.impl;

import com.example.demo.dao.PersonDAO;
import com.example.demo.database.PersonDB;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PersonDAOImpl implements PersonDAO {
    @Override
    public void printListPeople(List<Person> persons) {

    }

    @Override
    public List<Person> getAll() {
        return PersonDB.people;
    }

    @Override
    public List<Person> sortPeopleByFullName() {
        return List.of();
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return List.of();
    }

    @Override
    public List<String> getSortedJobs() {
        return List.of();
    }

    @Override
    public List<String> getSortedCities() {
        return List.of();
    }

    @Override
    public List<String> femaleNames() {
        return List.of();
    }

    @Override
    public Person highestEarner() {
        return null;
    }

    @Override
    public List<Person> bornAfter1990() {
        return List.of();
    }

    @Override
    public double averageSalary() {
        return 0;
    }

    @Override
    public double averageAge() {
        return 0;
    }

    @Override
    public List<Person> nameContains(String keyword) {
        return List.of();
    }

    @Override
    public List<Person> sortedByAgeForMale() {
        return List.of();
    }

    @Override
    public Person longestName() {
        return null;
    }

    @Override
    public List<Person> aboveAverageSalary() {
        return List.of();
    }

    @Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return Map.of();
    }

    @Override
    public Map<String, Integer> groupJobByCount() {
        return Map.of();
    }
}
