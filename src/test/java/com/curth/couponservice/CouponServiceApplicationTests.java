package com.curth.couponservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponServiceApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetCouponWithoutAuthentication_Forbidden() throws Exception {
        mvc.perform(get("/couponapi/coupon/COPON-CODE-007")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testGetCouponWithoutAuthentication_successAndEmpty() throws Exception {
        mvc.perform(get("/couponapi/coupon/COPON-CODE-007")).andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void testGetCouponWithoutAuthentication_success() throws Exception {
        mvc.perform(get("/couponapi/coupon/XX232B45-20")).andExpect(status().isOk())
                .andExpect(content()
                        .string("{\"id\":3,\"code\":\"XX232B45-20\",\"description\":\"Get 5% off on home applicances\",\"discount\":0.50,\"expDate\":\"2025-10-31\"}"));
    }

}
