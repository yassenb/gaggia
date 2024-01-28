import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.io.Buffer
import kotlinx.io.RawSource
import kotlinx.io.files.Path
import kotlinx.io.readString

class SerialCommunicator(private val coroutineScope: CoroutineScope, private val path: Path) {
    fun startListening(onMessage: (String) -> Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000)
                onMessage("t24")
            }
        }
    }
//    private val sink = SystemFileSystem.sink(path).buffered()
//    private val source = SystemFileSystem.source(path)
//
//    init {
//        configureBaud()
//    }
//
//    private fun configureBaud() {
//        Command("stty")
//            .args(listOf("-F", path.toString(), "115200"))
//            .stdout(Stdio.Inherit)
//            .spawn()
//            .wait()
//    }
//
//    fun startListening(onMessage: (String) -> Unit) {
//        coroutineScope.launch(Dispatchers.IO) {
//            while (true) {
//                onMessage(source.readLine())
//            }
//        }
//    }
//
//    fun send(message: String) {
//        sink.writeString(message + "\n")
//        sink.flush()
//    }
}

private val readBuffer = Buffer()

private fun RawSource.readLine(): String {
    while (true) {
        readAtMostTo(readBuffer, 1)
        val lastChar = readBuffer[readBuffer.size - 1].toInt().toChar()
        if (lastChar == '\n') break
    }
    return readBuffer.readString().dropLast(1)
}
