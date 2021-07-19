## Micronaut Issue #5778

https://github.com/micronaut-projects/micronaut-core/issues/5778

- Micronaut Version 2.5.9
---

### Problem

I have the following configuration file:

```
storage:
  providers:
    test:
      name: S3
      buckets:
        default: "The default bucket"
        testBucket1: "Bucket without hyphens"
        test-bucket-2: "Some hyphenated bucket"
        test-bucket-3: "Another hyphenated bucket"
```
A configuration class `AppConfig` is annotated with `@ConfigurationProperties` and it contains nested configuration
using static inner classes. However, the keys in the *buckets* map are not preserved.

The `buckets` property is annotated with `@MapFormat` AND `RAW`, but the keys are converted to camelCase.
I want the keys to be preserved as they are in the property file.

```
@MapFormat(transformation = MapFormat.MapTransformation.FLAT, keyFormat = StringConvention.RAW)
private Map<String, String> buckets = new HashMap<>();
```
### Debugging

When `ProviderConfig#setBuckets(Map<String, String> buckets)` is called, the `buckets` parameter is already a `Map` with incorrect (camelCase) keys.

### Test

```
./gradlew test
```
