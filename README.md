# DebugView

## Example Usage

To use DebugView, you need to run `Server` first, and then invoke the rendering service from your code. The following 2 section explains this in detail.

### Running the Server

Clone this repository using:

	https://github.com/jyuhuan/debug-view.git

Change current directory into the downloaded `debug-view/`. Execute the following command:

	sbt run

You may see the following prompt:

	Multiple main classes detected, select one to run:
	
	 [1] debugview.ExampleClientCode
	 [2] debugview.Server

Press `2` to run the server. 

Notice that you should keep the first window that appears minimized and do not close it. 


### Invoking the Rendering Sevice


In your project's `build.sbt` file, add the following lines:

```scala
resolvers += Resolver.sonatypeRepo("snapshots")
libraryDependencies += "me.yuhuan" %% "debug-view" % "0.0.0-SNAPSHOT"
```

Import the following package wherever you want to invoke the rendering service:

```scala
import debugview.RenderService
```
	
Call the rendering service like this:

```scala
val htmlStr = "<html>Your content. </html>"
RenderService.renderHtmlContent(htmlStr)
```

## Misc

Rendering services for other types of objects will be added soon.