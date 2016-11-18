package com.fiteval.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
<<<<<<< Updated upstream
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
=======
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
>>>>>>> Stashed changes

import com.fiteval.R;
import com.fiteval.ui.activity.MainActivity;

<<<<<<< Updated upstream
public class ShopFragment extends Fragment implements View.OnClickListener {
=======
public class ShopFragment extends Fragment {
>>>>>>> Stashed changes

    public static final String TAG = MainFragment.class.getName();

    private ImageView mIVWizardHat;
    private ImageView mIVSteelHelm;
    private ImageView mIVVikingHelm;

    private ImageView mIVWizardCloak;
    private ImageView mIVSteelPlate;
    private ImageView mIVVikingArmor;

    private ImageView mIVOakStaff;
    private ImageView mIVSword;
    private ImageView mIVBattleAxe;
    private ImageView mIVHat;
    private ImageView mIVArmor;
    private ImageView mIVWeapon;
    private Callback mCallback;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        mIVWizardHat = (ImageView) view.findViewById(R.id.iv_wizard_hat);
        mIVSteelHelm = (ImageView) view.findViewById(R.id.iv_steel_helm);
        mIVVikingHelm = (ImageView) view.findViewById(R.id.iv_viking_helmet);

        mIVWizardCloak = (ImageView) view.findViewById(R.id.iv_wizard_cloak);
        mIVSteelPlate = (ImageView) view.findViewById(R.id.iv_steelplate);
        mIVVikingArmor = (ImageView) view.findViewById(R.id.iv_viking_armor);

        mIVOakStaff = (ImageView) view.findViewById(R.id.iv_oakstaff);
        mIVSword = (ImageView) view.findViewById(R.id.iv_sword);
        mIVBattleAxe = (ImageView) view.findViewById(R.id.iv_battle_axe);

        mIVHat = (ImageView) view.findViewById(R.id.iv_helmet);
        mIVHat.setTag(-1);
        mIVArmor = (ImageView) view.findViewById(R.id.iv_armor);
        mIVArmor.setTag(-1);
        mIVWeapon = (ImageView) view.findViewById(R.id.iv_weapon);
        mIVWeapon.setTag(-1);

        final int[] stuff = {-1, -1, -1};

        mIVWizardHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIVHat.getTag().equals(0)) {
                    mIVHat.setImageResource(R.drawable.wizard_hat);
                    mIVHat.setVisibility(View.VISIBLE);
                    mIVHat.setTag(0);
                    stuff[0] = 0;
                } else {
                    stuff[0] = -1;
                    mIVHat.setTag(-1);
                    mIVHat.setVisibility(View.INVISIBLE);
                }
            }
        });

        mIVSteelHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIVHat.getTag().equals(1)) {
                    mIVHat.setImageResource(R.drawable.steel_helm);
                    mIVHat.setVisibility(View.VISIBLE);
                    mIVHat.setTag(1);
                    stuff[0] = 1;
                } else {
                    stuff[0] = -1;
                    mIVHat.setTag(-1);
                    mIVHat.setVisibility(View.INVISIBLE);
                }
            }
        });

        mIVVikingHelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIVHat.getTag().equals(2)) {
                    mIVHat.setImageResource(R.drawable.viking_helmet);
                    mIVHat.setVisibility(View.VISIBLE);
                    mIVHat.setTag(2);
                    stuff[0] = 2;
                } else {
                    stuff[0] = -1;
                    mIVHat.setTag(-1);
                    mIVHat.setVisibility(View.INVISIBLE);
                }
            }
        });

        mIVWizardCloak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIVArmor.getTag().equals(3)) {
                    mIVArmor.setImageResource(R.drawable.wizard_cloak);
                    mIVArmor.setVisibility(View.VISIBLE);
                    mIVArmor.setTag(3);
                    stuff[1] = 3;
                } else {
                    stuff[1] = -1;
                    mIVArmor.setTag(-1);
                    mIVArmor.setVisibility(View.INVISIBLE);
                }
            }
        });

        mIVSteelPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIVArmor.getTag().equals(4)) {
                    mIVArmor.setImageResource(R.drawable.steelplate_body);
                    mIVArmor.setVisibility(View.VISIBLE);
                    mIVArmor.setTag(4);
                    stuff[1] = 4;
                } else {
                    stuff[1] = -1;
                    mIVArmor.setTag(-1);
                    mIVArmor.setVisibility(View.INVISIBLE);
                }
            }
        });

        mIVVikingArmor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIVArmor.getTag().equals(5)) {
                    mIVArmor.setTag(-1);
                    mIVArmor.setVisibility(View.INVISIBLE);
                    stuff[1] = 5;
                } else {
                    stuff[1] = -1;
                    mIVArmor.setImageResource(R.drawable.viking_armor);
                    mIVArmor.setVisibility(View.VISIBLE);
                    mIVArmor.setTag(5);
                }
            }
        });

        mIVOakStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIVWeapon.getTag().equals(6)) {
                    mIVWeapon.setTag(-1);
                    mIVWeapon.setVisibility(View.INVISIBLE);
                    stuff[2] = 6;
                } else {
                    stuff[2] = -1;
                    mIVWeapon.setImageResource(R.drawable.oakstaff);
                    mIVWeapon.setVisibility(View.VISIBLE);
                    mIVWeapon.setTag(6);
                }
            }
        });

        mIVSword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIVWeapon.getTag().equals(7)) {
                    mIVWeapon.setTag(-1);
                    mIVWeapon.setVisibility(View.INVISIBLE);
                    stuff[2] = 7;
                } else {
                    stuff[2] = -1;
                    mIVWeapon.setImageResource(R.drawable.sword);
                    mIVWeapon.setVisibility(View.VISIBLE);
                    mIVWeapon.setTag(7);
                }
            }
        });

        mIVBattleAxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIVWeapon.getTag().equals(8)) {
                    mIVWeapon.setTag(-1);
                    mIVWeapon.setVisibility(View.INVISIBLE);
                    stuff[2] = 8;
                } else {
                    stuff[2] = -1;
                    mIVWeapon.setImageResource(R.drawable.battle_axe);
                    mIVWeapon.setVisibility(View.VISIBLE);
                    mIVWeapon.setTag(8);
                }
            }
        });

        Button confirm = (Button) view.findViewById(R.id.butConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i : stuff) {
                    if (i >= 0) {
                        if (MainActivity.knight.getmInv().get(i).isPurchased()) {
                            MainActivity.knight.equipItem(MainActivity.knight.getmInv().get(i));
                        } else if (MainActivity.knight.getmGold() > MainActivity.knight.getmInv().get(i).getCost()) {
                            MainActivity.knight.setmGold(MainActivity.knight.getmGold() - MainActivity.knight.getmInv().get(i).getCost());
                            MainActivity.knight.equipItem(MainActivity.knight.getmInv().get(i));
                        } else {
                            Toast toast = new Toast(getContext());
                            toast.setText("You need more money to purchase this!");
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        Log.d("Shop", MainActivity.knight.getmInv().get(i).getName());
                    }
                }
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }

    @Override
    public void onClick(View view) {

    }

    public interface Callback {
        void updateToolbarTitle(String title);
    }
}
