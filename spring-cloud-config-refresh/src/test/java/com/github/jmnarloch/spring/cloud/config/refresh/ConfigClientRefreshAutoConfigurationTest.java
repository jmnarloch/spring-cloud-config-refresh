/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jmnarloch.spring.cloud.config.refresh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the {@link ConfigClientRefreshAutoConfiguration} class.
 *
 * @author Jakub Narloch
 */
@SpringApplicationConfiguration(
        classes = ConfigClientRefreshAutoConfigurationTest.TestConfiguration.class
)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigClientRefreshAutoConfigurationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldRegisterSchedulerPostProcessor() {

        // when
        ScheduledAnnotationBeanPostProcessor bean =
                applicationContext.getBean(ScheduledAnnotationBeanPostProcessor.class);

        // then
        assertNotNull(bean);
    }

    @Import({RefreshAutoConfiguration.class, ConfigClientRefreshAutoConfiguration.class})
    @EnableAutoConfiguration
    @Configuration
    public static class TestConfiguration {

    }
}