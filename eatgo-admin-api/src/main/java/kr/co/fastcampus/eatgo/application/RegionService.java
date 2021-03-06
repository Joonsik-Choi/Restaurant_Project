package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;
    RegionService(RegionRepository regionRepository){
        this.regionRepository=regionRepository;
    }
    public List<Region> getRegions() {
        List<Region> regions=regionRepository.findAll();
        return regions;
    }
}
