package cola.yue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketSeverceActivity extends Activity {
	/** Called when the activity is first created. */
	public static final String SERVERIP = "10.0.2.15";// 这个地址其实没有用到
	public static final int SERVERPORT = 8191; // 注意这里和上面重定向的配置是有关系的。
	private Button button = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		System.out.println("Thread 1 >>>>>>>" + Thread.currentThread().getId());
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// try {
				// ServerSocket serverSocket = new ServerSocket(SERVERPORT);
				// while (true) {
				// Socket client = serverSocket.accept();
				// System.out.println("S: Receiving...\n"
				// + client.getInetAddress());
				// client.getInetAddress();
				// }
				// } catch (Exception e) {
				// System.out.println("S: Error");
				// e.printStackTrace();
				//
				// }
				new Servicethread().start();
			}
		});

	}

	class Servicethread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			System.out.println("Thread 2 >>>>>>>"
					+ Thread.currentThread().getId());
			try {
				ServerSocket serverSocket = new ServerSocket(SERVERPORT);
				System.out.println("waiting for connnecting......");
				Socket socket = serverSocket.accept();
				System.out.println("Connnected......");
				System.out.println("S: Receiving...\n"
						+ socket.getInetAddress());
				System.out.println("Thread 3 >>>>>>>"
						+ Thread.currentThread().getId());
				
				// 3.获得输入流
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 获得输出流
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				
				// 给客户一个响应
				String reply = "I'm server, welcome!";
				pw.write(reply);
				pw.flush();
				// 4.读取用户输入信息
				String info = null;
				while (!((info = br.readLine()) == null)) {
					System.out.println("[client]:" + info);
				}
				
				
				// 5.关闭资源
				System.out.println("Server closed...");
				pw.close();
				br.close();
				socket.close();
				serverSocket.close();
			} catch (Exception e) {
				System.out.println("S: Error");
				e.printStackTrace();

			}

		}

	}

}