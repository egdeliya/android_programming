package com.example.delya.loginfragmentswork;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


// Базовый класс для фрагментов "Позвонить" и "Отправить смс"
// так как эти фрагменты требуют реализацию одного и того же
// интерфейса
public abstract class ActionBaseFragment extends Fragment {

    public interface OnActionIntentListener {
        String getPhoneNumber();
    }
}
