package com.example.demo.src.restaurants;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.restaurants.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class RestaurantsService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestaurantsDao restaurantsDao;
    private final RestaurantsProvider restaurantsProvider;
    private final JwtService jwtService;

    @Autowired
    public RestaurantsService(RestaurantsDao restaurantsDao, RestaurantsProvider restaurantsProvider, JwtService jwtService) {
        this.restaurantsDao = restaurantsDao;
        this.restaurantsProvider = restaurantsProvider;
        this.jwtService = jwtService;
    }

    public void patchRestaurantLike(PatchRestaurantReq patchRestaurantReq, int restaurantId,int userId) throws BaseException {
        try{

            int result = restaurantsDao.patchRestaurantLike(patchRestaurantReq, restaurantId,userId);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_ISLIKE);
            }
            System.out.println("2");
        } catch(Exception exception){
            System.out.println("3");
            System.out.println(exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //POST
    public PostRestaurantRes createRestaurant(PostRestaurantReq postRestaurantReq) throws BaseException {

        try{
            System.out.println("2");
            int id = restaurantsDao.createRestaurant(postRestaurantReq);
            return new PostRestaurantRes(id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //POST
    public PostRestaurantVisitedRes createRestaurantVisited(int restaurantId, int userId, PostRestaurantVisitedReq postRestaurantVisitedReq) throws BaseException {

        try{
            System.out.println("2");
            int id = restaurantsDao.createRestaurantVisited(restaurantId, userId, postRestaurantVisitedReq);
            return new PostRestaurantVisitedRes(id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
