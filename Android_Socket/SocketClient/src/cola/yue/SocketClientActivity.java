package cola.yue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketClientActivity extends Activity {
	/** Called when the activity is first created. */
	private Button button = null;
	private Button sendstringbButton = null;
	private Socket socket = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.button);
		sendstringbButton = (Button) findViewById(R.id.sendbutton);

		button.setOnClickListener(new buttonOnClickListener());
		sendstringbButton.setOnClickListener(new sendbuttonOnClickListener());
		// button.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// try {
		// socket = new Socket("10.0.2.2", 8192);//
		// 注意这里也是和上面重定向的配置是有关系的。而且这里的地址必须是10.0.2.2
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });

	}

	class buttonOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				socket = new Socket("10.0.2.2", 8192);// 注意这里也是和上面重定向的配置是有关系的。而且这里的地址必须是10.0.2.2
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class sendbuttonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				// 1.建立客户端socket连接，指定服务器位置及端口
				Socket socket = new Socket("10.0.2.2", 8192);
				// 2.输入流
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 输出流
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				
				
				// 接收服务器的响应
				String reply = null;
				while (!((reply = br.readLine()) == null)) {
					System.out.println("[server]:" + reply);
				}

				//3.利用流按照一定的操作，对socket进行读写操作
				String info = "I'm client, hello!";
				pw.write(info);
				pw.flush();
				socket.shutdownOutput();
				
				// 4.关闭资源
				System.out.println("Client closed...");
				br.close();
				pw.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}