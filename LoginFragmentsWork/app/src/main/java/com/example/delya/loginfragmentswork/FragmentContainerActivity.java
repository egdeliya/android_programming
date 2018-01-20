package com.example.delya.loginfragmentswork;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragmentContainerActivity extends AppCompatActivity
        implements SwitcherFragment.OnButtonClickListener,
        ActionBaseFragment.OnActionIntentListener {

    private int mActionFragmentId;
    private int mSwitcherFragmentId;
    private int mPhoneFieldFragmentId;

    private FragmentManager mFragmentManager;

    private static String TAG = "Fragment container activity";

    // реализация требуемого фрагментом SwitcherFragment интерфейса
    public void switchToCallFragment(){
        CallFragment newCallFrag  = new CallFragment();
        switchToCertainFragment(newCallFrag, true);
    }

    // реализация требуемого фрагментом SwitcherFragment интерфейса
    public void switchToSendSmsFragment(){
        SendSmsFragment newSendSmsFrag  = new SendSmsFragment();
        switchToCertainFragment(newSendSmsFrag, false);
    }

    public void switchToCertainFragment(ActionBaseFragment actionFragment, boolean isCallActive) {

        SwitcherFragment switcherFrg = (SwitcherFragment) mFragmentManager
                .findFragmentById(mSwitcherFragmentId);

        PhoneFieldFragment phoneFldFrg = (PhoneFieldFragment)
                mFragmentManager.findFragmentById(mPhoneFieldFragmentId);

        FragmentTransaction trans = mFragmentManager.beginTransaction();

        if (phoneFldFrg != null) {

            switcherFrg.changeActiveButton(isCallActive);

            trans.replace(R.id.action_fragment_container, actionFragment, "Send sms fragment");
            trans.commit();

        } else {
            PhoneFieldFragment newPhoneFldFrg = new PhoneFieldFragment();

            trans.replace(mActionFragmentId, actionFragment, "Send sms fragment");
            trans.replace(mPhoneFieldFragmentId, newPhoneFldFrg,
                    "Phone field fragment");
            trans.addToBackStack(null);
            trans.hide(switcherFrg);
            trans.commit();
        }
    }


    // реализация требуемого фрагментами CallFragment и SendSmsFragment интерфейса
    public String getPhoneNumber() {
        PhoneFieldFragment phoneFldFrg = (PhoneFieldFragment)
                mFragmentManager.findFragmentById(mPhoneFieldFragmentId);

        return phoneFldFrg.getPhoneNumber();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // чтобы спрятать стандартный header
        getSupportActionBar().hide();

        setContentView(R.layout.activity_fragment_container);

        // сохраняем id layout-ов для фрагментов
        mActionFragmentId = R.id.action_fragment_container;
        mSwitcherFragmentId = R.id.switcher_fragment_container;
        mPhoneFieldFragmentId = R.id.phone_field_fragment_container;

        mFragmentManager = getSupportFragmentManager();

        SwitcherFragment switcherFrg = (SwitcherFragment)
                mFragmentManager.findFragmentById(mSwitcherFragmentId);

        FragmentTransaction trans = mFragmentManager.beginTransaction();

        // проверка, что запускаем в первый раз
        if (switcherFrg == null) {
            // если запускаем первый раз на планшете,
            // то фрагмент с кнопкой и полем для ввода номера телефона
            // должны быть сразу же на экране
            // по умолчанию виден фрагмент CallFragment
            if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {

                switcherFrg = new SwitcherFragment();
                PhoneFieldFragment phoneFieldFragment = new PhoneFieldFragment();

                trans.add(mSwitcherFragmentId, switcherFrg, "Switcher Fragment");
                trans.add(mPhoneFieldFragmentId, phoneFieldFragment, "Phone field fragment!");

            } else {
                // запускаем на меленьком эране в первый раз,
                // на экране должен быть фрагмент с кнопками
                switcherFrg = new SwitcherFragment();
                trans.add(mSwitcherFragmentId, switcherFrg, "Switcher Fragment");

            }
            trans.commit();
        }
    }
}
