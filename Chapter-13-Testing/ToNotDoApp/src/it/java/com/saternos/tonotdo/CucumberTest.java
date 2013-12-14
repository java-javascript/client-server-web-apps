package com.saternos.tonotdo;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"pretty"},
        features = "./src/it/resources"
)

// The class name must end in "Test" to be found by Maven by default
public class CucumberTest {
}
