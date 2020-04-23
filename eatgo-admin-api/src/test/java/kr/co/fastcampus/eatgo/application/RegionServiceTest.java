package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class RegionServiceTest {
    @Autowired
    RegionService regionService;
    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        regionService=new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region>  mockRegions=new ArrayList<>();
        mockRegions.add(Region.builder().name("seoul").build());
        given(regionService.getRegions()).willReturn(mockRegions);
        List<Region> regions=regionService.getRegions();
        Region region=regions.get(0);
        assertThat(region.getName(), is("seoul"));
    }
}