package com.example.demo.service;

import com.example.demo.entity.Fertilizerentity;

import java.util.List;

public interface Fertilizerservice {

    Fertilizerentity addFertilizer(Fertilizerentity fertilizer);

    List<Fertilizer> getAllFertilizers();

    Fertilizerentity getFertilizerById(Long id);
}
