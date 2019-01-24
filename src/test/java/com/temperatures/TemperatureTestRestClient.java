package com.temperatures;

import com.temperatures.model.Temperature;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class TemperatureTestRestClient {


    private static String url = "http://localhost:8080/temperatures";

    @Test
    public void testAPI() throws InterruptedException {
        //setup
        Temperature tempFixture = new Temperature();
        tempFixture.setTemperatureInCelsius(99D);

        //execute post
        RestTemplate restTemplate = new RestTemplate();
        Temperature tempCreated = restTemplate.postForObject(url, tempFixture, Temperature.class);

        //validate
        Assert.assertTrue(0 < tempCreated.getId());
        Assert.assertEquals(tempFixture.getTemperatureInCelsius(), tempCreated.getTemperatureInCelsius(), 0.001);
        Assert.assertNotNull(tempCreated.getCreateDate());
        Assert.assertNotNull(tempCreated.getUpdateDate());
        System.out.println("Created temperature " + tempCreated);

        // sleep one second as update date and time is accurate to the second
        TimeUnit.SECONDS.sleep(1);

        //execute put
        restTemplate.put(url + "/" + tempCreated.getId(), tempCreated);

        //execute get
        Temperature[] allTemps = restTemplate.getForObject(url, Temperature[].class);
        System.out.println("All temperatures after put : " + Arrays.toString(allTemps));

        Assert.assertNotNull(allTemps);
        for (Temperature temp : allTemps) {
            if (temp.getId() == tempCreated.getId()){
                Assert.assertTrue(temp.getUpdateDate().compareTo(tempCreated.getUpdateDate()) > 0);
            }
        }

        //execute delete
        restTemplate.delete(url + "/" + tempCreated.getId());

        //execute get
        Temperature[] allTempAfterDelete = restTemplate.getForObject(url, Temperature[].class);
        System.out.println("All temperatures after delete : " + Arrays.toString(allTempAfterDelete));

        for (Temperature temp : allTempAfterDelete) {
            Assert.assertTrue(temp.getId() != tempCreated.getId());
        }
    }
}
