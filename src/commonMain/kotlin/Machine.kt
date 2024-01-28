import kotlin.concurrent.Volatile

class Machine(private val serialCommunicator: SerialCommunicator) {
    data class State(val temperature: Float)

    @Volatile
    var temperature = 0F
        private set

    val state
        get() = State(temperature = temperature)

    fun start() {
        serialCommunicator.startListening { message ->
            if (message.startsWith(TEMPERATURE_PREFIX)) {
                val temperature = message.removePrefix(TEMPERATURE_PREFIX).toFloat()
                handleTemperatureUpdate(temperature)
            } else {
                error("unknown command")
            }
        }
    }

    private fun handleTemperatureUpdate(value: Float) {
        temperature = value
    }

    companion object {
        private const val TEMPERATURE_PREFIX = "t"

        lateinit var instance: Machine
            private set

        fun initInstance(serialCommunicator: SerialCommunicator) {
            instance = Machine(serialCommunicator)
        }
    }
}
