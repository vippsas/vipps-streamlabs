package no.vipps.twitchecom;

import no.vipps.twitchecom.DTO.StreamLabDTO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class StreamlabService {

    private String STREAMLABS_API_BASE = "https://www.streamlabs.com/api/v1.0";

    @Value("{$STREAMLABS_CLIENT_ID}")
    private String STREAMLABS_CLIENT_ID;

    @Value("${STREAMLABS_CLIENT_SECRET}")
    private String STREAMLABS_CLIENT_SECRET;

    private OkHttpClient okHttpClient = new OkHttpClient();

    private int expiresIn = 0;
    private int createdAt = 0;
    private String accessToken;

    public String getAccessToken() throws IOException {
        if (expiresIn == 0 ||
                createdAt == 0 ||
                accessToken == null
                || createdAt + expiresIn < new Date().getTime()
        ) {
            FormBody formBody = new FormBody.Builder()
                    .add("grant_type", "refresh_token")
                    .add("client_id", STREAMLABS_CLIENT_ID)
                    .add("STREAM_LABS_CLIENT_SECRET", STREAMLABS_CLIENT_SECRET)
                    .add("redirect_uri", "")
                    .build();
            Request request = new Request.Builder()
                    .post(formBody)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
        }
        return "";
    }

    public void notifyStreamLab(String orderId) throws IOException {
        //Get the streamlab object created at initiate payment from the map
        StreamLabDTO streamLabDTO = VippsService.orderIdDonateMap.get(orderId);

        String amount = streamLabDTO.getAmount();
        String message = streamLabDTO.getMessage();
        String sender = streamLabDTO.getSender();

        //
        notifyStreamlabOfDonation(sender, message, amount);
        VippsService.orderIdDonateMap.remove(orderId);
    }

    public void notifyStreamlabOfDonation(String donor, String message, String amount ) throws IOException {
        FormBody formBody = new FormBody.Builder()
                .add("name", donor)
                .add("message", message)
                .add("identifier", donor)
                .add("amount", amount)
                .add("currency", "NOK")
                .add("access_token", "zfSd0bnBDNlCWz89EmLtjiccQiSDzi3SqKsmZJ5H")
                .build();

        Request request = new Request.Builder()
                .url(STREAMLABS_API_BASE+"/donations")
                .post(formBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }
}

