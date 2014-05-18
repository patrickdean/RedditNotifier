package patrick.redditnotifier.io

import java.io.{File, PrintWriter}

object FileWriter {
  def writeToFile(path: String, fileName: String, lines: List[String]) {
    val directory = new File(path)
    if (!directory.exists)
      directory.mkdir
    val writer = new PrintWriter(path + fileName)
    lines.foreach(line => writer.write(line + "\n"))
    writer.close()
  }
}
