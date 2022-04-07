package com.adi.project.service;

import com.adi.project.model.ApiHosting;
import com.adi.project.repository.ApiHostingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiHostingServiceTest {

    @Mock
    ApiHostingRepository apiHostingRepository;

    @InjectMocks
    ApiHostingService apiHostingService;

    @Test
    void should_return_all_of_the_hosting_names() {
        // given
        List<ApiHosting> hostings = new ArrayList<>();
        ApiHosting apiHosting = new ApiHosting("Spring");
        hostings.add(apiHosting);
        ApiHosting apiHosting2 = new ApiHosting("Django");
        hostings.add(apiHosting2);
        // when
        when(apiHostingRepository.findAll())
                .thenReturn(hostings);
        List<String> returnedNames = apiHostingService.findAllNames();
        // then
        assertEquals("Spring", returnedNames.get(0));
    }

    @Test
    void should_return_one_hosting_name() {
        // given
        List<ApiHosting> hostings = new ArrayList<>();
        ApiHosting apiHosting = new ApiHosting("Spring");
        hostings.add(apiHosting);
        ApiHosting apiHosting2 = new ApiHosting("Django");
        hostings.add(apiHosting2);

        // when
        when(apiHostingRepository.findAll())
                .thenReturn(hostings);
        List<String> returnedNames = apiHostingService.findAllNames();

        // then
        assertEquals(2, returnedNames.size());
    }

    @Test
    void should_return_api_hosting_with_the_same_name_when_saved() {
        // given
        ApiHosting apiHosting = new ApiHosting();
        apiHosting.setName("Spring");

        // when
        when(apiHostingRepository.save(ArgumentMatchers.any(ApiHosting.class)))
                .thenReturn(apiHosting);
        ApiHosting newHosting = apiHostingService.save(apiHosting);

        // then
        assertEquals(apiHosting.getName(), newHosting.getName());
    }
    
}