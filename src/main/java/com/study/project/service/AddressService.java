package com.study.project.service;

import com.study.project.entity.Address;
import com.study.project.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    // 데이터 전체를 조회하기 위한 메소드
    public List<Address> addressList() {
        return addressRepository.findAll();
    }

    // 특정 지역이 포함된 데이터를 조회하기 위한 메소드 (서울특별시, 부산광역시, ...)
    public List<Address> addressSearchList(String searchKeyword) {
        return addressRepository.findByAddoContaining(searchKeyword);
    }

}
