import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server{
    public static void main(String[] args) throws SocketException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) { // связь между сервером и клиентом
            System.out.println("Сервер запущен, ожидаем подключение...");
            Socket socket = serverSocket.accept(); // Установление соединения
            System.out.println("Клиент подключился");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
            // Поток, отправленный клиенту
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            // Поток, полученный от клиента 
            while(true) {
                String clientRequest = dataInputStream.readUTF(); // возвращает строку от клиента
                if (clientRequest.equals("end")) break;
                // String[] value = clientRequest.split(" ");
                // for (int i = 0; i < value.length; i = i + 2) {
                //     String[] string = value[i].split("");
                //     String find = "qwertyuiop[]asdfghjkl;'zxcvbnm/йцукенгшщзхъфывапролджэячсмитьбю!\"№%:?()ё~`@#$^&{}><";
                //     for (int j = 0; j < string.length; j++) {
                //         if (find.contains(string[j])){
                //             string[j] = "";                            
                //         }
                //     }
                // }
                String[] str = clientRequest.split(" ");
                double a = Double.parseDouble(str[0]);
                String sign = str[1];
                double b = Double.parseDouble(str[2]);
                double result;
                switch (sign) {
                    case "+":
                        result = a + b;
                        if (result % 1 > 0) {
                            dataOutputStream.writeUTF(String.format("Сумма чисел равна: %.2f", result));
                            // отправили клиенту
                            break;
                        } else {
                            dataOutputStream.writeUTF(String.format("Сумма чисел равна: %.0f", result));
                            break;
                        }    
                    case "-":
                        result = a - b;
                        if (result % 1 > 0) {
                            dataOutputStream.writeUTF(String.format("Разница чисел равна: %.2f", result));
                            break;
                        } else {
                            dataOutputStream.writeUTF(String.format("Разница чисел равна: %.0f", result));
                            break;
                        }    
                    case "*":
                        result = a * b;
                        if (result % 1 > 0) {
                            dataOutputStream.writeUTF(String.format("Умножение чисел равно: %.2f", result));
                            break;
                        } else {
                            dataOutputStream.writeUTF(String.format("Умножение чисел равно: %.0f", result));
                            break;
                        }    
                    case "/":
                        result = a / b;
                        if (result % 1 > 0) {
                            dataOutputStream.writeUTF(String.format("Деление чисел равно: %.2f", result));
                            break;
                        } else {
                            dataOutputStream.writeUTF(String.format("Деление чисел равно: %.0f", result));
                            break;
                        }    
                }
                System.out.println("Клиент сказал " + clientRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
