import java.util.concurrent.TimeUnit;

public class Manager {
	public static void main(String[] args){
		ILS ils =new ILS();
		
		//初期光度で照明を点灯
		//send_signal_to_server(sgnal_cd,signal_k); //信号値を調光用サーバへ送信

		//各センサの目標照度・色温度の設定
		ils.add_newSensor(0.6, 0.3, 500, 4000);
		ils.add_newSensor(0.6, 1.8, 700, 6000);
		ils.add_newSensor(2.7, 0.3, 300, 3000);
		ils.add_newSensor(2.1, 2.1, 400, 5000);

		for (int i = 0; i < 1; i++) {
			//get_Target();		//目標照度・色温度の取得
			//ils.add_newSensor(x,y,LX,K);		//照度センサの追加
			//ils.change_Target(ID,LX,K);		//目標照度・色温度の変更

			ils.Calc_influence_coefficient();//逐点法による影響度係数の計算
			ils.Optimization();		//光度・色温度の最適化
			
			ils.show();


			//change_to_signal(CD,K); //光度・色温度を信号値へ変換
			//send_signal_to_server(sgnal_cd,signal_k); //信号値を調光用サーバへ送信

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}