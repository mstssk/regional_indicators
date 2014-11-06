package jp.mstssk.regional_indicators.regional_indicators;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView textView = (TextView) findViewById(R.id.text);

        char regionalHigh = '\uD83C';
        char regionalLowA = '\uDDE6';

        StringBuilder sb = new StringBuilder();
//        for (char i = 'a'; i <= 'z'; i++) {
//            char ichar = (char) (regionalLowA + (i - 'a'));
//            for (char j = 'a'; j <= 'z'; j++) {
//                char jchar = (char) (regionalLowA + (j - 'a'));
//                sb.append(i).append(j).append(" : ");
//                sb.append(regionalHigh).append(ichar);
//                sb.append(regionalHigh).append(jchar);
//                sb.append('\n');
//            }
//        }
        int count = 0;
        String[] split = countryCodes.split(",");
        Toast.makeText(this, split.length + " countries.", Toast.LENGTH_LONG).show();
        for (String codes : split) {
//            sb.append(codes).append(" : ");
            if(exclude(codes)) {
                continue;
            }
            count++;
            for (int i = 0; i < codes.length(); i++) {
                char c = (char) (regionalLowA + (codes.toLowerCase().charAt(i) - 'a'));
                sb.append(regionalHigh).append(c);
            }
            sb.append(" ");
        }
        Toast.makeText(this, count + " countries(Impl).", Toast.LENGTH_LONG).show();

        textView.setText(sb.toString());
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
