package com.auth.TALESS.Main.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.auth.TALESS.Main.Activities.LauncherActivity;
import com.auth.TALESS.Main.Activities.LoginActivity;
import com.auth.TALESS.Main.Activities.generalInfoActivity;
import com.auth.TALESS.R;
import com.auth.TALESS.Util.SessionManager;
import com.auth.TALESS.Util.Utilities;

/**
 * Implements the Settings Fragment View
 * Uses PreferenceFragment
 * Layout at res/xml/preferences.xml
 * Some functionality inside the .xml file
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SwitchPreferenceCompat coloredPreference = findPreference("coloredTitles");
        if (coloredPreference != null) {
            coloredPreference.setOnPreferenceChangeListener((preference1, newValue) -> {
                LauncherActivity.pref_paintTitles = !LauncherActivity.pref_paintTitles;
                return true;
            });
        }

        ListPreference themePreference = findPreference("pref_theme");
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                Utilities.setDeviceThemeMode(requireContext(),(String)newValue);
                return true;
            });
        }

        //Set the various click listeners with the correct extra for the Intent
        Preference faq = findPreference("FAQ");
        faq.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(requireActivity(), generalInfoActivity.class);
            intent.putExtra("type", "FAQ");
            startActivity(intent);
            return true;
        });

        Preference app = findPreference("theApp");
        app.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(requireActivity(), generalInfoActivity.class);
            intent.putExtra("type", "APP");
            startActivity(intent);
            return true;
        });

        Preference team = findPreference("theTeam");
        team.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(requireActivity(), generalInfoActivity.class);
            intent.putExtra("type", "TEAM");
            startActivity(intent);
            return true;
        });

        Preference tac = findPreference("TAC");
        tac.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(requireActivity(), generalInfoActivity.class);
            intent.putExtra("type", "TAC");
            startActivity(intent);
            return true;
        });

        Preference logoutPreference = findPreference("logout");
        if (logoutPreference != null) {
            logoutPreference.setOnPreferenceClickListener(preference -> {
                // 清除 Session
                SessionManager.clearSession(requireContext());

                // 跳转到登录页并清空任务栈
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();

                return true;
            });
        }
    }
}