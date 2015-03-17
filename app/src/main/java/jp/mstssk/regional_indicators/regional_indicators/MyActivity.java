package jp.mstssk.regional_indicators.regional_indicators;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.LayoutParams.WRAP_CONTENT;


public class MyActivity extends Activity {

    public static final char REGIONAL_LOW_A = '\uDDE6';
    public static final char REGIONAL_HIGH = '\uD83C';

    @InjectView(R.id.count_button)
    Button countButton;

    @InjectView(R.id.linear_layout)
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);

//        TextView textView = (TextView) findViewById(R.id.text);
//        StringBuilder sb = new StringBuilder();
////        for (char i = 'a'; i <= 'z'; i++) {
////            char ichar = (char) (regionalLowA + (i - 'a'));
////            for (char j = 'a'; j <= 'z'; j++) {
////                char jchar = (char) (regionalLowA + (j - 'a'));
////                sb.append(i).append(j).append(" : ");
////                sb.append(regionalHigh).append(ichar);
////                sb.append(regionalHigh).append(jchar);
////                sb.append('\n');
////            }
////        }
//        int count = 0;
//        String[] split = countryCodes.split(",");
//        Toast.makeText(this, split.length + " countries.", Toast.LENGTH_LONG).show();
//        for (String codes : split) {
////            sb.append(codes).append(" : ");
//            if(exclude(codes)) {
//                continue;
//            }
//            count++;
//            sb.append(toRegionalFlag(codes)).append(" ");
//        }
//        Toast.makeText(this, count + " countries(Impl).", Toast.LENGTH_LONG).show();
//
//        textView.setText(sb.toString());

        final List<String> countryCodesList = createCountryCodesList();
        Toast.makeText(this, countryCodesList.size() + "", Toast.LENGTH_SHORT).show();
        for (String codes : countryCodesList) {
            final LayoutParams layoutParams = new LayoutParams(WRAP_CONTENT, MATCH_PARENT);
            layout.addView(buildTestView(toRegionalFlag(codes)), layoutParams);
        }
    }

    @OnClick(R.id.count_button)
    void count() {
        int count = 0;
        for (int i = 0; i < layout.getChildCount(); i++) {
            TextView view = (TextView) layout.getChildAt(i);
            int width = view.getMeasuredWidth();
            if (width < 100) {
                count++;
                Log.d("regional", "flag:" + toCountryCodes(view.getText()));
            }
        }
        Toast.makeText(this, count + "", Toast.LENGTH_SHORT).show();
    }

    private List<String> createCountryCodesList() {
        final List<String> list = new ArrayList<String>();
        for (char first = 'a'; first <= 'z'; first++) {
            for (char second = 'a'; second <= 'z'; second++) {
                list.add(String.valueOf(new char[]{first, second}));
            }
        }
        return list;
    }

    private TextView buildTestView(String regionalFlagChars) {
        TextView textView = new TextView(this);
        textView.setText(regionalFlagChars);
        return textView;
    }

    private String toRegionalFlag(String codes) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < codes.length(); i++) {
            char c = (char) (REGIONAL_LOW_A + (codes.toLowerCase().charAt(i) - 'a'));
            sb.append(REGIONAL_HIGH).append(c);
        }
        return sb.toString();
    }

    private String toCountryCodes(CharSequence regionalFlagChars) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < regionalFlagChars.length(); i++) {
            if (regionalFlagChars.charAt(i) == REGIONAL_HIGH) {
                continue; // HIGHサロゲートは無視
            }
            char c = (char) (regionalFlagChars.charAt(i) - REGIONAL_LOW_A + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    private static String countryCodes = "IS,IE,AZ,AF,US,VI,AS,AE,DZ,AR,AW,AL,AM,AI,AO,AG,AD,YE," +
            "GB,IO,VG,IL,IT,IQ,IR,IN,ID,WF,UG,UA,UZ,UY,EC,EG,EE,ET,ER,SV,AU,AT,AX,OM,NL,GH,CV," +
            "GG,GY,KZ,QA,UM,CA,GA,CM,GM,KH,MP,GN,GW,CY,CU,CW,GR,KI,KG,GT,GP,GU,KW,CK,GL,CX,GE," +
            "GD,HR,KY,KE,CI,CC,CR,KM,CO,CG,CD,SA,GS,WS,ST,BL,ZM,PM,SM,MF,SL,DJ,GI,JE,JM,SY,SG," +
            "SX,ZW,CH,SE,SD,SJ,ES,SR,LK,SK,SI,SZ,SC,GQ,SN,RS,KN,VC,SH,LC,SO,SB,TC,TH,KR,TW,TJ," +
            "TZ,CZ,TD,CF,CN,TN,KP,CL,TV,DK,DE,TG,TK,DO,DM,TT,TM,TR,TO,NG,NR,NA,AQ,NU,NI,NE,JP," +
            "EH,NC,NZ,NP,NF,NO,HM,BH,HT,PK,VA,PA,VU,BS,PG,BM,PW,PY,BB,PS,HU,BD,TL,PN,FJ,PH,FI," +
            "BT,BV,PR,FO,FK,BR,FR,GF,PF,TF,BG,BF,BN,BI,VN,BJ,VE,BY,BZ,PE,BE,PL,BA,BW,BQ,BO,PT," +
            "HK,HN,MH,MO,MK,MG,YT,MW,ML,MT,MQ,MY,IM,FM,ZA,SS,MM,MX,MU,MR,MZ,MC,MV,MD,MA,MN,ME," +
            "MS,JO,LA,LV,LT,LY,LI,LR,RO,LU,RW,LS,LB,RE,RU";

    private boolean exclude(String code) {
        String ignore = "WF,UM,GP,GS,BL,PM,MF,SJ,SH,AQ,EH,NC,HM,BV,FK,GF,TF,BQ,YT,MQ,RE";
        return ignore.contains(code);
    }

}
