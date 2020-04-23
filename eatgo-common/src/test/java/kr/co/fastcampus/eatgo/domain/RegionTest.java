package kr.co.fastcampus.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RegionTest {
    @Test
    public void creation(){
        Region region=Region.builder().name("seoul").build();
        assertThat(region.getName(), is("seoul"));
    }

}