package com.kezhevatov.math.web.controller;

import com.kezhevatov.math.exception.ErrorCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.kezhevatov.math.web.controller.MathController.SEQUENCE_PATH;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Andrey Kezhevatov.
 * Date: 03.12.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CosSequenceGeneratorWebTest {

    private static final String ROOT_PATH = "/";

    private static final String RESULT_FIELD = "sequence";

    private static final String ERROR_CODE_FIELD = "errorCode";

    @Autowired
    private WebApplicationContext context;

    @Value("${cosSequenceGenerator.maxValue}")
    private int maxValue;

    private MockMvc mvc;

    @Before
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetSequence3() throws Exception {
        String test3Id = "3";
        String test3Result = "((cos(1)+3)cos(1-cos(2))+2)cos(1-cos(2+cos(3)))+1";

        mvc.perform(get(ROOT_PATH + SEQUENCE_PATH).param("n", test3Id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(test3Result)));

    }

    @Test
    public void testGetGreaterThanMax() throws Exception {
        String testId = Integer.toString(maxValue + 1);
        String testResult = ErrorCode.ARGUMENT_NOT_VALID.toString();

        mvc.perform(get(ROOT_PATH + SEQUENCE_PATH).param("n", testId))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(ERROR_CODE_FIELD, equalTo(testResult)));
    }

    @Test
    public void testGetLessThanMin() throws Exception {
        String testId = "0";
        String testResult = ErrorCode.ARGUMENT_NOT_VALID.toString();

        mvc.perform(get(ROOT_PATH + SEQUENCE_PATH).param("n", testId))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath(ERROR_CODE_FIELD, equalTo(testResult)));
    }
}
