package br.edu.idp.es.stsw.hellocucumber.runners;

import io.cucumber.core.cli.Main;

public class RunCucumberTest {
        public static void main(String[] args) {
        Main.main(new String[]{
            "-g", "br.edu.idp.es.stsw.test.steps",
            "src/test/resources/features"
        });
    }
}
