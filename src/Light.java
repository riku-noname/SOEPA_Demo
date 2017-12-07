
public class Light {
	private int ID;			//照明ID
	private double CD = InitialValue.INITIAL_CD;		//光度
	private double K = InitialValue.INITIAL_K;			//色温度
	private double Light_X;											//照明のx座標
	private double Light_Y;												//照明のy座標
	private double[] Influence_deg = new double[InitialValue.SENSOR_NUM];		//各センサの影響度を格納する配列
	private int Signal_Cd = 0;				//ソケット通信時のCdの信号値（10進数）
	private int Signal_K = 4000;				//ソケット通信時のKの値
	private int SignalKNum =  0;				//ソケット通信時のKの信号値（10進数）


	public Light(int ID) {
		this.ID = ID;
		//照明の座標設定
		Light_X = InitialValue.LIGHT_INTERVAL * (ID / 5);
		Light_Y = InitialValue.LIGHT_INTERVAL * (ID % 5);
	}

	//照明IDの取得
	public int get_ID() {
		return ID;
	}

	//座標の取得
	public double get_Light_X() {					//取得
		return Light_X;
	}
	public double get_Light_Y() {					//取得
		return Light_Y;
	}

	//光度の設定と取得
	public void set_CD(double CD) {		//設定
		this.CD = CD;
	}
	public double get_CD() {					//取得
		return CD;
	}

	//色温度設定と取得
	public void set_K(double K) {			//設定
		this.K = K;
	}
	public double get_K() {					//取得
		return K;
	}

	//影響度係数の設定と取得
	public void set_Influence_deg(int sensorID, double Inf){
		Influence_deg[sensorID] = Inf;
	}
	public double get_Influence_deg(int sensorID){
		return Influence_deg[sensorID];
	}

	//Dimmerで使用する信号値に適した照明の色温度
	public void set_Signal_Cd(int Signal_Cd){this.Signal_Cd= Signal_Cd;}
	public int get_Signal_Cd(){return Signal_Cd;}

	//Dimmerで使用する信号値に適した照明の色温度
	public void set_Signal_K(int Signal_K){this.Signal_K = Signal_K;}
	public int get_Signal_K(){return Signal_K;}

	//Dimmerで使用する信号値に適した照明の色温度
	public void set_SignalKNum(int SignalKNum){this.SignalKNum = SignalKNum;}
	public int get_SignalKNum(){return SignalKNum;}

}
