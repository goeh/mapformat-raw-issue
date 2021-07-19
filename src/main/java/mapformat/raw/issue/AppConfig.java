package mapformat.raw.issue;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.convert.format.MapFormat;
import io.micronaut.core.naming.conventions.StringConvention;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ConfigurationProperties("storage")
public class AppConfig {

    @MapFormat
    private Map<String, ProviderConfig> providers = new HashMap<>();

    public Map<String, ProviderConfig> getProviders() {
        return providers;
    }

    public void setProviders(Map<String, ProviderConfig> providers) {
        this.providers = providers;
    }

    public Optional<ProviderConfig> getProvider(String name) {
        return Optional.ofNullable(this.providers.get(name));
    }

    @ConfigurationProperties("providers")
    public static class ProviderConfig {

        private String name;

        @MapFormat(transformation = MapFormat.MapTransformation.FLAT, keyFormat = StringConvention.RAW)
        private Map<String, String> buckets = new HashMap<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, String> getBuckets() {
            return buckets;
        }

        public void setBuckets(Map<String, String> buckets) {
            this.buckets = buckets;
        }

        public Optional<String> getBucket(String name) {
            return Optional.ofNullable(this.buckets.get(name));
        }
    }
}

