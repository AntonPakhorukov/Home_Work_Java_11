import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) throws SocketException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Сервер запущен, ожидаем подключение...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while(true) {
                String clientRequest = dataInputStream.readUTF();
                if (clientRequest.equals("end")) break;
                String[] str = clientRequest.split(" ");
                double a = Double.parseDouble(str[0]);
                String sign = str[1];
                double b = Double.parseDouble(str[2]);
                double result;
                switch (sign) {
                    case "+":
                        result = a + b;
                        dataOutputStream.writeUTF(String.format("Сумма чисел равна: %.2f", result));
                        break;    
                    case "-":
                        result = a - b;
                        dataOutputStream.writeUTF(String.format("Разница чисел равна: %.2f", result));
                        break;
                    case "*":
                        result = a * b;
                        dataOutputStream.writeUTF(String.format("Умножение чисел равно: %.2f", result));
                        break;
                    case "/":
                        result = a / b;
                        dataOutputStream.writeUTF(String.format("Умножение чисел равно: %.2f", result));
                        break;
                }
                System.out.println("Клиент сказал" + clientRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
