package stspz.vntu.com.pos.rest.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import stspz.vntu.com.pos.rest.requests.AuthorizeRequest;
import stspz.vntu.com.pos.rest.requests.BaseRequest;
import stspz.vntu.com.pos.rest.requests.CreateOrderRequest;
import stspz.vntu.com.pos.rest.requests.CreateProductRequest;
import stspz.vntu.com.pos.rest.responses.AuthorizeResponse;
import stspz.vntu.com.pos.rest.responses.BaseResponse;
import stspz.vntu.com.pos.rest.responses.GetProductResponse;
import stspz.vntu.com.pos.rest.responses.GetUserInfoResponse;

/**
 * Created by Alexander on 29.02.2016.
 */

public interface ApiService {

    @POST("authorization/sign-in")
    Call<AuthorizeResponse> authorize(@Body AuthorizeRequest request);

    @POST("authorization/sign-out")
    Call<BaseResponse> logOut(@Body BaseRequest request);

    @POST("categories/get?limit=15&offset=0")
    Call<BaseResponse> getCategories();

    @POST("groups/1/products")
    Call<BaseResponse> getProductsByGroup();

    @POST("groups/1/add/2")
    Call<BaseResponse> addProductInGroup();

    @POST("groups/1/remove/2")
    Call<BaseResponse> removeProductFromGroup();

    @POST("groups/1/get")
    Call<BaseResponse> getGroupById();

    @POST("groups/get?limit=15&offset=0")
    Call<BaseResponse> getGroups();

    @POST("groups/1/remove")
    Call<BaseResponse> removeGroup();

    @POST("groups/create")
    Call<BaseResponse> createGroup();

    @POST("groups/update")
    Call<BaseResponse> updateGroup();

    @POST("orders/get?limit=15&offset=0")
    Call<BaseResponse> getOrder();

    @POST("orders/get-admin?limit=15&offset=0")
    Call<BaseResponse> getAllOrder();

    @POST("orders/1/cancel")
    Call<BaseResponse> cancelOrder();

    @POST("orders/1/get")
    Call<BaseResponse> getOrderById();

    @POST("orders/create")
    Call<BaseResponse> createOrder(@Body CreateOrderRequest request);

    @POST("orders/update")
    Call<BaseResponse> updateOrder();

    @POST("products/1/get")
    Call<BaseResponse> getProductById();

    @POST("products/{productCode}/get-by-code")
    Call<GetProductResponse> getProductByCode(@Path("productCode") String productCode, @Body BaseRequest request);

    @POST("products/get?limit=15&offset=0&categoryId=1")
    Call<BaseResponse> getProducts();

    @POST("products/1/increase/3")
    Call<BaseResponse> increaseProduct();

    @POST("products/1/expire")
    Call<BaseResponse> expireProductById();

    @POST("products/1/remove")
    Call<BaseResponse> removeProductById();

    @POST("products/update-amount")
    Call<BaseResponse> updateAmount();

    @POST("products/update")
    Call<BaseResponse> updateProduct();

    @POST("products/create")
    Call<BaseResponse> createProduct(@Body CreateProductRequest request);

    @POST("statistics/1/2016-08-03/2016-D09-03/day/get")
    Call<BaseResponse> getStatisticByCashierId();

    @POST("users/1/remove")
    Call<BaseResponse> removeUserById();

    @POST("users/get?limit=10&offset=0&type=0")
    Call<BaseResponse> getUsers();

    @POST("users/info")
    Call<GetUserInfoResponse> getUserInfo(@Body BaseRequest request);

    @POST("users/create")
    Call<BaseResponse> createUser();

}
