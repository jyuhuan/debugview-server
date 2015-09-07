package debugview.server

import java.net._


/**
 * The rendering server program.
 * Run this before the client requests the render service (read: before everything).
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object Server extends App {

  val myPortNumber = 1234
  val serverSocket = new ServerSocket(myPortNumber)
  val myIpAddress = InetAddress.getLocalHost.getHostAddress

  println(s"I am running on $myIpAddress at port $myPortNumber! ")

  Renderer.showPersistentWindow()

  while (true) {
    val clientSocket = serverSocket.accept()
    val messenger = TcpMessenger(clientSocket)

    val taskCode = messenger.receiveInt

    taskCode match {
      case TaskCode.RenderHtmlContent ⇒ {
        println("Received new HTML page rendering task... ")

        println("Receiving class name...")
        val className = messenger.receiveString

        println("\tWaiting for segments... ")
        val segments = messenger.receiveStrings
        println("\tSegments received. ")
        val htmlStr = segments.mkString("")
        Renderer.showHtmlWindow(className, htmlStr)

        println("done. \n")
      }

      case TaskCode.RenderSvg ⇒ {
        println("Received new SVG rendering task... ")

        println("Receiving class name...")
        val className = messenger.receiveString

        println("\tWaiting for segments... ")
        val segments = messenger.receiveStrings
        println("\tSegments received. ")
        val htmlStr = segments.mkString("")
        Renderer.showSvgWindow(className, htmlStr)

        println("done. \n")
      }
      case TaskCode.RenderGraphviz ⇒ {
        println("Rendering Graphviz dot... ")
        println("\tNot implemented yet... \n")
      }
      case _ ⇒ {
        println(s"Unknown task code $taskCode received! \n")
      }
    }

    clientSocket.close()


  }

}
