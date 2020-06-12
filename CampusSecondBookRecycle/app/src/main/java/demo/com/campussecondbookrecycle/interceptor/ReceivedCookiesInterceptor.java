package demo.com.campussecondbookrecycle.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {

    private Context mContext;

    public ReceivedCookiesInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (originalResponse.header("Set-Cookie") == null)
            return originalResponse;
        if(!originalResponse.header("Set-Cookie").isEmpty()){
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")){
                cookies.add(header);
            }
            SharedPreferences.Editor config = mContext.getSharedPreferences("config",Context.MODE_PRIVATE).edit();
            config.putStringSet("cookie",cookies);
            config.commit();
        }
        return originalResponse;
    }
}
