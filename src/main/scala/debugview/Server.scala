package debugview

import java.io.DataInputStream
import java.net._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */


object RequestCode {
  val RenderHtml = "0"
  val RenderSvg = "1"
  val RenderGraphviz = "2"
}




object Server extends App {

  val myPortNumber = 1234
  val serverSocket = new ServerSocket(myPortNumber)
  val myIpAddress = InetAddress.getLocalHost.getHostAddress

  println(s"I am running on $myIpAddress at port $myPortNumber! ")


  while (true) {
    val clientSocket = serverSocket.accept()

    val dataInputStream = new DataInputStream(clientSocket.getInputStream)


  }

}
