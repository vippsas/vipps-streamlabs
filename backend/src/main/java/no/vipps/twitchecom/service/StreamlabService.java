package no.vipps.twitchecom.service;

import no.vipps.twitchecom.entity.Donation;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StreamlabService {

    private String STREAMLABS_API_BASE = "https://www.streamlabs.com/api/v1.0";

    @Value("${STREAMLABS_ACCESS_TOKEN}")
    private String STREAMLABS_ACCESS_TOKEN;

    private OkHttpClient okHttpClient = new OkHttpClient();

    public boolean notifyStreamlabOfDonation(Donation donation) throws IOException {
        FormBody formBody = new FormBody.Builder()
                .add("name", donation.getSender())
                .add("message", donation.getMessage())
                .add("identifier", donation.getSender())
                .add("amount", String.valueOf(donation.getAmountInUSD()))
                .add("currency", "USD")
                .add("access_token", STREAMLABS_ACCESS_TOKEN)
                .build();


        Request request = new Request.Builder()
                .url(STREAMLABS_API_BASE+"/donations")
                .post(formBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        return response.isSuccessful();
    }
}

