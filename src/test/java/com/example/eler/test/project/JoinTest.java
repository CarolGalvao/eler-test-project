package com.example.eler.test.project;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class JoinTest {

    @InjectMocks
    private Join join = new Join();

    @Test
    void test(){
        join.callAll();
    }
}