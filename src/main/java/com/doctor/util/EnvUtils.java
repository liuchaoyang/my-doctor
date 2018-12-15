package com.doctor.util;

import com.doctor.enums.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvUtils {

    @Autowired
    private Environment environment;

    public Env activeEnv() {
        String[] activeProfiles = environment.getActiveProfiles();
        return  Env.valueOf(activeProfiles[0].toUpperCase());
    }
}
