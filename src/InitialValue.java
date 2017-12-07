/***************************************************
 ******************初期値の設定クラス******************
 ***************************************************/

public class InitialValue {
	//使用機器の数
	public final static int LIGHT_NUM = 30;								//照明台数
	public final static int SENSOR_NUM = 4;								//最大センサ台数

	//模擬オフィスのサイズ
	public final static double CEILING_HEIGHT = 1.6;				//天井高

	//照明の設定
	public final static double LIGHT_INTERVAL = 0.6;				//照明の設置間隔

	public final static double  INITIAL_CD = 300;						//初期点灯光度
	public final static double  MAX_CD = 2530;							//最大点灯光度
	public final static double  MIN_CD = 20;								//最小点灯光度

	public final static double  INITIAL_K = 4000;						//初期点灯色温度
	public final static double  MAX_K = 6500;							//最大点灯色温度
	public final static double  MIN_K = 2700;							//最小点灯色温度

	//照度センサの設定
	public final static int MAX_TARGET_LX = 900;						//目標照度の上限値
	public final static int MIN_TARGET_LX = 200;						//目標照度の下限値

	public final static int MAX_TARGET_K = 6000;						//目標色温度の上限値
	public final static int MIN_TARGET_K = 3000;						//目標色温度の下限値

	//最急降下法
	public final static int MAX_STEP = 10;									//最大ステップ数
	public final static double EPS = 0.00001;								//ε：十分に小さな値
	public final static double TAU = (1+Math.sqrt(5))/2;			//τ：黄金分割法に使用
	public final static double ALPHA = 1;									//消費電力の計算に使うα，とりあえず1に設定
	public final static double BETA = 1;										//消費電力の計算に使うβ，とりあえず1に設定
	public final static double WEIGHT = 50;								//目的関数の重み．とりあえず1に設定
	public final static double INITIAL_X1 = 0;
	public final static double INITIAL_STEP_SIZE = 1;

	//ファイルパス
	public final static String LDC_path = "./LightDistributionCurve.txt";		//配光曲線のファイルパス
	public final static String SC_path = "./SignalCd.txt";		//信号値とCD値のファイルパス
	public final static String SK_path = "./SignalK.txt";		//信号値とKのファイルパス

}
