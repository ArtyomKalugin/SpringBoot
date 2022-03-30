package ru.stud.kpfu.kalugin.controller.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.stud.kpfu.kalugin.helper.JsonHelper;

import java.util.Map;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class JsonHelperTest {

    private final JsonHelper jsonHelper = new JsonHelper();

    @Test
    public void testParseJson() throws JsonProcessingException {
        String json = "{\"coord\":{\"lon\":37.6156,\"lat\":55.7522},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":270.55,\"feels_like\":266.31,\"temp_min\":270.07,\"temp_max\":272.25,\"pressure\":1008,\"humidity\":62,\"sea_level\":1008,\"grnd_level\":989},\"visibility\":10000,\"wind\":{\"speed\":3.23,\"deg\":273,\"gust\":5.92},\"clouds\":{\"all\":96},\"dt\":1648630298,\"sys\":{\"type\":2,\"id\":47754,\"country\":\"RU\",\"sunrise\":1648609569,\"sunset\":1648656104},\"timezone\":10800,\"id\":524901,\"name\":\"Moscow\",\"cod\":200}";
        Map<String, String> result = jsonHelper.parseJson(json);
        Assert.assertNotNull(result);
        Assert.assertEquals("overcast clouds", result.get("description"));
    }


}
