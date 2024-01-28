import io.ktor.server.application.Application
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import kotlinx.coroutines.runBlocking
import kotlinx.io.files.Path

object Main {
    fun run(serialFile: String) = runBlocking {
        val serialCommunicator = SerialCommunicator(this, Path(serialFile))
        Machine.initInstance(serialCommunicator)
        Machine.instance.start()

        embeddedServer(CIO, port = 8080, module = Application::module).start(wait = false)
    }
}
