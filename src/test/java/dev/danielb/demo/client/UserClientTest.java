package dev.danielb.demo.client;

import dev.danielb.demo.ExternalServiceConfiguration;
import dev.danielb.demo.ExternalServiceProperties;
import dev.danielb.demo.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserClientTest {


    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(RestClientAutoConfiguration.class, ExternalServiceConfiguration.class));


    @Test
    void testUserClient() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(UserClient.class);
        });
    }

    @Test
    void shouldContainDefaultBaseUrl() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(ExternalServiceProperties.class);
            assertThat(context.getBean(ExternalServiceProperties.class).baseUrl()).isEqualTo("https://jsonplaceholder.typicode.com");
        });
    }

    @Test
    void shouldSetCustomBaseUrl() {
        contextRunner
                .withPropertyValues("external-service.base-url=https://www.google.com")
                .run(context -> {
                    assertThat(context).hasSingleBean(ExternalServiceProperties.class);
                    assertThat(context.getBean(ExternalServiceProperties.class).baseUrl()).isEqualTo("https://www.google.com");
                });
    }

    @Test
    void testFindAll() {
        contextRunner.run(context -> {
            UserClient userClient = context.getBean(UserClient.class);
            assertEquals(userClient.findAll().size(), 10);
        });
    }

    @Test
    void testFindById() {
        contextRunner.run(context -> {
            UserClient userClient = context.getBean(UserClient.class);
            assertEquals(userClient.findById("1").id(), "1");
        });
    }

    @Test
    void testCreate() {
        contextRunner.run(context -> {
            UserClient userClient = context.getBean(UserClient.class);
            User user = new User("11", "Daniel", "danielb", "a@k.ro", null, "123", "www", null);
            User actual = userClient.create(user);
            assertEquals(actual.id(), "11");
            assertEquals(actual.name(), "Daniel");
        });
    }

    @Test
    void testUpdate() {
        contextRunner.run(context -> {
            UserClient userClient = context.getBean(UserClient.class);
            User user = new User("1", "Daniel", "danielb", "a@k.ro", null, "123", "www", null);
            assertEquals(userClient.update(user).id(), "1");
        });
    }

    @Test
    void testDelete() {
        contextRunner.run(context -> {
            UserClient userClient = context.getBean(UserClient.class);
            assertEquals(userClient.delete("1").getStatusCode().value(), 200);
        });
    }
}