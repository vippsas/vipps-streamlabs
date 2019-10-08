package no.vipps.twitchecom.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import no.vipps.twitchecom.DTO.*;
import no.vipps.twitchecom.repository.OrderRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class VippsService {

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

    Logger logger = Logger.getLogger(VippsService.class.getName());

    @Autowired
    OrderRepository orderRepository;

    private OkHttpClient okHttpClient = new OkHttpClient();

    private GetAccessTokenDTO accessTokenDTO;


    public String getAccessToken(){
        if (accessTokenDTO == null || accessTokenDTO.hasExired()){
            FormBody body = new FormBody.Builder()
                    .build();
            Request request = new Request.Builder()
                    .addHeader("client_Id", CLIENT_ID)
                    .addHeader("client_Secret", CLIENT_SECRET)
                    .addHeader("Ocp-Apim-Subscription-Key", OCP_APIM_SUBSCRIPTION_KEY_ECOMMERCE)
                    .url(BASE_URL_ACCESS_TOKEN+"/accesstoken/get")
                    .post(body)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    if(responseBody != null){
                        String responseBodyString = responseBody.string();
                        logger.info("accesstoken response from Vipps is successful and body is not null");
                        logger.info("accesstoken response from Vipps=" + responseBodyString);
                        accessTokenDTO = new Gson().fromJson(responseBodyString, GetAccessTokenDTO.class);
                    }else{
                        logger.info("accesstoken response from Vipps is NOT successful and body is not null");
                        logger.info("accesstoken response from Vipps is NOT" + response.message());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accessTokenDTO.getAccess_token();
    }

    public String initiatePayment(String orderId, String amountInNOK, double amountInUSD, String transactionText) throws IOException {

        //Create the initiatePayment object
        MerchantInfoDTO merchantInfoDTO = new MerchantInfoDTO(MERCHANT_SERIAL_NUMBER, CALLBACK_PREFIX, FALLBACK_URL + "/" + orderId, isApp);
        TransactionDTO transactionDTO = new TransactionDTO(orderId, amountInNOK, transactionText + " " + amountInUSD + " USD");
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO("");

        InitiatePaymentRequestDTO initiatePaymentRequestDTO = new InitiatePaymentRequestDTO(
                merchantInfoDTO,
                customerInfoDTO,
                transactionDTO
        );

        String requestBody = new Gson().toJson(initiatePaymentRequestDTO);

        logger.info("requestBody in initatePayment is: ");
        logger.info(requestBody.toString());

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

        String responsebody = response.body().string();
        logger.info("Response from Vipps: " + response.message());

        return responsebody;
    }

    public GetPaymentStatusResponse getPaymentStatusResponse(String orderId) throws IOException {

        Gson gson = new Gson();

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer "+ getAccessToken())
                .addHeader("Ocp-Apim-Subscription-Key", OCP_APIM_SUBSCRIPTION_KEY_ECOMMERCE)
                .addHeader("Content-Type", "application/json")
                .url(BASE_URL_ACCESS_TOKEN+"/ecomm/v2/payments/"+orderId+"/status")
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        if(response.isSuccessful()){
            String responseString = response.body().string();
            GetPaymentStatusResponse getPaymentStatusResponse = gson.fromJson(responseString, GetPaymentStatusResponse.class);

            return getPaymentStatusResponse;
        }else{
            return null;
        }
    }
}
