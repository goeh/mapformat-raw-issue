package mapformat.raw.issue;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class MapformatRawIssueTest1 {

    @Inject
    AppConfig config;

    @Test
    void testItWorks() {
        Assertions.assertEquals("S3", config.getProvider("test").get().getName());
        Assertions.assertTrue(config.getProvider("test").get().getBucket("default").isPresent());
        Assertions.assertTrue(config.getProvider("test").get().getBucket("testBucket1").isPresent());
        Assertions.assertTrue(config.getProvider("test").get().getBucket("test-bucket-2").isPresent());
        Assertions.assertTrue(config.getProvider("test").get().getBucket("test-bucket-3").isPresent());
    }

}
