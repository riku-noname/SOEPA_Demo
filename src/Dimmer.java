import gnu.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.io.*;


public class Dimmer {
    static ArrayList<Integer> packet = new ArrayList<>();
    ArrayList<String[]> al = new ArrayList<String[]>();
    private CommPortIdentifier comID;
    private CommPort commPort;
    private SerialPort port;
    private static String com = "COM5";
    private OutputStream out;
    private static int SEQ = 0x00;
    private int lum = 0x00;
    private int lightnum = 16;
    int time_interval = 4000;
    String[] iddata = null;
    int intid[] = new int[100];
    private int counter = 1;

    //CSVから読み込んだ値格納用の配列
    protected static int CSV_Signal_Cd[] = new int[402];
    protected static int CSV_Signal_K[] = new int[402];

    public static void NumericConversion(Light light[]){

        readCSV();

        for(int i = 0; i < light.length; i++){
            //光度の近似値計算用の変数定義
            double PreCd= 0.0;
            double PostCd = 0.0;

            for(int num = 0; num < CSV_Signal_Cd.length; num++){
                //System.out.println(CSV_Signal_Cd[num]);
                if(num % 2 == 1){
                    if(CSV_Signal_Cd[num] - light[i].get_CD() >= 0){
//                        System.out.println(num);
                        System.out.println(CSV_Signal_Cd[num]);
                        System.out.println(light[i].get_CD());
                        System.out.println(CSV_Signal_Cd[num] - light[i].get_CD());
                        PreCd = Math.abs(CSV_Signal_Cd[num -2] - light[i].get_CD());
                        PostCd = Math.abs(CSV_Signal_Cd[num] - light[i].get_CD());
                        if(PreCd < PostCd){
                            light[i].set_Signal_Cd(CSV_Signal_Cd[num-3]);
                            System.out.println("Light[" + i + "]の光度信号値 : " + light[i].get_Signal_Cd());
                            break;
                        }
                        else{
                            light[i].set_Signal_Cd(CSV_Signal_Cd[num-1]);
                            System.out.println("Light[" + i + "]の光度信号値 : " + light[i].get_Signal_Cd());
                            break;
                        }
                    }
                }
            }

           //System.out.println("Light[" + i + "]の光度信号値 : " + light[i].get_Signal_Cd());


            //色温度の四捨五入計算用の変数定義
            double Kdouble = 0.0;
            int Kint = 0;
            int K1000 = 0;
            double div = 0.0;
            //色温度の四捨五入
            Kdouble = light[i].get_K() / 100.0;
            //System.out.println("Kdouble : " + Kdouble);
            Kint = (int)Kdouble;
            //System.out.println("Kint : " + Kint);
            K1000 = Kint * 100;
            //System.out.println("K1000 : " + K1000);
            div = light[i].get_K() - K1000;
            //System.out.println("div : " + div);
            if(div >= 25){
                light[i].set_Signal_K(K1000 + 50);
            }
            else{
                light[i].set_Signal_K(K1000);
            }
            //System.out.println("Light[" + i + "]の四捨五入したK : " + light[i].get_Signal_K());

            for(int num = 0; num < CSV_Signal_Cd.length; num++){
                if(num % 2 == 1){
                    if(CSV_Signal_K[num] == light[i].get_Signal_K()){
                        //System.out.println(num);
                        //System.out.println(CSV_Signal_K[num-1]);
                        light[i].set_SignalKNum(CSV_Signal_K[num-1]);
                        //System.out.println(light[i].get_SignalKNum());
                    }
                }
            }

        }

    }

    public static void readCSV(){
        try {
            ArrayList<String[]> al = new ArrayList<>();
            File file = new File(InitialValue.SC_path);
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
            for (int lin = 0; lin < 201; lin++) {
                for (int row = 0; row < 2; row++) {
                    if(row == 1) {
                        CSV_Signal_Cd[num] = Integer.parseInt(array[lin][row]);
                    }
                    else{
                        CSV_Signal_Cd[num] = Integer.decode(array[lin][row]);
                    }
                    //System.out.println(Signal_Cd[num]);
                    //System.out.println(array[lin][row]);
                    num++;
                }
            }
        } catch (IOException e) {
            System.out.println("file read error");
        }

        try {
            ArrayList<String[]> al = new ArrayList<>();
            File file = new File(InitialValue.SK_path);
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
            for (int lin = 0; lin < 201; lin++) {
                for (int row = 0; row < 2; row++) {
                    if(row == 1) {
                        CSV_Signal_K[num] = Integer.parseInt(array[lin][row]);
                    }
                    else{
                        CSV_Signal_K[num] = Integer.decode(array[lin][row]);
                    }
                    //System.out.println(Signal_K[num]);
                    //System.out.println(array[lin][row]);
                    num++;
                }
            }
        } catch (IOException e) {
            System.out.println("file read error");
        }
    }

}
