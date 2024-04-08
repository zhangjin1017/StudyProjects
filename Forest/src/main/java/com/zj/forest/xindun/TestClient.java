package com.zj.forest.xindun;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;

/**
 * @Author zhangjin
 * @Date 2024/1/31 16:49
 * @description:
 */
public interface TestClient {
    //加签 加密(非国密)
    @Post(url="http://172.16.5.44:30644/api-auth/oauth/token")
    String getTokenWithSignAndEncode(@Query("timestamp") String timestamp,
                                     @Query("sign") String sign,
                                     @Query("clientId") String clientId
    );
}
