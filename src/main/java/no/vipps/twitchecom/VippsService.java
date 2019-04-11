package no.vipps.twitchecom;

import com.google.gson.Gson;
import no.vipps.twitchecom.DTO.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@Service
public class VippsService {

    @Value("${VIPPS_OCP_APIM_SUBSCRIPTION_KEY_ACCESSTOKEN}")
    private String OCP_APIM_SUBSCRIPTION_KEY_ACCESSTOKEN;

    @Value("${VIPPS_CLIENT_ID}")
    private String CLIENT_ID;

    @Value("${VIPPS_CLIENT_SECRET}")
    private String CLIENT_SECRET;

    @Value("${VIPPS_OCP_APIM_SUBSCRIPTION_KEY_ECOMMERCE}")
    private String OCP_APIM_SUBSCRIPTION_KEY_ECOMMERCE;

    @Value("${VIPPS_CALLBACK_PREFIX}")
    private String CALLBACK_PREFIX;

    @Value("${VIPPS_FALLBACK_URL}")
    private String FALLBACK_URL;

    @Value("${VIPPS_MERCHANT_SERIAL_NUMBER}")
    private String MERCHANT_SERIAL_NUMBER;

    private Boolean isApp = false;

    //if test environment use https://apitest.vipps.no
    private String BASE_URL_ACCESS_TOKEN = "https://api.vipps.no";

    private Random random = new Random();
    private int order = 1000;

    public static HashMap<String, StreamLabDTO> orderIdDonateMap = new HashMap<>();

    private OkHttpClient okHttpClient = new OkHttpClient();

    private GetAccessTokenDTO accessTokenDTO;
    private StreamlabService streamlabService = new StreamlabService();
    

    public String getAccessToken(){
        if (accessTokenDTO == null || accessTokenDTO.hasExired()){
            FormBody body = new FormBody.Builder()
                    .build();
            Request request = new Request.Builder()
                    .addHeader("client_Id", CLIENT_ID)
                    .addHeader("client_Secret", CLIENT_SECRET)
                    .addHeader("Ocp-Apim-Subscription-Key", OCP_APIM_SUBSCRIPTION_KEY_ACCESSTOKEN)
                    .url(BASE_URL_ACCESS_TOKEN+"/accesstoken/get")
                    .post(body)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                accessTokenDTO = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), GetAccessTokenDTO.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accessTokenDTO.getAccess_token();
    }

    public String initiatePayment(int amount, String transactionText, String senderName) throws IOException {

        //prefix of orderId is randomized just to make sure new instances doesn't make orders of same id.
        String orderId = random.nextInt(100) + "" + order++;


        //Create object we want to send to Streamlabs if payment is successful. Add the object to a map with orderId as key.
        //When callback hits we get orderId from callback, get the streamlabobject back.
        StreamLabDTO streamLabDTO = new StreamLabDTO(senderName, String.valueOf(((double) amount)), transactionText);
        orderIdDonateMap.put(orderId, streamLabDTO);

        //Create the initiatePayment object
        MerchantInfoDTO merchantInfoDTO = new MerchantInfoDTO(MERCHANT_SERIAL_NUMBER, CALLBACK_PREFIX, FALLBACK_URL, isApp);
        TransactionDTO transactionDTO = new TransactionDTO(orderId, String.valueOf(amount*100), transactionText);
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO("");

        InitiatePaymentRequestDTO initiatePaymentRequestDTO = new InitiatePaymentRequestDTO(
                merchantInfoDTO,
                customerInfoDTO,
                transactionDTO
        );

        String requestBody = new Gson().toJson(initiatePaymentRequestDTO);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestBody);

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer "+ getAccessToken())
                .addHeader("Ocp-Apim-Subscription-Key", OCP_APIM_SUBSCRIPTION_KEY_ECOMMERCE)
                .addHeader("Content-Type", "application/json")
                .url(BASE_URL_ACCESS_TOKEN+"/ecomm/v2/payments")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        if(response.body() == null){
            return "";
        }

        return response.body().string();
    }
}
