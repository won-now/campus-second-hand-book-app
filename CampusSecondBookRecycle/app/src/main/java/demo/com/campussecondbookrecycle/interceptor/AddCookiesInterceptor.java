package demo.com.campussecondbookrecycle.interceptor;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    private Context mContext;

    public AddCookiesInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet)mContext.getSharedPreferences
                ("config",Context.MODE_PRIVATE).getStringSet("cookie",null);
        if(preferences != null){
            for (String cookie : preferences){
                builder.addHeader("Cookie",cookie);
                Log.v("okhttp","Adding Header: " + cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
