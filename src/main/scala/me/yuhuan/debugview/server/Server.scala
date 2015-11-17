package me.yuhuan.debugview.server

import java.net._

import me.yuhuan.util.io._
import me.yuhuan.util.net.TcpMessenger
import scala.sys.process._


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
      case TaskCode.RenderHtml ⇒ {
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
        val svgStr = segments.mkString("")
        Renderer.showSvgWindow(className, svgStr)

        println("done. \n")
      }
      case TaskCode.RenderGraphviz ⇒ {
        println("Rendering Graphviz dot... ")

        println("Receiving class name...")
        val className = messenger.receiveString
        println("\tWaiting for segments... ")
        val segments = messenger.receiveStrings
        println("\tSegments received. ")
        val dotStr = segments.mkString("")


        val tmpFileName = getClass.getResource("/tmp.dot").getPath
        TextFile.writeAll(tmpFileName)(dotStr)

        val svgFileName = getClass.getResource("/tmp.svg").getPath
        s"dot -Tsvg $tmpFileName -o $svgFileName".!
        TextFile.deleteIfExists(tmpFileName)

        val svgStr = TextFile.readAll(svgFileName)
        Renderer.showSvgWindow(className, svgStr)
      }
      case _ ⇒ {
        println(s"Unknown task code $taskCode received! \n")
      }
    }

    clientSocket.close()


  }

}
