# Cloudflare Trace API
CfTrace is a Java API for interacting with Cloudflare's Trace system and retrieving various trace and geo-related data. 

Add via Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
...
<dependency>
    <groupId>com.rukanuel</groupId>
    <artifactId>cftrace</artifactId>
    <version>1</version>
</dependency>
```

Add via Gradle:
```xml
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
...
dependencies {
    implementation 'com.rukanuel:cftrace:1'
}
```

## Usage
### Trace Data
The ```Trace``` method allows you to query different Cloudflare trace endpoints. You can specify the provider and optionally extract a specific key from the trace data.
```java
import com.rukanuel.cftrace.CfTrace;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Example of retrieving trace data from Cloudflare
        String traceData = CfTrace.Trace("cloudflare", "clientIp");
        System.out.println("Client IP: " + traceData);
    }
}
```

**Parameters**:
- ```provider``` (String): The name of the provider. Some examples include:
  - ```"101"```, ```"cloudflare"```, ```"cloudflare-eth"```, ```"workers"```, ```"pages"```, ```"tv"```, ```"icanhazip"```
- ```key``` (String): Optionally specify a key to extract a specific value from the trace data. If the key is not found, ```"Key not found"``` will be returned. If no key is provided, the entire trace data is returned.

### Geo Data
You can also retrieve geo-location data from Cloudflare’s speed test metadata endpoint. The Geo method returns the full response or allows you to query for specific keys.
```java
import com.rukanuel.cftrace.CfTrace;

public class Main {
    public static void main(String[] args) {
        // Example of retrieving full geo data
        String geoData = CfTrace.Geo();
        System.out.println("Geo Data: " + geoData);
    
        // Example of retrieving a specific geo key (e.g., country)
        String country = CfTrace.Geo("country");
        System.out.println("Country: " + country);
    }
}
```
**Parameters**:

- ```key``` (String): Specify a key to extract from the geo JSON response. If left empty, the entire response is returned.