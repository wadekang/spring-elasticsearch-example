package com.example.elasticsearch.svc;

import com.example.elasticsearch.dto.Bucket;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final RestClient restClient;

    public void searchAssetsList() throws IOException {

        String endpoint = "dsp_*_*_20220823";
        endpoint = URLEncoder.encode(endpoint, "UTF-8");
        Request request = new Request("POST", "/" + endpoint + "/_search?");

        String data = "{\"size\":0,\"aggs\":{\"byAssetsId\":{\"terms\":{\"field\":\"assetsId\",\"size\":10000}}}}";

        request.addParameter("pretty", "false");
        request.setEntity(new NStringEntity(data, ContentType.APPLICATION_JSON));
        Response response = restClient.performRequest(request);

        JSONObject reJsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
        JSONArray buckets = reJsonObject.getJSONObject("aggregations").getJSONObject("byAssetsId").getJSONArray("buckets");

        log.info("{}", reJsonObject);
        log.info("{}", buckets);

        ObjectMapper objectMapper = new ObjectMapper();


        List<Bucket> bucketList = new ArrayList<>();

        buckets.forEach(item -> {
            log.info("{}", item.toString());

            JSONObject jsonObject = (JSONObject) item;
            Bucket bucket = new Bucket();

            bucket.setAssetNo(jsonObject.getInt("key"));
            bucket.setDocCount(jsonObject.getInt("doc_count"));

            bucketList.add(bucket);

            log.info("{}", bucket);
        });
    }
}
