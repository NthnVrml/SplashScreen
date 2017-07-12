package portail.internal.android.sncf.com.pnvesplashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by VERMELLE Nathan.
 * Date : 2016
 */

public class PnveSplashScreen {

    Activity activity;
    LayoutInflater layoutInflater;

    ImageView logo_iv;

    LottieAnimationView lottieAnimationView;

    TextView header_tv;
    TextView footer_tv;
    TextView before_logo_tv;
    TextView after_logo_tv;

    String header_text = null;
    String footer_text = null;
    String before_logo_text = null;
    String after_logo_text = null;
    RelativeLayout splash_wrapper_rl;
    Bundle bundle = null;

    private View view;

    //indicator
    private int splashBackgroundColor = 0;
    private int splashBackgroundResource = 0;
    private int mLogo = 0;

    private Class<?> targetActivity = null;

    private int SPLASH_TIME_OUT = 2000; //The time before launch target Activity - by default 2 seconds

    /**
     * Constructor
     * @param activity
     */
    public PnveSplashScreen(Activity activity) {
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
        this.view = layoutInflater.inflate(R.layout.layout_splashscreen, null);
        this.splash_wrapper_rl = (RelativeLayout) view.findViewById(R.id.splash_wrapper_rl);

    }

    /**
     * set Full Screen mode
     * @return
     */
    public PnveSplashScreen withFullScreen() {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return this;
    }

    /**
     * Set Target Activity
     * @param tAct
     * @return
     */
    public PnveSplashScreen withTargetActivity(Class<?> tAct) {
        this.targetActivity = tAct;
        return this;
    }

    /**
     * Set Time out of splash screen
     * @param timout
     * @return
     */
    public PnveSplashScreen withSplashTimeOut(int timout) {
        this.SPLASH_TIME_OUT = timout;
        return this;
    }

    /**
     * Extra
     * @param bundle
     * @return
     */
    public PnveSplashScreen withBundleExtras(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public PnveSplashScreen withBackgroundColor(int color) {
        this.splashBackgroundColor = color;
        splash_wrapper_rl.setBackgroundColor(splashBackgroundColor);
        return this;
    }

    public PnveSplashScreen withBackgroundResource(int resource) {
        this.splashBackgroundResource = resource;
        splash_wrapper_rl.setBackgroundResource(splashBackgroundResource);
        return this;
    }

    public PnveSplashScreen withLogo(int logo) {
        this.mLogo = logo;
        logo_iv = (ImageView) view.findViewById(R.id.logo);
        logo_iv.setVisibility(View.VISIBLE);
        logo_iv.setImageResource(mLogo);
        return this;
    }

    public PnveSplashScreen withLottieAnimation(String jsonFile){
        this.lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.animation_view);
        this.lottieAnimationView.setVisibility(View.VISIBLE);

        this.lottieAnimationView.setAnimation(jsonFile);
        this.lottieAnimationView.loop(true);
        this.lottieAnimationView.playAnimation();

        return this;
    }

    public PnveSplashScreen withHeaderText(String text) {
        this.header_text = text;
        header_tv = (TextView) view.findViewById(R.id.header_tv);
        header_tv.setText(text);
        return this;
    }

    public PnveSplashScreen withFooterText(String text) {
        this.footer_text = text;
        footer_tv = (TextView) view.findViewById(R.id.footer_tv);
        footer_tv.setText(text);
        return this;
    }

    public PnveSplashScreen withBeforeLogoText(String text) {
        this.before_logo_text = text;
        before_logo_tv = (TextView) view.findViewById(R.id.before_logo_tv);
        before_logo_tv.setText(text);
        return this;
    }

    public PnveSplashScreen withAfterLogoText(String text) {
        this.after_logo_text = text;
        after_logo_tv = (TextView) view.findViewById(R.id.after_logo_tv);
        after_logo_tv.setText(text);
        return this;
    }

    public ImageView getLogo() {
        return logo_iv;
    }

    public TextView getBeforeLogoTextView() {
        return before_logo_tv;
    }

    public TextView getAfterLogoTextView() {
        return after_logo_tv;
    }

    public TextView getHeaderTextView() {
        return header_tv;
    }

    public TextView getFooterTextView() {
        return footer_tv;
    }

    /**
     * create
     * @return
     */
    public View create() {
        setUpHandler();

        return view;
    }

    /**
     * Setup
     */
    private void setUpHandler() {
        if (targetActivity != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(activity, targetActivity);
                    if (bundle != null) {
                        i.putExtras(bundle);
                    }
                    activity.startActivity(i);
                    // close splash
                    activity.finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

}
