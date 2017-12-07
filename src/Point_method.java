import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Point_method {
    protected static double all_cd[] = new double[182];
    //protected static double influence[][] = new double[88][12];

    public static void calc_degree(Sensor sensor[], Light light[]){
        double l_2 = 0.0;
        double l = 0.0;
        double thita = 0.0;
        double kakudo = 0.0;
        int degree = 0;
        double cd = 0.0;
        double ill = 0.0;

        read_CdCsv();

        for(int lnum = 0; lnum < InitialValue.LIGHT_NUM; lnum++){
            for(int snum = 0; snum < InitialValue.SENSOR_NUM; snum++){
                //sensorとlightの平面距離 l を求める
                l_2 = (light[lnum].get_Light_X() - sensor[snum].get_Sensor_X()) * (light[lnum].get_Light_X() - sensor[snum].get_Sensor_X())
                        + (light[lnum].get_Light_Y() - sensor[snum].get_Sensor_Y()) * (light[lnum].get_Light_Y() - sensor[snum].get_Sensor_Y());
                l = Math.sqrt(l_2);
                //System.out.println("センサ[" + (snum) + "]と照明[" + (lnum) + "]の二乗距離（l^2）：" + l_2);
                //System.out.println("センサ[" + (snum) + "]と照明[" + (lnum) + "]の距離（l）：" + l);

                //sensorとlightの角度を求める
                thita = Math.atan(l / InitialValue.CEILING_HEIGHT);
                //System.out.println("θ = " + thita + " [rad]");

                //ラジアンから度に変換
                kakudo = Math.round(Math.toDegrees(thita));
                //System.out.println(kakudo);
                degree = (int) kakudo; //degreeに角度を代入
                //System.out.println("角度：" + degree +"°");

                //cdを求める
                cd = all_cd[degree];
                //System.out.println("光度：" + cd + " [cd]");

                //光度から照度を計算
                //System.out.println("cos" + degree + "°：" + Math.cos(Math.toRadians(degree)));
                ill = cd * Math.cos(Math.toRadians(degree)) / (InitialValue.CEILING_HEIGHT * InitialValue.CEILING_HEIGHT + l_2);
                //System.out.println("照度：" + ill +" [lx]");

                //影響度係数の計算
                light[lnum].set_Influence_deg(snum, ill / InitialValue.MAX_CD);
                //System.out.println("Influence degree(Light ID:" +lnum+", SensorID:"+snum+"):"+ light[lnum].get_Influence_deg(snum));
//                influence[lnum][snum] = ill / InitialValue.MAX_CD;
//                System.out.println("影響度係数：" + influence[lnum][snum]);
            }
        }

//        write_InfluenceCsv(influence, InitialValue.LIGHT_NUM, InitialValue.SENSOR_NUM);
    }

    public static void read_CdCsv(){
        try {
            ArrayList<String[]> al = new ArrayList<>();
            File file = new File(InitialValue.LDC_path);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                al.add(line.split(","));
            }
            br.close();
            //array[line][row]　例えば、array[0][0]=1 array[0][1] = 6
            String[][] array = new String[al.size()][];
            for (int i = 0; i < al.size(); i++) {
                //System.out.println(al.size());
                array[i] = al.get(i);
                //System.out.println(array[i]);
            }
            int num = 0;
            for (int lin = 0; lin < 90; lin++) {
                for (int row = 0; row < 1; row++) {
                    all_cd[num] = Double.parseDouble(array[lin][row]);
                    //System.out.println(all_cd[num]);
                    //System.out.println(array[lin][row]);
                    num++;
                }
            }
        } catch (IOException e) {
            System.out.println("file read error");
        }
    }
}
