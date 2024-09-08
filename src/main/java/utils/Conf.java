package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * {@code Conf} handles actions for reading {@code .properties} files
 */
public final class Conf {
    private static final Logger logger = LoggerFactory.getLogger(Conf.class);

    /**
     * Reads framework config from {@cоde framework.properties}.
     *
     * @param key the config key (Example :{@code browser=CHROME}, key = {@code browser})
     * @return String - the value of the given key (Example: {@code browser=CHROME}, value = {@code CHROME}
     */
    public static String framework(String key) {
        return getKeyValueFromPropertyFile("framework.properties", key);
    }

    public static String constant(String key) {
        return getKeyValueFromPropertyFile("constant.properties", key);
    }

    /**
     * Reads project config from {@code project.properties}.
     *
     * @param key the config key
     * @return String - the value of the given key
     */
    public static String project(String key) {
        return project(key);
    }

    private static String getKeyValueFromPropertyFile(String propertyDir, String key) {
        InputStream inputStream;
        Properties properties;

        try {
            inputStream = new FileInputStream(setPropertyFileDir(propertyDir));
            properties = new Properties();
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String keyValue = properties.getProperty(key);
            if (StringUtils.isWhitespace(keyValue)) {
                throw new NullPointerException(String.format("Value for %s is empty in %s", key, propertyDir));
            }

            if (StringUtils.isBlank(keyValue)) {
                throw new NullPointerException(String.format("%s not found in %s", key, propertyDir));
            }

            return StringUtils.trim(keyValue);
        } catch (IOException e) {
            logger.warn(e.getMessage());
            Assert.fail(e.getMessage());

            return e.getMessage();
        }
    }

    private static String setPropertyFileDir(String propertyDir) {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtils.replace(System.getProperty("user.dir"), "\\", "/"));
        builder.append("/src/main/resources/");
        builder.append(propertyDir);

        return builder.toString();
    }

    public static String routes(String key) {
        return getKeyValueFromPropertyFile("routes.properties", key);
    }
}