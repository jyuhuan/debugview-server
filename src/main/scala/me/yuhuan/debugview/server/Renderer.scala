package me.yuhuan.debugview.server

import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.geometry.Insets
import javafx.scene._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.scene.paint.Color
import javafx.scene.web.WebView
import javafx.stage._
import javax.swing.SwingUtilities

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
private object Renderer {

  val panelBackgroundColor = Color.rgb(60, 63, 65)
  val panelForegroundColor = Color.rgb(187, 187, 187)
  val contentBackgroundColor = Color.rgb(43, 43, 43)

  def showHtmlWindow(title: String, html: String) = {
    println("\tShowing window...")

    SwingUtilities.invokeLater(new Runnable {
      def run(): Unit = {
        new JFXPanel
        Platform.runLater(new Runnable {
          def run(): Unit = {
            val stage = new Stage()

            val root = new VBox()
            val scene = new Scene(root, 500, 500, panelBackgroundColor)

            stage.setTitle(title)
            stage.setScene(scene)

            // Main webview
            val wbvContent = new WebView()

            wbvContent.getEngine.loadContent(html)

            // Toolbar under main webview
            val toolbar = new ToolBar()
            val slider = new Slider(0.1, 3.0, 1.0)
            wbvContent.zoomProperty().bind(slider.valueProperty())
            toolbar.getItems.addAll(slider)


            VBox.setVgrow(wbvContent, Priority.ALWAYS)

            // Add everything to the root
            root.getChildren.addAll(wbvContent, toolbar)

            stage.show()
            stage.requestFocus()
          }
        })
      }
    })
  }

  def showSvgWindow(title: String, svg: String) = {
    val html = s"<html> $svg </html>"
    showHtmlWindow(title, html)
  }




  def showPersistentWindow() = {
    SwingUtilities.invokeLater(new Runnable {
      def run(): Unit = {
        new JFXPanel
        Platform.runLater(new Runnable {
          def run(): Unit = {
            val stage = new Stage()

            val root = new Group()
            val scene = new Scene(root, 400, 150, panelBackgroundColor)

            stage.setTitle("Debug View 0.1.0")
            stage.setScene(scene)

            // Add controls to the root

            val label = new Label("Minimize this window but DON'T close it! \nFailure to do so will result in windows not shown. \n\nUntil I find a better solution, it has to be like this. \nBEAR WITH IT!")
            label.setTextFill(panelForegroundColor)
            label.setPadding(new Insets(10.0, 10.0, 10.0, 10.0))
            val mainVBox = new VBox(8)
            mainVBox.getChildren.addAll(label)

            root.getChildren.add(mainVBox)

            stage.show()
          }
        })
      }
    })
  }

}
