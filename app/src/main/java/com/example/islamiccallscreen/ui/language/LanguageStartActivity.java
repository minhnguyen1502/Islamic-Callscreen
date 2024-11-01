package com.example.islamiccallscreen.ui.language;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.islamiccallscreen.R;
import com.example.islamiccallscreen.base.BaseActivity;
import com.example.islamiccallscreen.databinding.ActivityLanguageStartBinding;
import com.example.islamiccallscreen.ui.intro.IntroActivity;
import com.example.islamiccallscreen.ui.language.adapter.LanguageStartAdapter;
import com.example.islamiccallscreen.ui.language.model.LanguageModel;
import com.example.islamiccallscreen.util.SharePrefUtils;
import com.example.islamiccallscreen.util.SystemUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LanguageStartActivity extends BaseActivity<ActivityLanguageStartBinding> {

    List<LanguageModel> listLanguage;
    String codeLang = "";

    @Override
    public ActivityLanguageStartBinding getBinding() {
        return ActivityLanguageStartBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
//        EventTracking.logEvent(this, "language_fo_open");

        initData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LanguageStartAdapter languageStartAdapter = new LanguageStartAdapter(listLanguage, code -> codeLang = code, this);
        binding.rcvLangStart.setLayoutManager(linearLayoutManager);
        binding.rcvLangStart.setAdapter(languageStartAdapter);

//        languageStartAdapter.setCheck(SystemUtil.getPreLanguage(getBaseContext()));

        binding.ivCheck.setOnClickListener(view -> {
//            EventTracking.logEvent(this, "language_fo_save_click");

            if (codeLang.isEmpty()) {
                Toast.makeText(LanguageStartActivity.this, "please_choose_your_language", Toast.LENGTH_SHORT).show();
            } else {
                SystemUtil.saveLocale(getBaseContext(), codeLang);
//                Utils.setLanguageSelected(true);
                startNextActivity(IntroActivity.class, null);
                finishAffinity();
            }
        });
    }

    @Override
    public void bindView() {

    }


    @Override
    public void onBack() {
        finishAffinity();
    }


    private void initData() {
        listLanguage = new ArrayList<>();
        listLanguage.add(new LanguageModel("English", "en"));
        listLanguage.add(new LanguageModel("China", "zh"));
        listLanguage.add(new LanguageModel("French", "fr"));
        listLanguage.add(new LanguageModel("German", "de"));
        listLanguage.add(new LanguageModel("Hindi", "hi"));
        listLanguage.add(new LanguageModel("Indonesia", "in"));
        listLanguage.add(new LanguageModel("Portuguese", "pt"));
        listLanguage.add(new LanguageModel("Spanish", "es"));

        String deviceLanguageCode = Locale.getDefault().getLanguage();
        String previousLanguageCode = SystemUtil.getPreLanguage(getBaseContext());
        if (SharePrefUtils.getCountOpenApp(this) > 1 && !previousLanguageCode.isEmpty()) {
            Collections.sort(listLanguage, (lang1, lang2) -> lang1.getName().compareToIgnoreCase(lang2.getName()));
            for (int i = 0; i < listLanguage.size(); i++) {
                if (listLanguage.get(i).getCode().equals(previousLanguageCode)) {
                    LanguageModel selectedLanguage = listLanguage.remove(i);
                    listLanguage.add(0, selectedLanguage);
                    break;
                }
            }
        } else {
            boolean isDeviceLangInList = false;
            for (int i = 0; i < listLanguage.size(); i++) {
                if (listLanguage.get(i).getCode().equals(deviceLanguageCode)) {
                    LanguageModel deviceLanguage = listLanguage.remove(i);
                    listLanguage.add(0, deviceLanguage);
                    isDeviceLangInList = true;
                    break;
                }
            }
            if (!isDeviceLangInList) {
                for (int i = 0; i < listLanguage.size(); i++) {
                    if (listLanguage.get(i).getCode().equals("en")) {
                        LanguageModel englishLanguage = listLanguage.remove(i);
                        listLanguage.add(0, englishLanguage);
                        break;
                    }
                }
            }
        }

    }

}
