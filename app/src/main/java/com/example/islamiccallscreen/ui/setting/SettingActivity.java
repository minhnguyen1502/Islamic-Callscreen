package com.example.islamiccallscreen.ui.setting;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.ziplock.lockscreen.zipperlock.R;
import com.ziplock.lockscreen.zipperlock.base.BaseActivity;
import com.ziplock.lockscreen.zipperlock.databinding.ActivitySettingBinding;
import com.ziplock.lockscreen.zipperlock.dialog.rate.IClickDialogRate;
import com.ziplock.lockscreen.zipperlock.dialog.rate.RatingDialog;
import com.ziplock.lockscreen.zipperlock.ui.language.LanguageActivity;
import com.ziplock.lockscreen.zipperlock.util.EventTracking;
import com.ziplock.lockscreen.zipperlock.util.SharePrefUtils;
import com.ziplock.lockscreen.zipperlock.util.SystemUtil;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> {
    @Override
    public ActivitySettingBinding getBinding() {
        return ActivitySettingBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        EventTracking.logEvent(this, "setting_view");

        String currentLanguageCode = SystemUtil.getPreLanguage(this);
        String currentLanguage = getLanguageNameByCode(currentLanguageCode);
        binding.tvLanguage.setText(currentLanguage);
    }

    @Override
    public void bindView() {
        binding.btnAbout.setOnClickListener(v -> {
            EventTracking.logEvent(this, "setting_about_click");
            startActivity(new Intent(this, AboutActivity.class));
        });

        if (SharePrefUtils.isRated(this)) binding.btnRate.setVisibility(View.GONE);

        binding.btnLanguage.setOnClickListener(v -> {
            EventTracking.logEvent(this, "setting_language_click");
            startActivity(new Intent(this, LanguageActivity.class));
        });

        binding.btnRate.setOnClickListener(v -> {
            EventTracking.logEvent(this, "setting_rate_click");
            rate();
        });

        binding.btnShare.setOnClickListener(v -> {
            EventTracking.logEvent(this, "setting_share_click");
            share();
        });

        binding.ivBack.setOnClickListener(v -> {
            EventTracking.logEvent(this, "personalized_success_home_click");
            onBack();
        });
    }

    @Override
    public void onBack() {
        finish();
    }

    private String getLanguageNameByCode(String code) {
        switch (code) {
            case "en":
                return "English";
            case "zh":
                return "China";
            case "fr":
                return "French";
            case "de":
                return "German";
            case "hi":
                return "Hindi";
            case "in":
                return "Indonesia";
            case "pt":
                return "Portuguese";
            case "es":
                return "Spanish";
            default:
                return "Unknown";
        }
    }

    private void share() {
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        intentShare.putExtra(Intent.EXTRA_TEXT, "Download application :" + "https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(intentShare, "Share with"));
        binding.btnShare.postDelayed(() -> binding.btnShare.setEnabled(true), 500);

    }

    ReviewInfo reviewInfo;
    ReviewManager manager;

    private void rate() {
        EventTracking.logEvent(SettingActivity.this, "rate_show");

        RatingDialog ratingDialog = new RatingDialog(this, true);
        ratingDialog.init(new IClickDialogRate() {
            @Override
            public void send() {
                binding.btnRate.setVisibility(View.GONE);
                ratingDialog.dismiss();
                String uriText = "mailto:" + SharePrefUtils.email + "?subject=" + "Review for " + SharePrefUtils.subject + "&body=" + SharePrefUtils.subject + "\nRate : " + ratingDialog.getRating() + "\nContent: ";
                Uri uri = Uri.parse(uriText);
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setData(uri);
                try {
                    startActivity(Intent.createChooser(sendIntent, getString(R.string.Send_Email)));
                    SharePrefUtils.forceRated(SettingActivity.this);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SettingActivity.this, getString(R.string.There_is_no), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void rate() {
                manager = ReviewManagerFactory.create(SettingActivity.this);
                Task<ReviewInfo> request = manager.requestReviewFlow();
                request.addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        reviewInfo = task.getResult();
                        Task<Void> flow = manager.launchReviewFlow(SettingActivity.this, reviewInfo);
                        flow.addOnSuccessListener(result -> {
                            binding.btnRate.setVisibility(View.GONE);
                            SharePrefUtils.forceRated(SettingActivity.this);
                            ratingDialog.dismiss();
                        });
                    } else {

                        ratingDialog.dismiss();
                    }
                });
            }

            @Override
            public void later() {
                ratingDialog.dismiss();
            }

        });
        try {
            ratingDialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}
