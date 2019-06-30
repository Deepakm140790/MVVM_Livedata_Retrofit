package com.app.testapp.network;

import org.junit.Test;

import static org.junit.Assert.*;

public class RetrofitClientTest {

    @Test
    public void getClient() {
        assertNotNull(RetrofitClient.getClient());
    }
}