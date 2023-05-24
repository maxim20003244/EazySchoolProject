package com.eazybyte.springschoolproject.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EazySchoolInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String> eazyMap = new HashMap<>();
        eazyMap.put("App Name" , "EazySchool");
        eazyMap.put("App Discription" , "EazySchool web Application for students and Admin");
        eazyMap.put("Contact mobile", "+4474325236778");
        eazyMap.put("App version" , "1.0.0");
        builder.withDetail("eazyschool-info",eazyMap);

    }
}
